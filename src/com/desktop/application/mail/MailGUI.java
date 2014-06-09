package com.desktop.application.mail;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;


public class MailGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField pwdPassword;
	private JTextField txtHostAddress;
	private JTextField txtPortNumber;
	private JLabel lblMailDetails;
	private JTextField txtTo;
	private JTextField txtFrom;
	private JTextField txtSubject;
	private JTextField txtProxyAddress;
	private JTextField txtPortNumber_1;
	private ArrayList<String> attachments = new ArrayList<String>();
	private JTextArea textArea_1 = new JTextArea();
	/**
	 * @wbp.nonvisual location=253,24
	 */
	private JButton btnAttachments;
	private JLabel lblMessage;
	private JButton btnRemove;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailGUI frame = new MailGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MailGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 786);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeskstopMailApplication = new JLabel("Desktop Mail Application");
		lblDeskstopMailApplication.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDeskstopMailApplication.setForeground(new Color(0, 0, 0));
		lblDeskstopMailApplication.setBounds(275, 13, 218, 23);
		contentPane.add(lblDeskstopMailApplication);
		
		JLabel lblNewLabel = new JLabel("Host Details");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 32, 134, 23);
		contentPane.add(lblNewLabel);
		
		txtUserName = new JTextField();
		txtUserName.setText("Gmail User Name");
		txtUserName.setBounds(27, 68, 172, 22);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("Password");
		pwdPassword.setBounds(227, 68, 156, 22);
		contentPane.add(pwdPassword);
		
		txtHostAddress = new JTextField();
		txtHostAddress.setText("smtp.gmail.com");
		txtHostAddress.setBounds(413, 68, 182, 22);
		contentPane.add(txtHostAddress);
		txtHostAddress.setColumns(10);
		
		txtPortNumber = new JTextField();
		txtPortNumber.setText("587");
		txtPortNumber.setBounds(625, 68, 59, 22);
		contentPane.add(txtPortNumber);
		txtPortNumber.setColumns(10);
		
		lblMailDetails = new JLabel("Mail Details");
		lblMailDetails.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblMailDetails.setBounds(27, 132, 116, 16);
		contentPane.add(lblMailDetails);
		
		txtTo = new JTextField();
		txtTo.setText("To(seperate multiple address with \";\")");
		txtTo.setBounds(27, 178, 289, 22);
		contentPane.add(txtTo);
		txtTo.setColumns(10);
		
		txtFrom = new JTextField();
		txtFrom.setText("From");
		txtFrom.setBounds(328, 178, 302, 22);
		contentPane.add(txtFrom);
		txtFrom.setColumns(10);
		
		final TextArea textArea = new TextArea();
		textArea.setBounds(27, 276, 603, 201);
		contentPane.add(textArea);
		
		textArea_1.setBounds(121, 504, 509, 148);
		contentPane.add(textArea_1);
		
		txtSubject = new JTextField();
		txtSubject.setText("Subject");
		txtSubject.setBounds(27, 223, 603, 22);
		contentPane.add(txtSubject);
		txtSubject.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(attachments.size() == 0){
					SimpleMail newMail;
					if(txtProxyAddress.isVisible() & txtPortNumber_1.isVisible()){
						newMail = new SimpleMail(txtUserName.getText(), pwdPassword.getPassword(), txtHostAddress.getText(), txtPortNumber.getText(), txtTo.getText(), txtFrom.getText(), txtSubject.getText(), textArea.getText(),txtProxyAddress.getText(),txtPortNumber_1.getText());
					}else{
						newMail = new SimpleMail(txtUserName.getText(), pwdPassword.getPassword(), txtHostAddress.getText(), txtPortNumber.getText(), txtTo.getText(), txtFrom.getText(), txtSubject.getText(), textArea.getText());
					}
					if(newMail.sendMail()){
						JOptionPane.showMessageDialog(null, "Email is sent !!");			
					}else{
						JOptionPane.showMessageDialog(null, "Failed to Send the mail !!");
					}
				}else{
					SimpleAttachmentMail newAttchAttachmentMail;
					if(txtProxyAddress.isVisible() & txtPortNumber_1.isVisible()){
						newAttchAttachmentMail = new SimpleAttachmentMail(txtUserName.getText(), pwdPassword.getPassword(), txtHostAddress.getText(), txtPortNumber.getText(), txtTo.getText(), txtFrom.getText(), txtSubject.getText(), textArea.getText(), attachments, txtProxyAddress.getText(),txtPortNumber_1.getText());
					}else{
						newAttchAttachmentMail = new SimpleAttachmentMail(txtUserName.getText(), pwdPassword.getPassword(), txtHostAddress.getText(), txtPortNumber.getText(), txtTo.getText(), txtFrom.getText(), txtSubject.getText(), textArea.getText(), attachments);
					}
					if(newAttchAttachmentMail.sendMail()){
						JOptionPane.showMessageDialog(null, "Email is sent !!");			
					}else{
						JOptionPane.showMessageDialog(null, "Failed to Send the mail !!");
					}
				}
			}
		});
		btnSend.setBounds(12, 627, 97, 25);
		contentPane.add(btnSend);
		
		JCheckBox chckbxProxy = new JCheckBox("Proxy ?");
		chckbxProxy.setBounds(27, 692, 113, 25);
		contentPane.add(chckbxProxy);
		chckbxProxy.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent evnt) {
				// TODO Auto-generated method stub
				if(evnt.getStateChange() == ItemEvent.SELECTED){
					txtProxyAddress.setVisible(true);
					txtPortNumber_1.setVisible(true);
				}else{
					txtProxyAddress.setVisible(false);
					txtPortNumber_1.setVisible(false);
				}
			}
		});
		
		txtProxyAddress = new JTextField();
		txtProxyAddress.setVisible(false);
		txtProxyAddress.setText("proxy address");
		txtProxyAddress.setBounds(171, 693, 172, 22);
		contentPane.add(txtProxyAddress);
		txtProxyAddress.setColumns(10);
		
		txtPortNumber_1 = new JTextField();
		txtPortNumber_1.setText("Port number");
		txtPortNumber_1.setVisible(false);
		txtPortNumber_1.setBounds(401, 693, 116, 22);
		contentPane.add(txtPortNumber_1);
		txtPortNumber_1.setColumns(10);
		
		btnAttachments = new JButton("Attach");
		btnAttachments.setBounds(12, 503, 97, 25);
		contentPane.add(btnAttachments);
		
		lblMessage = new JLabel("Message");
		lblMessage.setBounds(27, 258, 56, 16);
		contentPane.add(lblMessage);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(12, 538, 97, 25);
		contentPane.add(btnRemove);
		
		
		btnAttachments.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openDialog();
			}
		});	
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeAttachments();
			}
		});
		
		
		
	}
	
	private void openDialog(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showDialog(this, "choose an attchment");
		File file = fileChooser.getSelectedFile();
		try {
			attachments.add(file.getAbsolutePath());
			textArea_1.append(file.getAbsolutePath()+"\n");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void removeAttachments(){
		attachments.removeAll(attachments);
		textArea_1.setText("");
	}
}
