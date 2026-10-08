package com.kiranacademy.hospital;

import java.sql.Date;
import java.util.Scanner;

public class Patient {

	private int patientId;
    private String patientName;
    private int age;
    private String gender;
    private String disease;
    private String admissionType;
    private String conditionStatus;
    private int triageScore;
    private String doctorName;
    private Date admissionDate;
    private String mobile;
    private String transferStatus;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void accept() {
		
	}
	
	public void show() {
		
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getAdmissionType() {
		return admissionType;
	}
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}
	public String getConditionStatus() {
		return conditionStatus;
	}
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}
	public int getTriageScore() {
		return triageScore;
	}
	public void setTriageScore(int triageScore) {
		this.triageScore = triageScore;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	
}
