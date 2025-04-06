package com.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Services {

	public static void sendMail(String emailid,int otp){
		final String username  = "tanvimeer.tops@gmail.com";
		final String password   = "bwcw tlbg fvhx jncp";
		
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.debug", "true");
	
//		Session session = null;
//		if (username != null && password != null)
//		{
//			props.put("mail.smtp.auth", "true");
//			session = Session.getInstance(props,
//			new MyPasswordAuthenticator(username, password));
//		}
//		else
//		{
//			session = Session.getDefaultInstance(props, null);
//		}

 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailid));//to mail address.
			message.setSubject("OTP For Forgot Password");
			message.setText("Hello User, Your OTP For Forgot Password Is : "+otp);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}		
	}
}
