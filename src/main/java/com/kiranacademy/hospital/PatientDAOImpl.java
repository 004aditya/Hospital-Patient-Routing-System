package com.kiranacademy.hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public List<Patient> getPendingPatients(Connection con) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM hospital_patient_intake WHERE transfer_status = 'PENDING'";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setPatientName(rs.getString("patient_name"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setDisease(rs.getString("disease"));
                p.setAdmissionType(rs.getString("admission_type"));
                p.setConditionStatus(rs.getString("condition_status"));
                p.setTriageScore(rs.getInt("triage_score"));
                p.setDoctorName(rs.getString("doctor_name"));
                p.setAdmissionDate(rs.getDate("admission_date"));
                p.setMobile(rs.getString("mobile"));
                p.setTransferStatus(rs.getString("transfer_status"));
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void insertCriticalPatient(Patient patient, Connection con) {
        String sql = "INSERT INTO critical_care_patients " +
                     "(source_patient_id, patient_name, age, disease, admission_type, condition_status, triage_score, doctor_name) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patient.getPatientId());
            ps.setString(2, patient.getPatientName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getDisease());
            ps.setString(5, patient.getAdmissionType());
            ps.setString(6, patient.getConditionStatus());
            ps.setInt(7, patient.getTriageScore());
            ps.setString(8, patient.getDoctorName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertGeneralPatient(Patient patient, Connection con) {
        String sql = "INSERT INTO general_care_patients " +
                     "(source_patient_id, patient_name, age, disease, admission_type, condition_status, triage_score, doctor_name) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patient.getPatientId());
            ps.setString(2, patient.getPatientName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getDisease());
            ps.setString(5, patient.getAdmissionType());
            ps.setString(6, patient.getConditionStatus());
            ps.setInt(7, patient.getTriageScore());
            ps.setString(8, patient.getDoctorName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void markProcessed(int patientId, Connection con) {
        String sql = "UPDATE hospital_patient_intake SET transfer_status = 'PROCESSED', processed_at = NOW() WHERE patient_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean alreadyTransferred(int patientId, Connection con) {
        String sql = "SELECT source_patient_id FROM critical_care_patients WHERE source_patient_id = ? " +
                     "UNION SELECT source_patient_id FROM general_care_patients WHERE source_patient_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
