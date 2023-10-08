package cme2210;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class SaleFrame extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField ownerIDField;
	private JTextField propIDField;
	private JTextField clientIDField;
	private JTextField fpriceField;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleFrame frame = new SaleFrame();
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
	public SaleFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 931, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 200, 360);
		contentPane.add(scrollPane);
		
		String[] columns = {"ID","Owner ID","Price"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		table = new JTable(tableModel);
		for (Entry<String, Property> entry : Main.properties.entrySet()) {
			Property p = entry.getValue();
			String[] data = {p.getID(),p.getOwnerID(),String.valueOf(p.getPrice())};
			tableModel.addRow(data);
		}
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(220, 37, 200, 360);
		contentPane.add(scrollPane_1);
		
		String[] columns1 = {"ID","Buyer Name"};
		DefaultTableModel tableModel1 = new DefaultTableModel(columns1, 0){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		table_1 = new JTable(tableModel1);
		for (Entry<String, Buyer> entry : Main.buyers.entrySet()) {
			Buyer b = entry.getValue();
			String[] data = {b.getID(),b.getName()};
			tableModel1.addRow(data);
		}
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(430, 37, 259, 360);
		contentPane.add(scrollPane_2);
		
		String[] columns2 = {"ID","Owner ID","Property ID","Buyer ID","Final Price"};
		DefaultTableModel tableModel2 = new DefaultTableModel(columns2, 0){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		table_2 = new JTable(tableModel2);
		for (Entry<String, Sale> entry : Main.sales.entrySet()) {
			Sale s = entry.getValue();
			String[] data = {s.getID(),s.getPropToSale().getOwnerID(),s.getPropToSale().getID(),s.getSaleToBuyer().getID(),s.getFinalPrice()};
			tableModel2.addRow(data);
		}
		scrollPane_2.setViewportView(table_2);
		
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String value =table_2.getModel().getValueAt(table_2.getSelectedRow(), 0).toString();
				propIDField.setText(Main.sales.get(value).getPropToSale().getID());
				ownerIDField.setText(Main.sales.get(value).getPropToSale().getOwnerID());
				IDField.setText(Main.sales.get(value).getID());
				clientIDField.setText(Main.sales.get(value).getSaleToBuyer().getID());
				fpriceField.setText(Main.sales.get(value).getFinalPrice());
				
			}
	    });
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(714, 38, 46, 14);
		contentPane.add(lblNewLabel);
		
		IDField = new JTextField();
		IDField.setEditable(false);
		IDField.setBounds(793, 38, 86, 20);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Owner ID");
		lblNewLabel_1.setBounds(715, 70, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		ownerIDField = new JTextField();
		ownerIDField.setBounds(793, 70, 86, 20);
		contentPane.add(ownerIDField);
		ownerIDField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Property ID");
		lblNewLabel_2.setBounds(715, 103, 59, 14);
		contentPane.add(lblNewLabel_2);
		
		propIDField = new JTextField();
		propIDField.setBounds(793, 104, 86, 20);
		contentPane.add(propIDField);
		propIDField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Client ID");
		lblNewLabel_3.setBounds(715, 137, 57, 14);
		contentPane.add(lblNewLabel_3);
		
		clientIDField = new JTextField();
		clientIDField.setBounds(793, 138, 86, 20);
		contentPane.add(clientIDField);
		clientIDField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Final Price");
		lblNewLabel_4.setBounds(716, 173, 53, 14);
		contentPane.add(lblNewLabel_4);
		
		fpriceField = new JTextField();
		fpriceField.setBounds(793, 173, 86, 20);
		contentPane.add(fpriceField);
		fpriceField.setColumns(10);
		
		JButton editButton = new JButton("EDIT");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.sales.get(IDField.getText())!= null) {
					if (!ownerIDField.getText().equals(Main.sales.get(IDField.getText()).getPropToSale().getOwnerID()))
						JOptionPane.showMessageDialog(contentPane, "Owner can't be edited.");
					else {
						if (!propIDField.getText().equals(Main.sales.get(IDField.getText()).getPropToSale().getID()))
							JOptionPane.showMessageDialog(contentPane, "Property can't be edited.");
						else {
							if (Main.buyers.get(clientIDField.getText()) == null)
								JOptionPane.showMessageDialog(contentPane, "There is no such buyer with that ID.");
							else {
								if(!clientIDField.getText().equals(Main.buyers.get(clientIDField.getText()).getID()))
									JOptionPane.showMessageDialog(contentPane, "There is no such buyer with that ID.");
								else {
									if (fpriceField.getText().isBlank())
										Main.sales.get(IDField.getText()).setFinalPrice("0");
									else
										Main.sales.get(IDField.getText()).setFinalPrice(fpriceField.getText());
									Main.sales.get(IDField.getText()).setSaleToBuyer(Main.buyers.get(clientIDField.getText()));
									tableModel2.setRowCount(0);
									for (Entry<String, Sale> entry : Main.sales.entrySet()) {
										Sale s = entry.getValue();
										String[] data = {s.getID(), s.getPropToSale().getOwnerID(),s.getPropToSale().getID(),s.getSaleToBuyer().getID(),s.getFinalPrice()};
										tableModel2.addRow(data);
									}
								}
							}
						}		
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such sale with that ID.");
			}
		});
		editButton.setBounds(790, 252, 89, 23);
		contentPane.add(editButton);
		
		JButton addButton = new JButton("ADD");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!ownerIDField.getText().isBlank()&&!propIDField.getText().isBlank()&&!clientIDField.getText().isBlank()
						&&!fpriceField.getText().isBlank()) {
					Boolean correctFlag = true;
					//CHECK IF THERE IS AN OWNER WITH GIVEN ID
					if (Main.owners.get(ownerIDField.getText())== null) 
						JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");
					else {
						//CHECK IF THERE IS A PROPERTY WITH GIVEN ID
						if (Main.properties.get(propIDField.getText())!= null) {
							//CHECK IF THE GIVEN PROPERTY AND GIVEN OWNER MATCHES
							if (!Main.properties.get(propIDField.getText()).getOwnerID().equals(ownerIDField.getText())) {
								correctFlag = false;
								JOptionPane.showMessageDialog(contentPane, "This owner has no such property with that ID.");
							}
							else {
								for(int i = 0;i<table_2.getModel().getRowCount();i++)
								{
									if (tableModel2.getValueAt(i, 2).toString().equals(propIDField.getText())) {
										JOptionPane.showMessageDialog(contentPane, "This property has already been sold.");
										correctFlag = false;
										break;
								    	}
								    }
								}
								if(correctFlag) {
									Sale s = new Sale(validID(),Main.properties.get(propIDField.getText()),Main.buyers.get(clientIDField.getText())
											,fpriceField.getText());
									Main.sales.put(s.getID(), s);
									String[] data = {s.getID(), s.getPropToSale().getOwnerID(),s.getPropToSale().getID(),s.getSaleToBuyer().getID(),s.getFinalPrice()};
									tableModel2.addRow(data);
									JOptionPane.showMessageDialog(contentPane, "Sale has been added with ID: "+s.getID());
							}
							
						}
						else
							JOptionPane.showMessageDialog(contentPane, "There is no such property with that ID.");
					}
					
				}
			}
		});
		addButton.setBounds(699, 252, 89, 23);
		contentPane.add(addButton);
		
		JButton removeButton = new JButton("REMOVE");
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.sales.get(IDField.getText())!= null) {
					Main.sales.remove(IDField.getText());
					tableModel2.setRowCount(0);
					for (Entry<String, Sale> entry : Main.sales.entrySet()) {
						Sale s = entry.getValue();
						String[] data = {s.getID(), s.getPropToSale().getOwnerID(),s.getPropToSale().getID(),s.getSaleToBuyer().getID(),s.getFinalPrice()};
						tableModel2.addRow(data);
					}
				}
				else
					JOptionPane.showMessageDialog(contentPane, "There is no such Owner with that ID.");
			}
		});
		removeButton.setBounds(699, 284, 180, 23);
		contentPane.add(removeButton);
		
		JLabel lblNewLabel_6 = new JLabel("Properties");
		lblNewLabel_6.setBounds(92, 12, 59, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Clients");
		lblNewLabel_7.setBounds(307, 12, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Sales");
		lblNewLabel_8.setBounds(542, 12, 46, 14);
		contentPane.add(lblNewLabel_8);
		
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
			if (Main.sales.get("S"+String.valueOf(tempNumber)) != null)
				tempNumber++;
			else
				flag = false;
		}
		return "S"+String.valueOf(tempNumber);
	}
}
