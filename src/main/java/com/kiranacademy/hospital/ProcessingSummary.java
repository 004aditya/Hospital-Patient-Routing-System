package com.kiranacademy.hospital;

public class ProcessingSummary {
	 
    private int total;
    private int criticalCount;
    private int generalCount;
    private int failedCount;
    private int skippedCount;

    public void addSuccess(int patientId) {
        total++;
        System.out.println("Patient " + patientId + " -> SUCCESS");
    }

    public void addCritical(int patientId) {
        criticalCount++;
        System.out.println("Patient " + patientId + " -> CRITICAL CARE");
    }

    public void addGeneral(int patientId) {
        generalCount++;
        System.out.println("Patient " + patientId + " -> GENERAL CARE");
    }

    public void addFailed(int patientId, String reason) {
        failedCount++;
        System.out.println("Patient " + patientId + " -> FAILED -> " + reason);
    }

    public void addSkipped(int patientId, String reason) {
        skippedCount++;
        System.out.println("Patient " + patientId + " -> SKIPPED -> " + reason);
    }

    public void printSummary() {
        System.out.println("\n=== PROCESSING SUMMARY ===");
        System.out.println("Total Processed: " + total);
        System.out.println("Critical Care: " + criticalCount);
        System.out.println("General Care: " + generalCount);
        System.out.println("Validation Failed: " + failedCount);
        System.out.println("Skipped Duplicate: " + skippedCount);
        System.out.println("==========================");
    }
}
