package com.innverse.elearn.mail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.innverse.elearn.model.Invitation;

public class Testmail {

	private static Transport transport;

	public boolean sendMail(String friendEmail, String url, String id) {
		try {
			Logger logger = LoggerFactory.getLogger(Testmail.class);
			logger.error(friendEmail);
			logger.error("Searching for dispatcher.................................");

			final String to = friendEmail; // "smrgrover2@gmail.com";//"sks2508@gmail.com";//change
																			// accordingly
			logger.error("Email set..............!!!!!!!!");
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			logger.debug(props.getProperty("mail.smtp.host",
					"mail.smtp.socketFactory"));
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"smrgrover2@gmail.com", "Sameer_grover2000");// change
																					// accordingly
						}
					});

			// compose message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("manish@innverse.com"));// change
																		// accordingly.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("ElearnWebapp | Invite Friend");
			message.setText("Testing.......Link");
			message.setText("Invitiation Link");
			String urlString  = url + "/" + id; 
			message.setText(urlString);

			// send message
			transport.send(message);
			System.out.println("message sent successfully");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}