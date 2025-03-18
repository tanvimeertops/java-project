package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Contact;
import com.dao.ContactDao;

/**
 * Servlet implementation class ContactController
 */
@WebServlet("/ContactController")
public class ContactController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action= request.getParameter("action");
		if(action.equalsIgnoreCase("submit")) {
			Contact c=new Contact();
			c.setFname(request.getParameter("fname"));
			System.out.println(request.getParameter("fname"));
			c.setLname(request.getParameter("lname")); 
			c.setEmail(request.getParameter("email")); 
			c.setMobile(request.getParameter("mobile")); 
			c.setAddress(request.getParameter("address")); 
			ContactDao.insertContact(c);
			response.sendRedirect("contact.jsp");
		}
		
	}

}
