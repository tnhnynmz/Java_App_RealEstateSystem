package cme2210;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuyersFrame extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField userNameField;
	private JTextField passwordField;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyersFrame frame = new BuyersFrame();
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
	public BuyersFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 789, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		IDField = new JTextField();
		IDField.setEditable(false);
		IDField.setBounds(82, 8, 86, 20);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(10, 36, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		userNameField = new JTextField();
		userNameField.setBounds(82, 33, 86, 20);
		contentPane.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JTextField();
		passwordField.setBounds(82, 58, 86, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(10, 86, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		nameField = new JTextField();
		nameField.setBounds(82, 83, 86, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Phone");
		lblNewLabel_4.setBounds(10, 111, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		phoneField = new JTextField();
		phoneField.setBounds(82, 108, 86, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(10, 136, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		emailField = new JTextField();
		emailField.setBounds(82, 133, 86, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Address");
		lblNewLabel_6.setBounds(10, 161, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		addressField = new JTextField();
		addressField.setBounds(82, 158, 86, 40);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(199, 11, 564, 267);
		contentPane.add(scrollPane);
		
		String[] columns = {"ID","UserName","Password","Name","Phone","Email","Address"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		table = new JTable(tableModel);
		for (Entry<String, Buyer> entry : Main.buyers.entrySet()) {
			Buyer b = entry.getValue();
			String[] data = {b.getID(), b.getUsername(), b.getPassword(), b.getName(), b.getPhone(), b.getEmail(), b.getAddress()};
			tableModel.addRow(data);
		}
		scrollPane.setViewportView(table);
		
		JButton addButton = new JButton("ADD");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!emailField.getText().isBlank() &&!userNameField.getText().isBlank()&&!passwordField.getText().isBlank()&&!nameField.getText().isBlank()
						&&!phoneField.getText().isBlank()&&!addressField.getText().isBlank()){
					Boolean correctFlag = true;
					for(int i = 0;i<table.getModel().getRowCount();i++)
					{
					    if (tableModel.getValueAt(i, 1).toString().equals(userNameField.getText())) {
					    	JOptionPane.showMessageDialog(contentPane, "This Username is already in use.");
					    	correctFlag = false;
					    	break;
					    }
					}
					if (correctFlag) {
						Buyer b1 = new Buyer(validID(),userNameField.getText(),passwordField.getText(),nameField.getText(),phoneField.getText()
								,emailField.getText(), addressField.getText());
						Main.buyers.put(b1.getID(), b1);
						String[] data2 = {b1.getID(), b1.getUsername(), b1.getPassword()
							,b1.getName(), b1.getPhone(),b1.getEmail()
							,b1.getAddress()};
						tableModel.addRow(data2);
						JOptionPane.showMessageDialog(contentPane, "Buyer has been added with ID: "+b1.getID());
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Fill empty boxes.");}
			}
		});
		addButton.setBounds(10, 209, 76, 23);
		contentPane.add(addButton);
		
		JButton editButton = new JButton("EDIT");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.buyers.get(IDField.getText())!= null) {
					boolean correctFlag = true;
					for(int i = 0;i<table.getModel().getRowCount();i++)
					{
					    if (tableModel.getValueAt(i, 1).toString().equals(userNameField.getText())) {
					    	if (tableModel.getValueAt(i, 0).toString().equals(IDField.getText()))
					    		break;
					    	else {
					    		JOptionPane.showMessageDialog(contentPane, "This Username is already in use.");
						    	correctFlag = false;
						    	break;
					    	}
					    }
					}
					if (correctFlag) {
						Main.buyers.get(IDField.getText()).setUsername(userNameField.getText());
						Main.buyers.get(IDField.getText()).setPassword(passwordField.getText());
						Main.buyers.get(IDField.getText()).setName(nameField.getText());
						Main.buyers.get(IDField.getText()).setPhone(phoneField.getText());
						Main.buyers.get(IDField.getText()).setEmail(emailField.getText());
						Main.buyers.get(IDField.getText()).setAddress(addressField.getText());
						tableModel.setRowCount(0);
						for (Entry<String, Buyer> entry : Main.buyers.entrySet()) {
							Buyer b = entry.getValue();
							String[] data = {b.getID(), b.getUsername(), b.getPassword(), b.getName(), b.getPhone(), b.getEmail(), b.getAddress()};
							tableModel.addRow(data);
						}
						JOptionPane.showMessageDialog(contentPane, "Successful.");
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");
			}
		});
		editButton.setBounds(92, 209, 76, 23);
		contentPane.add(editButton);
		
		JButton removeButton = new JButton("REMOVE");
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.buyers.get(IDField.getText())!= null) {
					Main.buyers.remove(IDField.getText());
					ArrayList<String> salesToRemove = new ArrayList<>();
					for (Entry<String,Sale> s: Main.sales.entrySet()) {
						if (s.getValue().getSaleToBuyer().getID().equals(IDField.getText())){
							salesToRemove.add(s.getValue().getID());
						}
					}
					for (String s: salesToRemove) 
						Main.sales.remove(s);
					tableModel.setRowCount(0);
					for (Entry<String, Buyer> entry : Main.buyers.entrySet()) {
						Buyer b = entry.getValue();
						String[] data = {b.getID(), b.getUsername(), b.getPassword(), b.getName(), b.getPhone(), b.getEmail(), b.getAddress()};
						tableModel.addRow(data);
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");
			}
		});
		removeButton.setBounds(10, 255, 158, 23);
		contentPane.add(removeButton);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String value =table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
	            IDField.setText(Main.buyers.get(value).getID());
	            nameField.setText(Main.buyers.get(value).getName());
	            phoneField.setText(Main.buyers.get(value).getPhone());
	            emailField.setText(Main.buyers.get(value).getEmail());
	            addressField.setText(Main.buyers.get(value).getAddress());
	            userNameField.setText(Main.buyers.get(value).getUsername());
	            passwordField.setText(Main.buyers.get(value).getPassword());
			}
	    });
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        MainFrame mf = new MainFrame();
		        mf.setVisible(true);
		        dispose();
		    }
		});
	}
	public String validID() {
		int tempNumber = 0;
		boolean flag = true;
		while (flag) {
			if (Main.buyers.get("B"+String.valueOf(tempNumber)) != null)
				tempNumber++;
			else
				flag = false;
		}
		return "B"+String.valueOf(tempNumber);
	}
}
