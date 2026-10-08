package com.tka.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connection1 {

	

		private static String driver_class="com.mysql.cj.jdbc.Driver";
		private static String database_url="jdbc:mysql://localhost:3306/";
		private static String database_name="batch_k_435";
		private static String database_username="root";
		private static String database_password="Root@123";
		private static Statement statement=null;
		private static Connection connection =null;
		private static ResultSet resultSet=null;
		private static PreparedStatement preparedStatement = null;
		
		Connection1(){
			
		}
		public void getData() {
			getConnection();
			String insert = "Insert into hospital_patient_high values(?,?,?)";
			String query="select * from hospital_patient";
			String insert_low="Insert into hospital_patient_low values(?,?,?)";
			try {
				preparedStatement=connection.prepareStatement(query);
				
//				Statement statement= connection.createStatement();
				resultSet=preparedStatement.executeQuery();
				
				System.out.println("Get all patient data");
				while(resultSet.next()) {
					System.out.println("Name -: "+resultSet.getString(1)+", City -: "+resultSet.getString(2)
					+ ", Expence -: "+resultSet.getDouble(3));
					if(resultSet.getDouble(3)>10000) {
						preparedStatement=connection.prepareStatement(insert);
						
						preparedStatement.setString(1,resultSet.getString(1));
						preparedStatement.setString(2,resultSet.getString(2));
						preparedStatement.setInt(3,resultSet.getInt(3));
						preparedStatement.executeUpdate();
					}
					if(resultSet.getDouble(3)<10000) {
						preparedStatement=connection.prepareStatement(insert_low);
					
						preparedStatement.setString(1,resultSet.getString(1));
						preparedStatement.setString(2,resultSet.getString(2));
						preparedStatement.setInt(3,resultSet.getInt(3));
						preparedStatement.executeUpdate();
					}
				}
				
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnection();
			}
		}
		
		public void getHospitalPatientHigh() {
			getConnection();
			String query="Select * from hospital_patient_high";
			try {
				preparedStatement=connection.prepareStatement(query);
				resultSet=preparedStatement.executeQuery();
				System.out.println("Get all patient data");
				while(resultSet.next()) {
					System.out.println("Name -: "+resultSet.getString(1)+", City -: "+resultSet.getString(2)
					+ ", Expence -: "+resultSet.getDouble(3));
				}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			closeConnection();
		}
			
		}
		
		public void getHospitalPatientLow() {
			getConnection();
			String query="Select * from hospital_patient_low";
			try {
				preparedStatement=connection.prepareStatement(query);
				resultSet=preparedStatement.executeQuery();
				System.out.println("Get all patient data");
				while(resultSet.next()) {
					System.out.println("Name -: "+resultSet.getString(1)+", City -: "+resultSet.getString(2)
					+ ", Expence -: "+resultSet.getDouble(3));
				}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			closeConnection();
		}
			
		}
		public static void getConnection() {
			try {
				Class.forName(driver_class);
				connection = DriverManager.getConnection(database_url + database_name, database_username, database_password);
				System.out.println("Databasse connection done...");
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			} catch (SQLException e) {
				System.out.println(e);
			}
}
		private static void closeConnection() {
			try {
			if(connection!=null) 
				connection.close();
			if(statement!=null)
				statement.close();
			if(resultSet!=null)
				resultSet.close();
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				System.out.println("Connection close");
			}
		}
		
		public static void main(String[] args) {
			Connection1 connection = new Connection1();
			connection.getData();
			connection.getHospitalPatientHigh();
			connection.getHospitalPatientLow();
		}
}
