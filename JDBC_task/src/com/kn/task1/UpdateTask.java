package com.kn.task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTask {
	private static final String URL= "jdbc:mysql://localhost:3306/university";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Ram@8660045085";
	private static final String CREATE_Emplyoe6_QUERY ="CREATE TABLE Emplyoe6 (ID INT,NAME VARCHAR(20),salary INT);";
	private static final String INSERT_INTO_Emplyoe6 ="INSERT INTO Emplyoe6 VALUES (1,'ram',25000),(2,'Shyam',35000);";
	private static final String UPDATE_INTO_Emplyoe6="UPDATE Emplyoe6 SET salary =salary+(salary/0.10) WHERE ID=?; ";
	private static final String SELECT_ALL_FROM="SELECT * FROM Emplyoe6" ;
	public static void main(String[] args) {
		Connection con=null;
		Scanner scan=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con= DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			UpdateTask up=new UpdateTask();
			//up.creatTable(con);
			// up.insertData(con);
			up.selectQueri(con);
			
			up.updataData(con, scan);
			
			up.selectQueri(con);
//			
//			pstmt.execute();
//			System.out.println("Marks of Student with ID = "+id+", has been updated");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void creatTable(Connection con) throws SQLException {
		Statement stmt= con.createStatement();
		stmt.execute(CREATE_Emplyoe6_QUERY);
		System.out.println("Successfully Created the Student6 table");
	}
	public void insertData(Connection con) throws SQLException {
		Statement stmt= con.createStatement();
		int updat =stmt.executeUpdate(INSERT_INTO_Emplyoe6);
		System.out.println(updat+" rowes effected");
	}
	public void selectQueri(Connection con) throws SQLException {
		PreparedStatement pstmt=con.prepareStatement(SELECT_ALL_FROM);
		ResultSet rs=pstmt.executeQuery(SELECT_ALL_FROM);
		while(rs.next()) {
			System.out.println("ID = "+rs.getInt(1)+
					", NAME = "+rs.getString(2)+" ,MARKS = "+rs.getInt(3));
		}}
	public void updataData(Connection con,Scanner scan) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(UPDATE_INTO_Emplyoe6);
		System.out.println("Enter the ID Whose Marks need to update =");
		int id= scan.nextInt();
		pstmt.setInt(1, id);
		
		pstmt.execute();
		System.out.println("Marks of Student with ID = "+id+", has been updated");
	}
	

}
