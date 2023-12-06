package com.kn.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

	public static void main(String[] args) {
		String URL= "jdbc:mysql://localhost:3306/university";
		String USER_NAME="root";
		String PASSWORD="Ram@8660045085";
		String CREATE_STUDENT_QUERY ="CREATE TABLE STUDENT4 (ID INT,NAME VARCHAR(20),MARKS INT);";
		String INSERT_INTO_STUDENT4 ="INSERT INTO STUDENT4 VALUES (1,'RAM',100);";
		String UPDATE_INTO_STUDENT="UPDATE STUDENT4 SET NAME='RAM SHYAM' WHERE ID=1; ";
		String INSERT_INTO_STUDENT ="INSERT INTO STUDENT4 VALUES (2,'RAM',200);";
		String DELECT_FROM_STUDENT4="DELETE FROM STUDENT4 WHERE ID=2;";
		try {
			//1. load and register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//FQCN(Fully Qualification class name)- canonical na,me
			System.out.println("====>Driver Loaded and registered successifully");
			//2.Establice the connection with database
			Connection con= DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("===>Connection Estblised with database "+con);
			
			//3.CREATE Statement object
			Statement stmt= con.createStatement();
			
			//4.send and execute query
			int rowesEffeted=stmt.executeUpdate(DELECT_FROM_STUDENT4);
			System.out.println(rowesEffeted+" rowes effeted");
			
			System.out.println("------>Student-4 table created successfully !");
		} catch (ClassNotFoundException e) {
			System.out.println("----->Driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("--->Failed to establice connaction");
			e.printStackTrace();
		}
	}

}
