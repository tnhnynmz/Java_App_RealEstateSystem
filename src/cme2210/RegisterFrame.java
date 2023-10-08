package cme2210;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField nameField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(46, 79, 83, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(46, 107, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(46, 135, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(46, 163, 61, 16);
		contentPane.add(lblNewLabel_4);
		
		JComboBox <String>comboBox = new JComboBox();
		comboBox.setBounds(166, 240, 130, 27);
		contentPane.add(comboBox);
		comboBox.addItem("Owner");
		comboBox.addItem("Buyer");
			
	
		JLabel lblNewLabel_6 = new JLabel("Please enter empty fields");
		lblNewLabel_6.setVisible(false);
		lblNewLabel_6.setEnabled(false);
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(133, 344, 191, 16);
		contentPane.add(lblNewLabel_6);
		
		
		JButton signButton = new JButton("Sign up");
		signButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!usernameField.getText().isBlank()&&!passwordField.getText().isBlank()&&!nameField.getText().isBlank()&&!phoneField.getText().isBlank()
						&&!emailField.getText().isBlank()&&!addressField.getText().isBlank()) {
					Boolean correctFlag=true;
					if (comboBox.getSelectedItem().toString().equals("Owner")) {
						for (Entry<String,Owner> entry: Main.owners.entrySet()) {
							Owner temp = entry.getValue();
							if (temp.getUsername().equals(usernameField.getText())) {
								correctFlag=false;
								break;}
						}
						if (correctFlag) {
							String id = validID("owner");
							Owner o = new Owner(id, usernameField.getText(), passwordField.getText(), nameField.getText(),phoneField.getText(), emailField.getText(), addressField.getText());
							Main.owners.put(id, o);
							JOptionPane.showMessageDialog(contentPane, "Successful! ID: "+id);
						}
						else
							JOptionPane.showMessageDialog(contentPane, "Username is already in use.");
					}
					else if (comboBox.getSelectedItem().toString().equals("Buyer")) {
						for (Entry<String,Buyer> entry: Main.buyers.entrySet()) {
							Buyer temp = entry.getValue();
							if (temp.getUsername().equals(usernameField.getText())) {
								correctFlag=false;
								break;}
						}
						if (correctFlag) {
							String id = validID("buyer");
							Buyer b = new Buyer(id, usernameField.getText(), passwordField.getText(), nameField.getText(),phoneField.getText(), emailField.getText(), addressField.getText());
							Main.buyers.put(id, b);
							JOptionPane.showMessageDialog(contentPane, "Successful! ID: "+id);
						}
						else
							JOptionPane.showMessageDialog(contentPane, "Username is already in use.");
					}
				}
			}
		});
		signButton.setBounds(166, 314, 117, 29);
		contentPane.add(signButton);



		JLabel lblNewLabel_5 = new JLabel("Phone");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(46, 191, 61, 16);
		contentPane.add(lblNewLabel_5);


		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c=e.getKeyChar();
				if(!(Character.isLetter(c)||Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();

				}
			}
		});
		usernameField.setBounds(166, 74, 130, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(166, 102, 130, 26);
		contentPane.add(passwordField);

		emailField = new JTextField();
		emailField.setBounds(166, 130, 130, 26);
		contentPane.add(emailField);
		emailField.setColumns(10);

		addressField = new JTextField();
		addressField.setBounds(166, 158, 130, 26);
		contentPane.add(addressField);
		addressField.setColumns(10);

		phoneField = new JTextField();
		phoneField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();

				}

			}
		});
		phoneField.setBounds(166, 186, 130, 26);
		contentPane.add(phoneField);
		phoneField.setColumns(10);


		JLabel lblNewLabel_7 = new JLabel("User");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(46, 244, 61, 16);
		contentPane.add(lblNewLabel_7);

		
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(166, 44, 130, 26);
		contentPane.add(nameField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(46, 52, 83, 16);
		contentPane.add(lblNewLabel_1_1);
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("img//adobespark.png"));
		lblNewLabel_8.setBackground(new Color(0, 0, 0));
		lblNewLabel_8.setBounds(0, 0, 450, 379);
		contentPane.add(lblNewLabel_8);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login =new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(10, 18, 89, 23);
		contentPane.add(backButton);

		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	try {
					Main.writeUsers();
					Main.writeProperties();
					Main.writeSales();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        LoginFrame login = new LoginFrame();
		        login.setVisible(true);
		        dispose();
		    }
		});


	}
	public String validID(String user) {
		int tempNumber = 0;
		boolean flag = true;
		if (user.equals("owner")) {
			while (flag) {
				if (Main.owners.get("O"+String.valueOf(tempNumber)) != null)
					tempNumber++;
				else
					flag = false;
			}
			return "O"+String.valueOf(tempNumber);
		}
		if (user.equals("buyer")) {
			while (flag) {
				if (Main.buyers.get("B"+String.valueOf(tempNumber)) != null)
					tempNumber++;
				else
					flag = false;
			}
			return "O"+String.valueOf(tempNumber);
		}
		return null;
	}
	
}
