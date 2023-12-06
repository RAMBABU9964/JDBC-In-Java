package com.kn.jdbc.connection2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.xdevapi.PreparableStatement;

public class JDBCConnectionDemo {
	private static final String URL= "jdbc:mysql://localhost:3306/university";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Ram@8660045085";
	private static final String CREATE_STUDENT6_QUERY ="CREATE TABLE STUDENT6 (ID INT,NAME VARCHAR(20),MARKS INT);";
	private static final String INSERT_INTO_STUDENT6 ="INSERT INTO STUDENT6 VALUES (?,?,?);";
	private static final String UPDATE_INTO_STUDENT6="UPDATE STUDENT6 SET MARKS =(MARKS+10) WHERE ID=?; ";
	private static final String DELECT_FROM_STUDENT6="DELETE FROM STUDENT6 WHERE ID=?;";
	private static final String SELECT_ALL_FROM="SELECT * FROM STUDENT6" ;
	public static void main(String[] args) {
		Connection con=null;
		Scanner scan=new Scanner(System.in);
		try {
			//1. load and register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//FQCN(Fully Qualification class name)- canonical na,me
			//System.out.println("====>Driver Loaded and registered successifully");
			//2.Establice the connection with database
			con= DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			//System.out.println("===>Connection Estblised with database "+con);
			 
			JDBCConnectionDemo conn=new JDBCConnectionDemo();
			System.out.println("1. To INSERT DATA");
			System.out.println("2. To SELECT DATA");
			System.out.println("3. To UPDATE DATA");
			System.out.println("4. To DELECT DATA");
			int option=scan.nextInt();
			switch (option) {
			case 1: {
				conn.selectQueri(con);
				conn.insertData(con, scan);
				conn.selectQueri(con);
				break;
			}
			case 2:{
				conn.selectQueri(con);
				break;
			}case 3:{
				conn.selectQueri(con);
				conn.updataData(con, scan);
				conn.selectQueri(con);
				break;
			}	
			case 4:{
				conn.selectQueri(con);
				conn.delectData(con, scan);
				conn.selectQueri(con);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
			
			
			
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
               
			}
			System.out.println("ALL the date are curretly execute ");
			}

	}
	
	public void creatTable(Connection con) throws SQLException {
		Statement stmt= con.createStatement();
		stmt.execute(CREATE_STUDENT6_QUERY);
		System.out.println("Successfully Created the Student6 table");
	}
	
	public void insertData(Connection con,Scanner scan) throws SQLException {
		System.out.println("Enter value for  id =");
		int id=scan.nextInt();
		System.out.println("Enter value for  name =");
		String Name=scan.next();
		System.out.println("Enter value for  marks =");
		double marks=scan.nextDouble();
		
		PreparedStatement pstmt= con.prepareStatement(INSERT_INTO_STUDENT6);
		pstmt.setInt(1, id);
		pstmt.setString(2, Name);
		pstmt.setDouble(3, marks);
		pstmt.execute();
	}
	public void updataData(Connection con,Scanner scan) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(UPDATE_INTO_STUDENT6);
		System.out.println("Enter the ID Whose Marks need to update =");
		int id= scan.nextInt();
		pstmt.setInt(1, id);
		
		pstmt.execute();
		System.out.println("Marks of Student with ID = "+id+", has been updated");
	}
	public void selectQueri(Connection con) throws SQLException {
		PreparedStatement pstmt=con.prepareStatement(SELECT_ALL_FROM);
		ResultSet rs=pstmt.executeQuery(SELECT_ALL_FROM);
		while(rs.next()) {
			System.out.println("ID = "+rs.getInt(1)+
					", NAME = "+rs.getString(2)+" ,MARKS = "+rs.getInt(3));
		}}
		
	public void delectData(Connection con,Scanner scan) throws SQLException {
	PreparedStatement pstmt = con.prepareStatement(DELECT_FROM_STUDENT6);
		System.out.println("Enter the ID Whose Marks need to delect =");
		int id= scan.nextInt();
		pstmt.setInt(1, id);
		
		pstmt.execute();
		System.out.println("Marks of Student with ID = "+id+", has been delected");
	
	}

}
