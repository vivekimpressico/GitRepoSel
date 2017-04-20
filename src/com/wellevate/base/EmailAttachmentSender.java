package com.wellevate.base;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailAttachmentSender {

	public void sendEmailWithAttachments(String host, String port,
            final String userName, final String password,final String STARTTLS,final String AUTH,String DEBUG,final String SOCKET_FACTORY, String toAddress,final 
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        
        properties.put("mail.smtp.auth", AUTH);
        properties.put("mail.smtp.starttls.enable", STARTTLS);
        properties.put("mail.smtp.debug", DEBUG);
 
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
        properties.put("mail.smtp.socketFactory.fallback", "false");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress("gunjan.agrawal@impressico.com"));
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress("anil.kumar@impressico.com"));
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress("arun.sahoo@impressico.com"));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates body part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }

	/**
	 * Test sending e-mail with attachments
	 */
	public void Send() {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "465";
		String mailFrom = "arun.sahoo@impressico.com";
		String password = "arun@123";

		// Sequrity

		String STARTTLS = "true";
		String AUTH = "true";
		String DEBUG = "true";
		String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// message info
		String mailTo = "shishir.verma@impressico.com";
		String subject = "Stage Env Execution Report For Patient specific discount Automation Script";
		String message;
		message = "Please follow the below steps to see the report :" + "1. Open the attached ZIP file."
				+ "2. Locate the index.html file." + "3. Open index.html file in any browser.";

		// attachments
		String[] attachFiles = new String[1];
		attachFiles[0] =System.getProperty("user.dir") +"\\html.zip";
				
		// attachFiles[0] =
		// "C:/Users/user/Desktop/EmersonAutomation/Wellevate/test-output/index.html";
		// attachFiles[2] = "e:/Test/Video.mp4";

		try {
			sendEmailWithAttachments(host, port, mailFrom, password,STARTTLS,AUTH, DEBUG,SOCKET_FACTORY,mailTo,subject, message, attachFiles);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}
	public void Send1() {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "arun.sahoo@impressico.com";
		String password = "arun@123";

		// Sequrity

		String STARTTLS = "true";
		String AUTH = "true";
		String DEBUG = "true";
		String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// message info
		String mailTo = "anil.kumar@impressico.com";
		String subject = "QA  Env Execution Report For Automation Scripts";
		String message;
		message = "May Be Attached File in Spam Please Check it..";
		String[] attachFiles = new String[0];
		
		try {
			sendEmailWithAttachments(host, port, mailFrom, password,STARTTLS,AUTH, DEBUG,SOCKET_FACTORY,mailTo,subject, message, attachFiles);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}
}