package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PatientRoutingService {

    private PatientDAO dao = new PatientDAOImpl();
    private PatientValidator validator = new PatientValidator();
    private ProcessingSummary summary = new ProcessingSummary();

    public void processPatients() {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                System.out.println("Connection failed. Exiting...");
                return;
            }

            con.setAutoCommit(false);

            List<Patient> patients = dao.getPendingPatients(con);

            for (Patient p : patients) {
                if (dao.alreadyTransferred(p.getPatientId(), con)) {
                    summary.addSkipped(p.getPatientId(), "Already transferred");
                    continue;
                }

                List<String> errors = validator.validatePatient(p);
                if (!errors.isEmpty()) {
                    summary.addFailed(p.getPatientId(), errors.toString());
                    continue;
                }

                try {
                    if (isCritical(p)) {
                        dao.insertCriticalPatient(p, con);
                        summary.addCritical(p.getPatientId());
                    } else {
                        dao.insertGeneralPatient(p, con);
                        summary.addGeneral(p.getPatientId());
                    }

                    dao.markProcessed(p.getPatientId(), con);

                    con.commit();
                    summary.addSuccess(p.getPatientId());

                } catch (SQLException e) {
                    con.rollback();
                    summary.addFailed(p.getPatientId(), "Transaction failed: " + e.getMessage());
                }
            }

            summary.printSummary();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isCritical(Patient p) {
        return p.getConditionStatus().equalsIgnoreCase("Critical") ||
               (p.getAdmissionType().equalsIgnoreCase("Emergency") && p.getTriageScore() >= 7) ||
               (p.getConditionStatus().equalsIgnoreCase("Moderate") && p.getTriageScore() >= 8);
    }
}
