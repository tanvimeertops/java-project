package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.SignUp;
import com.dao.UserDao;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		
		if(action.equalsIgnoreCase("sign up")){
			boolean flag= UserDao.checkEmail(request.getParameter("email"));
				
			if(flag==false) {
				SignUp su=new SignUp();
				
				su.setFname(request.getParameter("fname"));
				su.setLname(request.getParameter("lname"));
				su.setEmail(request.getParameter("email"));
				su.setMobile(request.getParameter("mobile"));
				su.setPass(request.getParameter("pass"));
				su.setCpass(request.getParameter("cpass"));
				UserDao.signup(su);
				request.setAttribute("msg", "Succesfully Sign Up");
				request.getRequestDispatcher("signin.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "Email Address Already Registered");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			
			
		}else if (action.equalsIgnoreCase("sign in")) {
			System.out.println("Sign in cliked");
			SignUp su= UserDao.signin(request.getParameter("email"),request.getParameter("pass"));
			if(su==null) {
				request.setAttribute("msg", "Incorrect Username or Password");
				request.getRequestDispatcher("signin.jsp").forward(request, response);
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("su", su);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else if(action.equalsIgnoreCase("change password")) {
			System.out.println("change password clicked");
			HttpSession session=request.getSession();
			SignUp su= (SignUp) session.getAttribute("su");
			if(su.getPass().equals(request.getParameter("old_pass"))) {
				if(request.getParameter("new_pass").equals(request.getParameter("confirm_new_pass"))) {
					UserDao.changePassword(su.getEmail(),request.getParameter("new_pass"));
					response.sendRedirect("signin.jsp");
				}else {
					session.setAttribute("msg", "New Password and Confirm new password are Different");
					request.getRequestDispatcher("change_password.jsp").forward(request, response);
				}
			}else {
				session.setAttribute("msg", "Incorrect Old Password");
				request.getRequestDispatcher("change_password.jsp").forward(request, response);
			}
			
		}
		
		
	}

}
