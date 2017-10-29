package com.pos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.xml.bind.DatatypeConverter;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminScreen
{
	
	JFrame AdminScreen = new JFrame();
	private JTextField ID_Field;
	private JPasswordField password1;
	private JTextField FirstName;
	private JTextField LastName;
	private JPasswordField Password2;
	private JTextPane textPane;

	public AdminScreen()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					initialize();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}});
		
	}
		
	private void initialize() {	
		AdminScreen.getContentPane().setBackground(Color.WHITE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		AdminScreen.getContentPane().add(tabbedPane);
		
		JPanel adPanel = new JPanel();
		tabbedPane.addTab("Add User", null, adPanel, null);
		adPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login ID:");
		lblNewLabel.setBounds(10, 38, 74, 14);
		adPanel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 64, 74, 14);
		adPanel.add(lblPassword);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 125, 74, 14);
		adPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 150, 74, 14);
		adPanel.add(lblLastName);
		
		ID_Field = new JTextField();
		ID_Field.setBounds(117, 35, 104, 20);
		adPanel.add(ID_Field);
		ID_Field.setColumns(10);
		
		password1 = new JPasswordField();
		password1.setBounds(117, 61, 104, 20);
		adPanel.add(password1);
		
		FirstName = new JTextField();
		FirstName.setBounds(117, 122, 104, 20);
		adPanel.add(FirstName);
		FirstName.setColumns(10);
		
		LastName = new JTextField();
		LastName.setBounds(117, 147, 104, 20);
		adPanel.add(LastName);
		LastName.setColumns(10);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckData();
			}
		});
		btnAddUser.setBounds(132, 177, 89, 23);
		adPanel.add(btnAddUser);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 211, 211, 92);
		adPanel.add(textPane);
		
		JLabel lblReenterPassword = new JLabel("Re-Enter Password:");
		lblReenterPassword.setBounds(10, 95, 97, 14);
		adPanel.add(lblReenterPassword);
		
		Password2 = new JPasswordField();
		Password2.setBounds(117, 92, 104, 20);
		adPanel.add(Password2);
		
		JLabel lblRoomForAdding = new JLabel("Room for adding Permission options");
		lblRoomForAdding.setBounds(540, 111, 223, 89);
		adPanel.add(lblRoomForAdding);
		
		JPanel Inventory = new JPanel();
		tabbedPane.addTab("AddItems", null, Inventory, null);
		Inventory.setLayout(null);
		
		JLabel lblThisIsWhere = new JLabel("This is where you would add items into the system");
		lblThisIsWhere.setBounds(283, 205, 419, 120);
		Inventory.add(lblThisIsWhere);
		AdminScreen.setTitle("CMSC 451 POS  -- Admin Screen");
		AdminScreen.setBounds(100, 100, 915, 663);
		AdminScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AdminScreen.setVisible(true);
		
	}
	protected void CheckData() 
	{
		String p1 = password1.getText().toString();
		String p2 = Password2.getText().toString();
		if(p1.equals(p2))
		{
			//TODO: Add more filters for id/first&LastName sanity check
			Connection con = new SQLConnection().openSQL();
			try {
				String passhash = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(password1.getText().toString().getBytes("UTF-8")));
				String fname = FirstName.getText().toString();
				String lname = LastName.getText().toString();
				String id = ID_Field.getText().toString();
				String insert = "INSERT INTO USERS ([LoginID],[Password],[FirstName],[LastName]) VALUES (?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(insert);
				ps.setString(1, id);
				ps.setString(2, passhash);
				ps.setString(3,fname);
				ps.setString(4,lname);
				ps.execute();
				con.close();
				textPane.setText(fname + " " + lname + " Has been added successfully!");
			}
			catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			textPane.setText("Passwords do not match!");
		}
		
	}
	
}
