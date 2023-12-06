package com.kn.jdbc.connection3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCBatchProcessing {
	private static final String URL= "jdbc:mysql://localhost:3306/university";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Ram@8660045085";
	private static final String Query1 = "UPDATE TRAINER SET GENDER='MALE' WHERE ID !=3;";
	private static final String Query2 = "UPDATE TRAINER SET GENDER='FEMALE' WHERE ID =3;";
	public static void main(String[] args) {
		Connection con=null;
		try {
			//1. load and register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2.Establice the connection with database
			con= DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			//3. CREAT STATEMENT OBJECT
			Statement stmt=con.createStatement();
			//Batch processing -Executing multiple queries at a time
			//---->(A) set autocommit as false
			con.setAutoCommit(false);
			
			//--->(B) Add tasks to batch
			stmt.addBatch(Query1);
			stmt.addBatch(Query2);
			
			//--->(c) Get count of records affected
			int[] countBatch=stmt.executeBatch();
			for(int count : countBatch) {
				System.out.println(count +" rows affected");
			}
			
			//--->(D) commit
			con.commit();
			
		} catch (ClassNotFoundException e) {
			System.out.println("----->Driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("--->Failed to establice connaction");
			e.printStackTrace();
		}finally {
			//5.clos the connection
			try {
				if(con !=null) {
					con.close();
				}
			}catch (SQLException e2) {
					e2.printStackTrace();
               
			}}

	}

}
