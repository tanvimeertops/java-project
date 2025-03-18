package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ProjectUtil {
public static Connection createConnection() {
	Connection conn=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adv_java_2867", "root", "");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return conn;
	
}
}
