package com.kn.jdbc.connection2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCConnection2 {
	private static final String URL= "jdbc:mysql://localhost:3306/university";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Ram@8660045085";
	private static final String CREATE_TRAINER_QUERY ="CREATE TABLE TRAINER (ID INT,NAME VARCHAR(20));";
	private static final String INSERT_INTO_TRAINER1 ="INSERT INTO TRAINER VALUES (1,'Punith'),(2,'Arun sir'),(3,'Priya mdm'),(4,'ram sir');";
	private static final String UPDATE_INTO_TRAINER="UPDATE TRAINER SET NAME='Punith sir' WHERE ID=1; ";
	private static final String DELECT_FROM_TRAINER="DELETE FROM TRAINER WHERE ID=4;";
	private static final String SELECT_ALL_FROM="SELECT * FROM TRAINER" ;
	public static void main(String[] args) {
		Connection con=null;
		try {
			//1. load and register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//FQCN(Fully Qualification class name)- canonical na,me
			System.out.println("====>Driver Loaded and registered successifully");
			//2.Establice the connection with database
			con= DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("===>Connection Estblised with database "+con);
			
			//3.CREATE Statement object
			Statement stmt= con.createStatement();
			
			//4.send and execute query
			
			//Created the table
			//stmt.execute(CREATE_TRAINER_QUERY);
			//System.out.println("Successfully Created the TRAINER table");
			
			//Insert the data to trainer table
//		    int rowesEffeted=stmt.executeUpdate(INSERT_INTO_TRAINER);
//		    System.out.println(rowesEffeted+" rowes effeted");
			
			//SElect quary
		    ResultSet rs=stmt.executeQuery(SELECT_ALL_FROM);
			while(rs.next()) {
				System.out.println("ID = "+rs.getInt(1)+", NAME = "+rs.getString(2));
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
		}
	}

}
