package com.kiranacademy.hospital;

import java.sql.Connection;
import java.util.List;

public interface PatientDAO {

	List<Patient> getPendingPatients(Connection con);
    void insertCriticalPatient(Patient patient, Connection con);
    void insertGeneralPatient(Patient patient, Connection con);
    void markProcessed(int patientId, Connection con);
    boolean alreadyTransferred(int patientId, Connection con);
}
