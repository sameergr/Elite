package com.innverse.elearn.mail;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class SendMailAPI {
	protected String smtpHost = "smtp.gmail.com"; // replace this with a valid host
	protected int smtpPort = 465; // replace this with a valid port

	protected String sender = "smrgrover2@gmail.com"; // replace this with a valid sender
											// email address

	protected Properties props = new Properties();
	protected Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("smrgrover2@gmail.com",
							"Sameer_grover2000");// change accordingly
				}
			});
     
	 public SendMailAPI() {
		// TODO Auto-generated constructor stub
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.socketFactory.port", "465");
		 props.put("mail.smtp.socketFactory.class",
		 "javax.net.ssl.SSLSocketFactory");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.port", "465");
		 
	}
	 
}
