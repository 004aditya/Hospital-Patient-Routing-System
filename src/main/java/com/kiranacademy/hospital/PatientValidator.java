package com.kiranacademy.hospital;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PatientValidator {

    public List<String> validatePatient(Patient p) {
        List<String> errors = new ArrayList<>();

        if (p.getPatientName() == null || p.getPatientName().trim().length() < 3)
            errors.add("Invalid patient name");

        if (p.getAge() < 0 || p.getAge() > 120)
            errors.add("Invalid age");

        if (!(p.getGender().equalsIgnoreCase("Male") ||
              p.getGender().equalsIgnoreCase("Female") ||
              p.getGender().equalsIgnoreCase("Other")))
            errors.add("Invalid gender");

        if (p.getDisease() == null || p.getDisease().trim().isEmpty())
            errors.add("Disease cannot be blank");

        if (!(p.getAdmissionType().equalsIgnoreCase("Emergency") ||
              p.getAdmissionType().equalsIgnoreCase("Regular")))
            errors.add("Invalid admission type");

        if (!(p.getConditionStatus().equalsIgnoreCase("Critical") ||
              p.getConditionStatus().equalsIgnoreCase("Moderate") ||
              p.getConditionStatus().equalsIgnoreCase("Stable")))
            errors.add("Invalid condition status");

        if (p.getTriageScore() < 1 || p.getTriageScore() > 10)
            errors.add("Invalid triage score");

        if (p.getDoctorName() == null || p.getDoctorName().trim().isEmpty())
            errors.add("Doctor name cannot be blank");

        if (p.getAdmissionDate() != null && p.getAdmissionDate().toLocalDate().isAfter(LocalDate.now()))
            errors.add("Admission date cannot be in the future");

        if (p.getMobile() == null || !p.getMobile().matches("\\d{10}"))
            errors.add("Invalid mobile number");

        if (p.getTransferStatus() != null && !p.getTransferStatus().equalsIgnoreCase("PENDING"))
            errors.add("Record is not pending");

        return errors;
    }
}
