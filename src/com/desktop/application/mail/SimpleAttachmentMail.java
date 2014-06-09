package com.desktop.application.mail;

import java.util.ArrayList;
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

public class SimpleAttachmentMail {
	private String userName = null;
	private char[] password = null;
	private String host = null;
	private String portNumber = null;
	private String from = null;
	private String subject = null;
	private String message = null;
	private String proxyAddress = null;
	private String proxyPort = null;
	private String [] toAddress;
	private ArrayList<String> attachments;
	
	public SimpleAttachmentMail(String userName, char[] password, String host,
			String portNumber, String to, String from, String subject,
			String message,ArrayList<String> attachments) {
		super();
		this.userName = userName;
		this.password = password;
		this.host = host;
		this.portNumber = portNumber;
		this.from = from;
		this.subject = subject;
		this.message = message;
		this.toAddress = to.split(";");
		this.attachments = attachments;
	}	
	
	
	public SimpleAttachmentMail(String userName, char[] password, String host,
			String portNumber, String to, String from, String subject,
			String message,ArrayList<String> attachments, String proxyAddress, String proxyPort) {
		super();
		this.userName = userName;
		this.password = password;
		this.host = host;
		this.portNumber = portNumber;
		this.from = from;
		this.subject = subject;
		this.message = message;
		this.proxyAddress = proxyAddress;
		this.proxyPort = proxyPort;
		this.toAddress = to.split(";");
		this.attachments = attachments;
	}
	
	public boolean sendMail(){
		//Properties file 
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable","true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", portNumber);
				if(proxyAddress != null & proxyPort != null){
					props.put("mail.smtp.socks.host", proxyAddress);
					props.put("mail.smtp.socks.port", proxyPort);
				}
				
				
				// Creating a Session
				
				Session session = Session.getInstance(props, 
						new Authenticator(){
							protected PasswordAuthentication getPasswordAuthentication(){
								return new PasswordAuthentication(userName, String.valueOf(password));
							}
				});
				
				//creating mail body
				try {
					Message mailBody = new MimeMessage(session);
					mailBody.setFrom(new InternetAddress(from));
					for(int i=0;i<toAddress.length;i++){
						mailBody.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress[i]));
					}
					mailBody.setSubject(subject);
					
					//message body
					BodyPart body = new MimeBodyPart();
					body.setText(message);
					
					//adding message to multipart
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(body);
					
					//adding attachments
					DataSource source;
					for(String fileName : attachments){
						source = new FileDataSource(fileName);
						body = new MimeBodyPart();
						body.setDataHandler(new DataHandler(source));
						body.setFileName(fileName);
						multipart.addBodyPart(body);
					}
					
					
					// setting content 
					mailBody.setContent(multipart);
					
					// sending the mail
					Transport.send(mailBody);
					
					return true;
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
				
	}
	
	
	
	
}
