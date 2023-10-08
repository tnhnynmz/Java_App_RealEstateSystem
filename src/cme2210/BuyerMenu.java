package cme2210;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class BuyerMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField propIDField;
	private JTextField offerField;
	private JTable table_1;
	private JTextField saleIDField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyerMenu frame = new BuyerMenu();
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
	public BuyerMenu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(217, 43, 267, 292);
		contentPane.add(scrollPane);
		
		String[] columns = {"ID","Owner ID","Property ID","Final Price"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		table = new JTable(tableModel);
		for (Entry<String, Sale> entry : Main.sales.entrySet()) {
			if (entry.getValue().getSaleToBuyer().getID().equals(Main.currentUserID)) {
				String[] data = {entry.getValue().getID(),entry.getValue().getPropToSale().getOwnerID(),entry.getValue().getPropToSale().getID()
						,entry.getValue().getFinalPrice()};
				tableModel.addRow(data);
			}
		}
		scrollPane.setViewportView(table);
		

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(531, 43, 267, 292);
		contentPane.add(scrollPane_1);
		String[] columns2 = {"ID","Owner ID","Price"};
		DefaultTableModel tableModel2 = new DefaultTableModel(columns2, 0){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		table_1 = new JTable(tableModel2);
		for (Entry<String, Property> entry : Main.properties.entrySet()) {
				String[] data2 = {entry.getValue().getID(),entry.getValue().getOwnerID(),String.valueOf(entry.getValue().getPrice())};
				tableModel2.addRow(data2);
		}
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("Property");
		lblNewLabel.setBounds(28, 74, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Offer");
		lblNewLabel_1.setBounds(28, 99, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		propIDField = new JTextField();
		propIDField.setEditable(false);
		propIDField.setBounds(84, 71, 86, 20);
		contentPane.add(propIDField);
		propIDField.setColumns(10);
		
		offerField = new JTextField();
		offerField.setBounds(84, 96, 86, 20);
		contentPane.add(offerField);
		onlyInteger(offerField);
		offerField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("MY OFFERS");
		lblNewLabel_2.setBounds(329, 18, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton offerButton = new JButton("OFFER");
		offerButton.setBounds(28, 179, 71, 23);
		offerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Boolean correctFlag = true;
				if (!propIDField.getText().isBlank()) {
					if (correctFlag) {
						Sale newSale = new Sale(validID(),Main.properties.get(propIDField.getText()), Main.buyers.get(Main.currentUserID)
								,offerField.getText());
						Main.sales.put(newSale.getID(), newSale);
						Sale entry = Main.sales.get(newSale.getID());
						String[] data = {entry.getID(),entry.getPropToSale().getOwnerID(),entry.getPropToSale().getID()
								,entry.getFinalPrice()};
						tableModel.addRow(data);
						JOptionPane.showMessageDialog(contentPane, "Offer has been added with ID: "+entry.getID());
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "Please select a property from list.");
			}
			
		});
		contentPane.add(offerButton);
		
		JButton editButton = new JButton("EDIT");
		editButton.setBounds(99, 179, 71, 23);
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.sales.get(saleIDField.getText()) != null) {
					Main.sales.get(saleIDField.getText()).setFinalPrice(offerField.getText());
					tableModel.setRowCount(0);
					for (Entry<String, Sale> entry : Main.sales.entrySet()) {
						if (entry.getValue().getSaleToBuyer().getID().equals(Main.currentUserID)) {
							String[] data = {entry.getValue().getID(),entry.getValue().getPropToSale().getOwnerID(),entry.getValue().getPropToSale().getID()
									,entry.getValue().getFinalPrice()};
							tableModel.addRow(data);
						}
					}
					}
				else
					JOptionPane.showMessageDialog(contentPane, "Sale ID can't be changed.");
			}
			
		});
		contentPane.add(editButton);
		
		JButton removeButton = new JButton("REMOVE");
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.sales.get(saleIDField.getText()) != null) {
					Main.sales.remove(saleIDField.getText());
					tableModel.setRowCount(0);
					for (Entry<String, Sale> entry : Main.sales.entrySet()) {
						if (entry.getValue().getSaleToBuyer().getID().equals(Main.currentUserID)) {
							String[] data = {entry.getValue().getID(),entry.getValue().getPropToSale().getOwnerID(),entry.getValue().getPropToSale().getID()
									,entry.getValue().getFinalPrice()};
							tableModel.addRow(data);
						}
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such sale with that ID.");
			}
			
		});
		removeButton.setBounds(58, 213, 79, 23);
		contentPane.add(removeButton);
		
		JButton showPropertiesButton = new JButton("Show Properties");
		showPropertiesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ShowPropertiesFrame s1 = new ShowPropertiesFrame();
				s1.setVisible(true);
			}
		});
		showPropertiesButton.setBounds(33, 247, 125, 23);
		contentPane.add(showPropertiesButton);
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("PROPERTIES");
		lblNewLabel_3.setBounds(636, 18, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sale ID");
		lblNewLabel_4.setBounds(28, 124, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		saleIDField = new JTextField();
		saleIDField.setEditable(false);
		saleIDField.setBounds(84, 121, 86, 20);
		contentPane.add(saleIDField);
		saleIDField.setColumns(10);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String value =table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				propIDField.setText(Main.sales.get(value).getPropToSale().getID());
				offerField.setText(Main.sales.get(value).getFinalPrice());
				saleIDField.setText(Main.sales.get(value).getID());
			}
	    });
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String value =table_1.getModel().getValueAt(table_1.getSelectedRow(), 0).toString();
				propIDField.setText(Main.properties.get(value).getID());
				offerField.setText(String.valueOf(Main.properties.get(value).getPrice()));			
			}
	    });
		
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
			if (Main.sales.get("S"+String.valueOf(tempNumber)) != null)
				tempNumber++;
			else
				flag = false;
		}
		return "S"+String.valueOf(tempNumber);
	}
}
