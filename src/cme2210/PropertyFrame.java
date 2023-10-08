package cme2210;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class PropertyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField typeField;
	private JTextField ownerIDField;
	private JTextField sqrField;
	private JTextField priceField;
	private JTextField addressField;
	private JTextField bathField;
	private JTextField bedField;
	private JTextField ageField;
	private JTextField descField;
	private JTextField searchIDField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropertyFrame frame = new PropertyFrame();
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
	public PropertyFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(25, 62, 65, 14);
		contentPane.add(lblNewLabel);
		
		IDField = new JTextField();
		IDField.setEditable(false);
		IDField.setBounds(100, 59, 95, 20);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Bathrooms");
		lblNewLabel_6.setBounds(302, 62, 65, 14);
		contentPane.add(lblNewLabel_6);
		
		bathField = new JTextField();
		bathField.setBounds(372, 59, 89, 20);
		contentPane.add(bathField);
		//BATHROOMS MUST BE INTEGER, IF A KEY PRESSED IT MUST BE (0..9 OR BACKSPACE)
		onlyInteger(bathField);
		bathField.setColumns(10);
		
		JCheckBox poolCheck = new JCheckBox("Pool");
		poolCheck.setBounds(533, 59, 43, 21);
		poolCheck.setHorizontalAlignment(SwingConstants.CENTER);
		poolCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		contentPane.add(poolCheck);
		
		JCheckBox gardenCheck = new JCheckBox("Garden");
		gardenCheck.setBounds(533, 85, 55, 21);
		gardenCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		gardenCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		contentPane.add(gardenCheck);
		
		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setBounds(25, 114, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		typeField = new JTextField();
		typeField.setBounds(100, 111, 95, 20);
		contentPane.add(typeField);
		typeField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Bedrooms");
		lblNewLabel_7.setBounds(302, 114, 65, 14);
		contentPane.add(lblNewLabel_7);
		
		bedField = new JTextField();
		bedField.setBounds(372, 111, 89, 20);
		contentPane.add(bedField);
		//BEDROOMS MUST BE INTEGER, IF A KEY PRESSED IT MUST BE (0..9 OR BACKSPACE)
		onlyInteger(bedField);
		bedField.setColumns(10);
		
		JCheckBox garageCheck = new JCheckBox("Garage");
		garageCheck.setBounds(533, 111, 55, 21);
		garageCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		contentPane.add(garageCheck);
		
		JCheckBox balconyCheck = new JCheckBox("Balcony");
		balconyCheck.setBounds(533, 137, 59, 21);
		balconyCheck.setFont(new Font("Tahoma", Font.PLAIN, 9));
		contentPane.add(balconyCheck);
		
		JLabel lblNewLabel_2 = new JLabel("Owner ID");
		lblNewLabel_2.setBounds(25, 166, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		ownerIDField = new JTextField();
		ownerIDField.setBounds(100, 163, 95, 20);
		contentPane.add(ownerIDField);
		ownerIDField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Age");
		lblNewLabel_8.setBounds(324, 166, 43, 14);
		contentPane.add(lblNewLabel_8);
		
		ageField = new JTextField();
		ageField.setBounds(372, 163, 89, 20);
		contentPane.add(ageField);
		//AGE MUST BE INTEGER, IF A KEY PRESSED IT MUST BE (0..9 OR BACKSPACE)
		onlyInteger(ageField);
		ageField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Square Meter");
		lblNewLabel_3.setBounds(10, 226, 80, 14);
		contentPane.add(lblNewLabel_3);
		
		sqrField = new JTextField();
		sqrField.setBounds(100, 223, 95, 20);
		contentPane.add(sqrField);
		//SQUARE METER MUST BE INTEGER, IF A KEY PRESSED IT MUST BE (0..9 OR BACKSPACE)
		onlyInteger(sqrField);
		sqrField.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Description");
		lblNewLabel_9.setBounds(278, 223, 53, 14);
		contentPane.add(lblNewLabel_9);
		
		descField = new JTextField();
		descField.setBounds(278, 242, 183, 85);
		contentPane.add(descField);
		descField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setBounds(25, 310, 65, 14);
		contentPane.add(lblNewLabel_4);
		
		priceField = new JTextField();
		priceField.setBounds(100, 307, 95, 20);
		contentPane.add(priceField);
		//PRICE MUST BE INTEGER, IF A KEY PRESSED IT MUST BE (0..9 OR BACKSPACE)
		onlyInteger(priceField);
		priceField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setBounds(25, 369, 65, 14);
		contentPane.add(lblNewLabel_5);
		
		addressField = new JTextField();
		addressField.setBounds(100, 367, 93, 48);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		JButton addButton = new JButton("ADD");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				/*if (Main.properties.get(IDField.getText())!= null)
					JOptionPane.showMessageDialog(contentPane, "This ID is already in use.");
				else if (IDField.getText().charAt(0)!='P')
					JOptionPane.showMessageDialog(contentPane, "Buyer ID must start with 'P'.");*/
				if (!typeField.getText().isBlank() &&!ownerIDField.getText().isBlank()&&!sqrField.getText().isBlank()&&!priceField.getText().isBlank()
						&&!addressField.getText().isBlank()&&!bathField.getText().isBlank()&&!bedField.getText().isBlank()&&!ageField.getText().isBlank()
						&&!descField.getText().isBlank()){
					Boolean correctFlag = false;
					if (ownerIDField!=null) {
						if (Main.owners.get(ownerIDField.getText()) == null) {
							correctFlag = false;
							JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");}
						else {
							String ownerID = ownerIDField.getText();
							correctFlag = true;
						}
					}
					if (correctFlag) {
						Property ptemp = new Property(validID(),ownerIDField.getText(),typeField.getText()
								,Integer.parseInt(sqrField.getText()),Integer.parseInt(priceField.getText()),Integer.parseInt(bathField.getText())
								,Integer.parseInt(bedField.getText()),Integer.parseInt(ageField.getText()),
								balconyCheck.isSelected(),balconyCheck.isSelected(),gardenCheck.isSelected(),
								poolCheck.isSelected(),addressField.getText(),descField.getText());
						Main.properties.put(ptemp.getID(), ptemp);
						Main.owners.get(ownerIDField.getText()).addOwnerProperty(ptemp);
						JOptionPane.showMessageDialog(contentPane, "Property has been added with ID: "+ptemp.getID());
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "Fill empty boxes.");
			}
		});
		addButton.setBounds(500, 240, 89, 23);
		contentPane.add(addButton);
		JButton editButton = new JButton("EDIT");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.properties.get(IDField.getText()) == null)
					JOptionPane.showMessageDialog(contentPane, "There is no property with that ID.");
				else if (!typeField.getText().isBlank() &&!ownerIDField.getText().isBlank()&&!sqrField.getText().isBlank()&&!priceField.getText().isBlank()
						&&!addressField.getText().isBlank()&&!bathField.getText().isBlank()&&!bedField.getText().isBlank()&&!ageField.getText().isBlank()
						&&!descField.getText().isBlank()){
					Boolean correctFlag = false;
					if (ownerIDField!=null) {
						if (Main.owners.get(ownerIDField.getText()) == null) {
							correctFlag = false;
							JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");}
						else {
							String ownerID = ownerIDField.getText();
							correctFlag = true;
						}
					}
					if (correctFlag) {
						Main.owners.get(Main.properties.get(IDField.getText()).getOwnerID()).ownerProperties.remove(IDField.getText());
						Main.properties.get(IDField.getText()).setOwnerID(ownerIDField.getText());
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
					
						Main.owners.get(ownerIDField.getText()).ownerProperties.put(IDField.getText(), Main.properties.get(IDField.getText()));
						JOptionPane.showMessageDialog(contentPane, "Successful.");
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "Fill empty boxes.");
			}
		});
		editButton.setBounds(500, 273, 89, 23);
		contentPane.add(editButton);	
		JButton removeButton = new JButton("REMOVE");
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.properties.get(IDField.getText())== null)
					JOptionPane.showMessageDialog(contentPane, "There is no property with this ID.");
				else {
					Main.properties.remove(IDField.getText());
					for (Entry<String, Owner> o: Main.owners.entrySet()) {
						if (o.getValue().ownerProperties.get(IDField.getText()) != null) {
							Main.owners.get(o.getValue().getID()).ownerProperties.remove(IDField.getText());
							break;
						}	
					}
					ArrayList<String> salesToRemove = new ArrayList<>();
					for (Entry<String,Sale> s: Main.sales.entrySet()) {
						if (s.getValue().getPropToSale().getID().equals(IDField.getText())){
							salesToRemove.add(s.getValue().getID());
						}
					}
					for (String s: salesToRemove) 
						Main.sales.remove(s);
					JOptionPane.showMessageDialog(contentPane, "Successful.");
				}
			}
		});
		removeButton.setBounds(500, 305, 89, 23);
		contentPane.add(removeButton);
		
		JButton showButton = new JButton("Show Properties");
		showButton.setBounds(277, 365, 184, 23);
		showButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ShowPropertiesFrame s1 = new ShowPropertiesFrame();
				s1.setVisible(true);
			}
		});
		contentPane.add(showButton);
		JButton searchButton = new JButton("Search");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Property p = Main.properties.get(searchIDField.getText());
				if (p == null)
					JOptionPane.showMessageDialog(contentPane, "There is no property with that ID.");
				else {
					IDField.setText(p.getID());
					typeField.setText(p.getPropType());
					ownerIDField.setText(p.getOwnerID());
					sqrField.setText(String.valueOf(p.getSqrMeter()));
					priceField.setText(String.valueOf(p.getPrice()));
					addressField.setText(p.getAddress());
					bathField.setText(String.valueOf(p.getBedrooms()));
					bedField.setText(String.valueOf(p.getBathrooms()));
					ageField.setText(String.valueOf(p.getAge()));
					descField.setText(p.getDescription());
					poolCheck.setSelected(p.isPool());
					balconyCheck.setSelected(p.isBalcony());
					gardenCheck.setSelected(p.isGarden());
					garageCheck.setSelected(p.isGarage());
				}
			}
		});
		searchButton.setBounds(10, 11, 89, 22);
		contentPane.add(searchButton);	
		
		searchIDField = new JTextField();
		searchIDField.setBounds(100, 12, 95, 20);
		contentPane.add(searchIDField);
		searchIDField.setColumns(10);
		
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
			if (Main.properties.get("P"+String.valueOf(tempNumber)) != null)
				tempNumber++;
			else
				flag = false;
		}
		return "P"+String.valueOf(tempNumber);
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
}
