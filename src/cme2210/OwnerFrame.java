package cme2210;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;

public class OwnerFrame extends JFrame {
	private JPanel contentPane, contentPane2;
	private JTextField ownerIDField;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTable table, table2;
	private JTextField usernameField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerFrame frame = new OwnerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public OwnerFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 803, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Owner ID");
		lblNewLabel.setBounds(10, 27, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 105, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setBounds(10, 161, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 133, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 189, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		ownerIDField = new JTextField();
		ownerIDField.setEditable(false);
		ownerIDField.setBounds(85, 24, 86, 20);
		contentPane.add(ownerIDField);
		ownerIDField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(85, 102, 86, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(85, 158, 86, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(85, 130, 86, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setBounds(85, 186, 86, 56);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(202, 27, 575, 309);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		String[] columns = {"ID","Usernam","Password","Name","Phone","Email","Address"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		table = new JTable(tableModel);
		for (Entry<String, Owner> entry : Main.owners.entrySet()) {
			Owner o = entry.getValue();
			String[] data = {o.getID(),o.getUsername(),o.getPassword(), o.getName(), o.getPhone(), o.getEmail(), o.getAddress()};
			tableModel.addRow(data);
		}
		scrollPane.setViewportView(table);
		JButton editButton = new JButton("EDIT");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.owners.get(ownerIDField.getText())!= null) {
					boolean correctFlag = true;
					for(int i = 0;i<table.getModel().getRowCount();i++)
					{
						if (tableModel.getValueAt(i, 1).toString().equals(usernameField.getText())) {
					    	if (tableModel.getValueAt(i, 0).toString().equals(ownerIDField.getText()))
					    		break;
					    	else {
					    		JOptionPane.showMessageDialog(contentPane, "This Username is already in use.");
						    	correctFlag = false;
						    	break;
					    	}
					    }
					}
					if (correctFlag) {
						Main.owners.get(ownerIDField.getText()).setName(nameField.getText());
						Main.owners.get(ownerIDField.getText()).setPhone(phoneField.getText());
						Main.owners.get(ownerIDField.getText()).setEmail(emailField.getText());
						Main.owners.get(ownerIDField.getText()).setAddress(addressField.getText());
						Main.owners.get(ownerIDField.getText()).setPassword(passwordField.getText());
						Main.owners.get(ownerIDField.getText()).setUsername(usernameField.getText());
						tableModel.setRowCount(0);
						for (Entry<String, Owner> entry : Main.owners.entrySet()) {
							Owner o = entry.getValue();
							String[] data = {o.getID(),o.getUsername(),o.getPassword(), o.getName(), o.getPhone(), o.getEmail(), o.getAddress()};
							tableModel.addRow(data);
						}
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");
			}
		});
		editButton.setBounds(85, 253, 86, 23);
		contentPane.add(editButton);
		
		JButton removeButton = new JButton("REMOVE");
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.owners.get(ownerIDField.getText())!= null) {
					ArrayList<String> salesToRemove = new ArrayList<>();
					for (Entry<String,Sale> s: Main.sales.entrySet()) {
						if (s.getValue().getPropToSale().getOwnerID().equals(ownerIDField.getText())){
							salesToRemove.add(s.getValue().getID());
						}
					}
					for (String s: salesToRemove) 
						Main.sales.remove(s);
					
					for (Entry<String, Property> p: Main.owners.get(ownerIDField.getText()).ownerProperties.entrySet())
						Main.properties.remove(p.getValue().getID());
					
					Main.owners.remove(ownerIDField.getText());
					tableModel.setRowCount(0);
					for (Entry<String, Owner> entry : Main.owners.entrySet()) {
						Owner o = entry.getValue();
						String[] data = {o.getID(),o.getUsername(),o.getPassword(), o.getName(), o.getPhone(), o.getEmail(), o.getAddress()};
						tableModel.addRow(data);
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");
			}
		});
		removeButton.setBounds(10, 287, 161, 23);
		contentPane.add(removeButton);
		
		JButton showPropertiesButton = new JButton("<html>Show Selected<br>Owner's Properties</html>");
		showPropertiesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.owners.get(ownerIDField.getText())!= null) {
					ShowOwnerPropsFrame();
					
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");
			}
		});
		showPropertiesButton.setBounds(10, 321, 161, 37);
		contentPane.add(showPropertiesButton);
		
		JLabel lblNewLabel_5 = new JLabel("Username");
		lblNewLabel_5.setBounds(10, 52, 65, 14);
		contentPane.add(lblNewLabel_5);
		
		usernameField = new JTextField();
		usernameField.setBounds(85, 49, 86, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setBounds(10, 77, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		passwordField = new JTextField();
		passwordField.setBounds(85, 74, 86, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String value =table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
	            ownerIDField.setText(Main.owners.get(value).getID());
	            nameField.setText(Main.owners.get(value).getName());
	            phoneField.setText(Main.owners.get(value).getPhone());
	            emailField.setText(Main.owners.get(value).getEmail());
	            addressField.setText(Main.owners.get(value).getAddress());
	            usernameField.setText(Main.owners.get(value).getUsername());
	            passwordField.setText(Main.owners.get(value).getPassword());
			}
	    });
		//Ownerda io exception kalkacak*****
				JButton addButton = new JButton("ADD");
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (!nameField.getText().isBlank() &&!phoneField.getText().isBlank()&&!emailField.getText().isBlank()&&!addressField.getText().isBlank()
								&&!usernameField.getText().isBlank()&&!passwordField.getText().isBlank()){
							boolean correctFlag = true;
							for(int i = 0;i<table.getModel().getRowCount();i++)
							{
								if (tableModel.getValueAt(i, 1).toString().equals(usernameField.getText())) {
							    	if (tableModel.getValueAt(i, 0).toString().equals(validID()))
							    		break;
							    	else {
							    		JOptionPane.showMessageDialog(contentPane, "This Username is already in use.");
								    	correctFlag = false;
								    	break;
							    	}
							    }
							}
							if (correctFlag) {
								Owner o1 = new Owner(validID(), usernameField.getText(), passwordField.getText(), nameField.getText(), emailField.getText()
											,addressField.getText(), phoneField.getText());
								Main.owners.put(o1.getID(), o1);
								String[] data2 = {o1.getID(),o1.getUsername(),o1.getPassword(), o1.getName(), o1.getPhone(), o1.getEmail(), o1.getAddress()};
								tableModel.addRow(data2);
								JOptionPane.showMessageDialog(contentPane, "Owner has been added with ID: "+o1.getID());
							}
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Fill empty boxes.");}
					}
				});
				addButton.setBounds(10, 253, 77, 23);
				contentPane.add(addButton);
			
				addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				        MainFrame mf = new MainFrame();
				        mf.setVisible(true);
				        dispose();
				    }
				});
			
	}
	public void ShowOwnerPropsFrame() {
		JFrame frame2 = new JFrame();
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setBounds(100, 100, 450, 300);
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane2.setLayout(new BorderLayout(0, 0));
		frame2.setContentPane(contentPane2);
		JScrollPane scrollPane2 = new JScrollPane();
		contentPane2.add(scrollPane2, BorderLayout.CENTER);
		
		String[] columns = {"ID","OwnerID","Property Type","Square Meter","Price","Bedrooms","Bathrooms","Age","Balcony","Garage",
				"Garden", "Pool","Address"};
		DefaultTableModel model2 = new DefaultTableModel(columns, 0);
		table2 = new JTable(model2);
		for (Entry<String, Property> p : Main.owners.get(ownerIDField.getText()).ownerProperties.entrySet()) {
			Property entry = p.getValue();
			String[] data = {entry.getID(), entry.getOwnerID(), entry.getPropType(), String.valueOf(entry.getSqrMeter()),String.valueOf(entry.getPrice()),String.valueOf(entry.getBedrooms())
					,String.valueOf(entry.getBathrooms()),String.valueOf(entry.getAge()), String.valueOf(entry.isBalcony()),String.valueOf(entry.isGarage())
					,String.valueOf(entry.isGarden()), String.valueOf(entry.isPool()), entry.getAddress()};
			model2.addRow(data);
		}
		scrollPane2.setViewportView(table2);
		frame2.setVisible(true);
	}
	public String validID() {
		int tempNumber = 0;
		boolean flag = true;
		while (flag) {
			if (Main.owners.get("O"+String.valueOf(tempNumber)) != null)
				tempNumber++;
			else
				flag = false;
		}
		return "O"+String.valueOf(tempNumber);
	}
}
