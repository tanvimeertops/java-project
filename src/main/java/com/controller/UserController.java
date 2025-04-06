package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bean.SignUp;
import com.dao.UserDao;
import com.service.Services;

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
				su.setUsertype(request.getParameter("usertype"));
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
				if(su.getUsertype().equalsIgnoreCase("user")) {
					HttpSession session=request.getSession();
					session.setAttribute("su", su);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else {
					HttpSession session=request.getSession();
					session.setAttribute("su", su);
					request.getRequestDispatcher("seller_index.jsp").forward(request, response);
				}
				
			}
		}else if(action.equalsIgnoreCase("change password")) {
			System.out.println("change password clicked");
			HttpSession session=request.getSession();
			SignUp su= (SignUp) session.getAttribute("su");
			if(su.getUsertype().equalsIgnoreCase("user")) {
				if(su.getPass().equals(request.getParameter("old_pass"))) {
					System.out.println("1st condition");
					if(request.getParameter("new_pass").equals(request.getParameter("confirm_new_pass"))) {
						System.out.println("2nd condition");
						UserDao.changePassword(su.getEmail(),request.getParameter("new_pass"));
						response.sendRedirect("logout.jsp");
					}else {
						System.out.println("3rd condition");
						session.setAttribute("msg", "New Password and Confirm new password are Different");
						request.getRequestDispatcher("change_password.jsp").forward(request, response);
					}
				}else {
					System.out.println("4th condition");
					session.setAttribute("msg", "Incorrect Old Password");
					request.getRequestDispatcher("change_password.jsp").forward(request, response);
				}
			}else {
				System.out.println("in seller change password");
				if(su.getPass().equals(request.getParameter("old_pass"))) {
					System.out.println("1st condition");
					if(request.getParameter("new_pass").equals(request.getParameter("confirm_new_pass"))) {
						System.out.println("2nd condition");
						UserDao.changePassword(su.getEmail(),request.getParameter("new_pass"));
						response.sendRedirect("logout.jsp");
					}else {
						System.out.println("3rd condition");
						session.setAttribute("msg", "New Password and Confirm new password are Different");
						request.getRequestDispatcher("seller_change_password.jsp").forward(request, response);
					}
				}else {
					System.out.println("4th condition");
					session.setAttribute("msg", "Incorrect Old Password");
					request.getRequestDispatcher("seller_change_password.jsp").forward(request, response);
				}
			}
			
			
		}else if (action.equalsIgnoreCase("update profile")) {
			System.out.println("update profile clicked");
			SignUp su=new SignUp();
			if(su.getUsertype().equalsIgnoreCase("user")) {
				su.setFname(request.getParameter("fname"));
				su.setLname(request.getParameter("lname"));
				su.setEmail(request.getParameter("email"));
				su.setMobile(request.getParameter("mobile"));
				su.setUsertype(request.getParameter("usertype"));
				HttpSession session=request.getSession();
				session.setAttribute("su", su);
//				if (su.getUsertype().equals("user")) {
//					
//				}
				request.getRequestDispatcher("update_profile.jsp").forward(request, response);
			}else {
				su.setFname(request.getParameter("fname"));
				su.setLname(request.getParameter("lname"));
				su.setEmail(request.getParameter("email"));
				su.setMobile(request.getParameter("mobile"));
				su.setUsertype(request.getParameter("usertype"));
				HttpSession session=request.getSession();
				session.setAttribute("su", su);
//				if (su.getUsertype().equals("user")) {
//					
//				}
				request.getRequestDispatcher("seller_update_profile.jsp").forward(request, response);
			}
			
		
		}else if (action.equalsIgnoreCase("Send Otp")) {
			String email=request.getParameter("email");
			boolean flag= UserDao.checkEmail(email);
			
			if (flag==true) {
				int min = 1000;
				int max = 9999;
				int otp = min + (int)(Math.random() * ((max - min) + 1));
				System.out.println("Random Integer from 1000 to 9999: " + otp);
				Services.sendMail(email, otp);
				request.setAttribute("email", email);
				request.setAttribute("otp", otp);
				request.getRequestDispatcher("otp.jsp").forward(request, response);
				
			}else {
				request.setAttribute("msg", "Email Not Registered");
				request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
			}
		}else if (action.equalsIgnoreCase("verify otp")) {
			String email=request.getParameter("email");
			int otp1=Integer.parseInt(request.getParameter("otp1"));
			int otp2= Integer.parseInt(request.getParameter("otp"));
			if(otp1==otp2) {
				request.setAttribute("email", email);
				request.getRequestDispatcher("new_password.jsp").forward(request, response);
				
			}else {
				request.setAttribute("email", email);
				request.setAttribute("otp", otp2);
				request.setAttribute("msg", "Invalid OTP");
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}
		}
		
		
	}

}
