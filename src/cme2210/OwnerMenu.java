package cme2210;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OwnerMenu extends JFrame {

	private JPanel contentPane,contentPane2,contentPane3;
	private JTextField IDField;
	private JTextField bathField;
	private JTextField typeField;
	private JTextField bedField;
	private JTextField ageField;
	private JTextField sqrField;
	private JTextField descField;
	private JTextField priceField;
	private JTextField addressField;
	private JTable table,table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerMenu frame = new OwnerMenu();
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
	public OwnerMenu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 711, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(25, 22, 65, 14);
		contentPane.add(lblNewLabel);
		
		IDField = new JTextField();
		IDField.setColumns(10);
		IDField.setBounds(100, 19, 95, 20);
		contentPane.add(IDField);
		
		JLabel lblNewLabel_6 = new JLabel("Bathrooms");
		lblNewLabel_6.setBounds(302, 22, 65, 14);
		contentPane.add(lblNewLabel_6);
		
		bathField = new JTextField();
		onlyInteger(bathField);
		bathField.setColumns(10);
		bathField.setBounds(372, 19, 89, 20);
		contentPane.add(bathField);
		
		JCheckBox poolCheck = new JCheckBox("Pool");
		poolCheck.setHorizontalAlignment(SwingConstants.CENTER);
		poolCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		poolCheck.setBounds(278, 294, 43, 21);
		contentPane.add(poolCheck);
		
		JCheckBox gardenCheck = new JCheckBox("Garden");
		gardenCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		gardenCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		gardenCheck.setBounds(278, 320, 55, 21);
		contentPane.add(gardenCheck);
		
		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setBounds(25, 74, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		typeField = new JTextField();
		typeField.setColumns(10);
		typeField.setBounds(100, 71, 95, 20);
		contentPane.add(typeField);
		
		JLabel lblNewLabel_7 = new JLabel("Bedrooms");
		lblNewLabel_7.setBounds(302, 74, 65, 14);
		contentPane.add(lblNewLabel_7);
		
		bedField = new JTextField();
		onlyInteger(bedField);
		bedField.setColumns(10);
		bedField.setBounds(372, 71, 89, 20);
		contentPane.add(bedField);
		
		JCheckBox garageCheck = new JCheckBox("Garage");
		garageCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		garageCheck.setBounds(339, 294, 55, 21);
		contentPane.add(garageCheck);
		
		JCheckBox balconyCheck = new JCheckBox("Balcony");
		balconyCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		balconyCheck.setBounds(339, 320, 59, 21);
		contentPane.add(balconyCheck);
		
		JLabel lblNewLabel_8 = new JLabel("Age");
		lblNewLabel_8.setBounds(324, 126, 43, 14);
		contentPane.add(lblNewLabel_8);
		
		ageField = new JTextField();
		onlyInteger(ageField);
		ageField.setColumns(10);
		ageField.setBounds(372, 123, 89, 20);
		contentPane.add(ageField);
		
		JLabel lblNewLabel_3 = new JLabel("Square Meter");
		lblNewLabel_3.setBounds(10, 129, 80, 14);
		contentPane.add(lblNewLabel_3);
		
		sqrField = new JTextField();
		onlyInteger(sqrField);
		sqrField.setColumns(10);
		sqrField.setBounds(100, 126, 95, 20);
		contentPane.add(sqrField);
		
		JLabel lblNewLabel_9 = new JLabel("Description");
		lblNewLabel_9.setBounds(278, 183, 53, 14);
		contentPane.add(lblNewLabel_9);
		
		descField = new JTextField();
		descField.setColumns(10);
		descField.setBounds(278, 202, 183, 85);
		contentPane.add(descField);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setBounds(25, 186, 65, 14);
		contentPane.add(lblNewLabel_4);
		
		priceField = new JTextField();
		onlyInteger(priceField);
		priceField.setColumns(10);
		priceField.setBounds(100, 183, 95, 20);
		contentPane.add(priceField);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setBounds(25, 245, 65, 14);
		contentPane.add(lblNewLabel_5);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(100, 243, 93, 48);
		contentPane.add(addressField);
		
		JButton searchButton = new JButton("Search");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Property p = Main.owners.get(Main.currentUserID).ownerProperties.get(IDField.getText());
				if (p == null)
					JOptionPane.showMessageDialog(contentPane, "You don't have a property with that ID.");
				else {
					typeField.setText(p.getPropType());
					sqrField.setText(String.valueOf(p.getSqrMeter()));
					priceField.setText(String.valueOf(p.getPrice()));
					addressField.setText(p.getAddress());
					bedField.setText(String.valueOf(p.getBedrooms()));
					bathField.setText(String.valueOf(p.getBathrooms()));
					ageField.setText(String.valueOf(p.getAge()));
					descField.setText(p.getDescription());
					poolCheck.setSelected(p.isPool());
					balconyCheck.setSelected(p.isBalcony());
					gardenCheck.setSelected(p.isGarden());
					garageCheck.setSelected(p.isGarage());
				}
			}
		});
		searchButton.setBounds(194, 18, 80, 22);
		contentPane.add(searchButton);
		
		JButton addButton = new JButton("ADD");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!bathField.getText().isBlank() &&!typeField.getText().isBlank()&&!bedField.getText().isBlank()
						&&!ageField.getText().isBlank()&&!sqrField.getText().isBlank()&&!descField.getText().isBlank()&&!priceField.getText().isBlank()
						&&!addressField.getText().isBlank()){
					Property ptemp = new Property(validID(),Main.currentUserID,typeField.getText(),Integer.parseInt(sqrField.getText())
							,Integer.parseInt(priceField.getText()),Integer.parseInt(bedField.getText()),Integer.parseInt(bathField.getText())
							,Integer.parseInt(ageField.getText()),balconyCheck.isSelected(),garageCheck.isSelected(),gardenCheck.isSelected()
							,poolCheck.isSelected(), addressField.getText(), descField.getText());
					Main.properties.put(ptemp.getID(), ptemp);
					Main.owners.get(Main.currentUserID).addOwnerProperty(ptemp);
					JOptionPane.showMessageDialog(contentPane, "Property has been added with ID: "+ptemp.getID());
				}
				else
					JOptionPane.showMessageDialog(contentPane, "Fill empty boxes.");			
			}
		});
		addButton.setBounds(538, 70, 89, 23);
		contentPane.add(addButton);
		
		JButton editButton = new JButton("EDIT");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.properties.get(IDField.getText()) == null)
					JOptionPane.showMessageDialog(contentPane, "There is no property with that ID.");
				else if (!bathField.getText().isBlank() &&!typeField.getText().isBlank()&&!bedField.getText().isBlank()
						&&!ageField.getText().isBlank()&&!sqrField.getText().isBlank()&&!descField.getText().isBlank()&&!priceField.getText().isBlank()
						&&!addressField.getText().isBlank()){
					Main.properties.get(IDField.getText()).setPropType(typeField.getText());
					Main.properties.get(IDField.getText()).setSqrMeter(Integer.parseInt(sqrField.getText()));
					Main.properties.get(IDField.getText()).setPrice(Integer.parseInt(priceField.getText()));
					Main.properties.get(IDField.getText()).setAddress(addressField.getText());
					Main.properties.get(IDField.getText()).setBathrooms(Integer.parseInt(bathField.getText()));
					Main.properties.get(IDField.getText()).setBedrooms(Integer.parseInt(bedField.getText()));
					Main.properties.get(IDField.getText()).setAge(Integer.parseInt(ageField.getText()));
					Main.properties.get(IDField.getText()).setDescription(descField.getText());
					Main.properties.get(IDField.getText()).setPool(poolCheck.isSelected());
					Main.properties.get(IDField.getText()).setGarden(gardenCheck.isSelected());
					Main.properties.get(IDField.getText()).setGarage(garageCheck.isSelected());
					Main.properties.get(IDField.getText()).setBalcony(balconyCheck.isSelected());
				}
				else
					JOptionPane.showMessageDialog(contentPane, "Fill empty boxes.");
			}
		});
		editButton.setBounds(538, 122, 89, 23);
		contentPane.add(editButton);
		
		JButton removeButton = new JButton("REMOVE");
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.properties.get(IDField.getText())== null)
					JOptionPane.showMessageDialog(contentPane, "There is no property with this ID.");
				else {
					Main.owners.get(Main.currentUserID).ownerProperties.remove(IDField.getText());
					Main.properties.remove(IDField.getText());
					ArrayList<String> salesToRemove = new ArrayList<>();
					for (Entry<String,Sale> s: Main.sales.entrySet()) {
						if (s.getValue().getPropToSale().getID().equals(IDField.getText()))
							salesToRemove.add(s.getValue().getID());
					}
					for (String s: salesToRemove) 
						Main.sales.remove(s);
				}
			}
		});
		removeButton.setBounds(538, 182, 89, 23);
		contentPane.add(removeButton);
		
		JButton propButton = new JButton("MY PROPERTIES");
		propButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPropsFrame();}
		});
		propButton.setBounds(496, 285, 131, 23);
		contentPane.add(propButton);
		
		JButton offerButton = new JButton("MY OFFERS");
		offerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showSalesFrame();}
		});
		offerButton.setBounds(496, 319, 131, 23);
		contentPane.add(offerButton);
		
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
	public void showPropsFrame() {
		JFrame frame2 = new JFrame();
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setBounds(100, 100, 450, 300);
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane2.setLayout(new BorderLayout(0, 0));
		frame2.setContentPane(contentPane2);
		JScrollPane scrollPane2 = new JScrollPane();
		contentPane2.add(scrollPane2, BorderLayout.CENTER);
		
		String[] columns = {"ID","Property Type","Square Meter","Price","Bedrooms","Bathrooms","Age","Balcony","Garage",
				"Garden", "Pool","Address"};
		DefaultTableModel model2 = new DefaultTableModel(columns, 0);
		table = new JTable(model2);
		for (Entry<String, Property> p : Main.owners.get(Main.currentUserID).ownerProperties.entrySet()) {
			Property entry = p.getValue();
			String[] data = {entry.getID(), entry.getPropType(), String.valueOf(entry.getSqrMeter()),String.valueOf(entry.getPrice()),String.valueOf(entry.getBedrooms())
					,String.valueOf(entry.getBathrooms()),String.valueOf(entry.getAge()), String.valueOf(entry.isBalcony()),String.valueOf(entry.isGarage())
					,String.valueOf(entry.isGarden()), String.valueOf(entry.isPool()), entry.getAddress()};
			model2.addRow(data);
		}
		scrollPane2.setViewportView(table);
		frame2.setVisible(true);
	}
	public void showSalesFrame() {
		JFrame frame3 = new JFrame();
		frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame3.setBounds(100, 100, 450, 300);
		contentPane3 = new JPanel();
		contentPane3.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane3.setLayout(new BorderLayout(0, 0));
		frame3.setContentPane(contentPane3);
		JScrollPane scrollPane3 = new JScrollPane();
		contentPane3.add(scrollPane3, BorderLayout.CENTER);
		
		String[] columns = {"Property ID","Buyer Name","Phone","Email","Offer"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table_1 = new JTable(model);
		for (Entry<String, Sale> s : Main.sales.entrySet()) {
			if (s.getValue().getPropToSale().getOwnerID().equals(Main.currentUserID)) {
				String[] data = {s.getValue().getPropToSale().getID(),s.getValue().getSaleToBuyer().getName(),s.getValue().getSaleToBuyer().getPhone()
						,s.getValue().getSaleToBuyer().getEmail(),s.getValue().getFinalPrice()};
				model.addRow(data);
			}
			
		}
		scrollPane3.setViewportView(table_1);
		frame3.setVisible(true);
	}
	public void onlyInteger(JTextField textfield) {
		textfield.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = textfield.getText();
	            int l = value.length();
	            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
	               textfield.setEditable(true);
	            } else {
	               textfield.setEditable(false);
	            }
	         }
	      });
	}
	public String validID() {
		int tempNumber = 0;
		boolean flag = true;
		while (flag) {
			if (Main.properties.get("P"+String.valueOf(tempNumber)) != null)
				tempNumber++;
			else
				flag = false;
		}
		return "P"+String.valueOf(tempNumber);
	}
}
