package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bean.Contact;
import com.util.ProjectUtil;

public class ContactDao {
public static void insertContact(Contact c) {
	Connection conn=ProjectUtil.createConnection();
	String sql="insert into contact(fname,lname,email,mobile,address) values(?,?,?,?,?)";
	
	try {
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, c.getFname());
		pst.setString(2, c.getLname());
		pst.setString(3, c.getEmail());
		pst.setString(4, c.getMobile());
		pst.setString(5, c.getAddress());
		pst.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
