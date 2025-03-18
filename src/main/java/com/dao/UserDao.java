package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.SignUp;
import com.util.ProjectUtil;

public class UserDao {
	public static void signup(SignUp su) {
		
		Connection conn=ProjectUtil.createConnection();
		String sql="insert into signup (fname,lname,email,mobile,pass,cpass) values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,su.getFname() );
			pst.setString(2,su.getLname() );
			pst.setString(3,su.getEmail() );
			pst.setString(4,su.getMobile() );
			pst.setString(5,su.getPass() );
			pst.setString(6,su.getCpass() );
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static boolean checkEmail(String email) {
		boolean flag=false;
		
		Connection conn=ProjectUtil.createConnection();
		String sql="select * from signup where email=?";
		
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
	}
public static SignUp signin(String email,String pass) {
		
	SignUp su=null;
		Connection conn=ProjectUtil.createConnection();
		String sql="select * from signup where email=? and pass=?";
		
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,email );
			pst.setString(2,pass );
			ResultSet resultSet =pst.executeQuery();
			
			if(resultSet.next()) {
				su=new SignUp();
				su.setId(resultSet.getInt("id"));
				su.setFname(resultSet.getString("fname"));
				su.setLname(resultSet.getString("lname"));
				su.setEmail(resultSet.getString("email"));
				su.setMobile(resultSet.getString("mobile"));
				su.setPass(resultSet.getString("pass"));
				su.setCpass(resultSet.getString("cpass"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return su;
	}


public static void changePassword(String email, String new_password) {
	Connection conn=ProjectUtil.createConnection();
	
	String sql="update signup set pass=? where email=?";
	
	try {
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, new_password);
		pst.setString(2, email);
		pst.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}


}
