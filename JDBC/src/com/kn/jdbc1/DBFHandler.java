package com.kn.jdbc1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBFHandler {
private static final String Query="INSERT INTO studentes (NAME,MARKS) VALUES (?,?)";
public static void insertData(Connection con, Student s) {
	String name=s.getName();
	int marks=s.getMarks();
	
	try {
		PreparedStatement pstmt=con.prepareStatement(Query);
		pstmt.setString(1, name);
		pstmt.setInt(2, marks);
		int count =pstmt.executeUpdate();
		System.out.println(count+" rows affected");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
