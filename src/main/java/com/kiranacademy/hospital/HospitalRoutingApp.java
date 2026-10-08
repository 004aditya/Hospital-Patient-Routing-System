package com.kiranacademy.hospital;

public class HospitalRoutingApp {

    public static void main(String[] args) {
        System.out.println("=== KIRAN ACADEMY - HOSPITAL PATIENT ROUTING ===");

        PatientRoutingService service = new PatientRoutingService();

        service.processPatients();

        System.out.println("=== END OF EXECUTION ===");
    }
}
