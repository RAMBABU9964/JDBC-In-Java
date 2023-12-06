package com.kn.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentJDBCDemo {
	private static final String URL= "jdbc:mysql://localhost:3306/university";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Ram@8660045085";

	public static void main(String[] args) {
      Connection con=null;
      
      try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con= DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		Student s1 =new Student(2,"Amit", 35);
		Student s2 =new Student(3,"Ankit", 85);
		DBFHandler.insertData(con, s1);
		DBFHandler.insertData(con, s2);
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      finally {
		if(con !=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}

}
