package com.innverse.elearn.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MailFormatData {
    
     public static String contactUsRequestMail = "<!DOCTYPE html>\n" +
     "<html>\n" +
     "    <body>\n" +
     "        <div class=\"main\" style=\"margin-left: auto;margin-right: auto;background-color: #cccccc;height: 95%;position: relative;padding-top: 2%;padding-bottom: 2%;\">\n" +
     "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Dear #USERNAME#,</span>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">You're receiving this email because you have send your Query from ELMS Contact US page. We have Registered your query in our database.</p>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">We will reply about your query as soon as possible.</p><br/>\n" +
     "                <p style=\"font-size: 13px;\"> Your request message is : #MESSAGE#.</p>\n" +
     "                <p style=\"font-size: 13px;\">Please do not reply to this e-mail.</p><br/>\n" +
     "                <p style=\"font-size: 13px;\">Thanks<br/>\n" +
     "                ELMS Team<br/>\n" +
     "			  </div>\n" +
     "        </div>\n" +
     "    </body>\n" +
     "</html>\n"; //this will be the text of the email
     
     
     public static String contactUSResponseMail = "<!DOCTYPE html>\n" +
     "<html>\n" +
     "    <body>\n" +
     "        <div class=\"main\" style=\"margin-left: auto;margin-right: auto;background-color: #cccccc;height: 95%;position: relative;padding-top: 2%;padding-bottom: 2%;\">\n" +
     "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Dear #USERNAME#,</span>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">You're receiving this email about your Query from ELMS.</p>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">Your mobile number is : #MOBILE# .</p><br/>\n" +
     "				  <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">Your message was  : #MESSAGE#.</p><br/>\n" +
     "				 <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">Suggestion about your query is : #SUGGESTION#.</p><br/>\n" +
     "                <p style=\"font-size: 13px;\"> This is a system generated message.</p>\n" +
     "                <p style=\"font-size: 13px;\">Please do not reply to this e-mail.</p><br/>\n" +
     "                <p style=\"font-size: 13px;\">Thanks<br/>\n" +
     "                ELMS Team<br/>\n" +
     "</div>\n" +
     "    </body>\n" +
     "</html>\n"; //this will be the text of the email
     

    	public static String getContactUsRequestMailData(String userFullName, String message){
       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss z");
       		
       		String regExpressionUsername = "#USERNAME#";
       		String regExpressionMessage = "#MESSAGE#";

       		contactUsRequestMail = contactUsRequestMail.replaceAll(regExpressionUsername, userFullName);
       		contactUsRequestMail = contactUsRequestMail.replaceAll(regExpressionMessage, message);

       		return contactUsRequestMail;
       	}
    	
    	public static String getContactUsResponseMailData(String userFullName, String message, String suggestion, String mobile){
       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss z");
       		
       		String regExpressionUsername = "#USERNAME#";
       		String regExpressionMessage = "#MESSAGE#";
       		String regExpressionSuggestion = "#SUGGESTION#";
       		String regExpressionMobile = "#MOBILE#";

       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionUsername, userFullName);
       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionMessage, message);
       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionSuggestion, suggestion);
       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionMobile, mobile);

       		return contactUSResponseMail;
       	}
    	
}