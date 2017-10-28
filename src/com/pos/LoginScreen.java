package com.pos;

/*
 *	Group Charlie 
 * 	CMSC 495
 * 	University of Maryland, University College
 * 
 * 	Description: This Class starts the program with a login screen, to then transfer to the POS program when login is successful
 */

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.xml.bind.DatatypeConverter;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen {

	private JFrame frmCmscPos;
	private JTextField UserIDField;
	private JPasswordField passwordField;
	private JLabel lblErrorbox;
	

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					new LoginScreen();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}});
		
	}
	
	public LoginScreen() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCmscPos = new JFrame();
		frmCmscPos.getContentPane().setBackground(Color.WHITE);
		frmCmscPos.setTitle("CMSC 451 POS");
		frmCmscPos.setBounds(100, 100, 683, 498);
		frmCmscPos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCmscPos.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frmCmscPos.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(237, 208, 52, 14);
		panel.add(lblUserId);
		
		UserIDField = new JTextField();
		UserIDField.setBounds(316, 198, 166, 27);
		panel.add(UserIDField);
		UserIDField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(237, 246, 52, 14);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(316, 236, 166, 27);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				checkLogin();
				
			}

		});
		btnLogin.setBounds(393, 313, 89, 23);
		panel.add(btnLogin);
		
		lblErrorbox = new JLabel("");
		lblErrorbox.setEnabled(false);
		lblErrorbox.setBounds(393, 347, 89, 27);
		panel.add(lblErrorbox);
		frmCmscPos.setVisible(true);
	}

private void checkLogin()
{
		Connection con = openSQL();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String hash = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(passwordField.getText().toString().getBytes("UTF-8")));
			String sq= "SELECT UserId from USERS WHERE [LoginID] = ? AND [Password] = ?"; /*+ UserIDField.getText().toString() + " AND [Password] = " + passwordField.getText().toString() + ")";*/
			PreparedStatement ps = con.prepareStatement(sq);
			ps.setString(1, UserIDField.getText().toString());
			ps.setString(2, hash);
			ResultSet r = ps.executeQuery();
			if(r.next())
			{
				lblErrorbox.setText("");
				lblErrorbox.setEnabled(false);
				int id = r.getInt("UserID");
				Login(id);
			}
			else 
			{
				lblErrorbox.setText("INVALID LOGIN");
				lblErrorbox.setEnabled(true);
			}
			
			
		} catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
}

private void Login(int id) 
{
	// TODO Login to POS with provided ID
	
}

private Connection openSQL()
{
	try {
	String server = "jdbc:sqlserver://localhost\\POS;databaseName=POS;user=sa;password=cmsc495;";
	return (DriverManager.getConnection(server));}
	catch(SQLException e) 
	{
		e.printStackTrace();
		return null;
	}
}

}
