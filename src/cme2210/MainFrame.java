package cme2210;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 408);
		contentPane = new JPanel();		
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img//san-francisco-4674351_1280_adobespark.jpg"));
		lblNewLabel_4.setBounds(119, 46, 434, 307);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("REAL ESTATE MANAGEMENT SYSTEM");
		lblNewLabel_5.setFont(new Font("Mukta Mahee", Font.BOLD, 16));
		lblNewLabel_5.setBounds(179, 19, 327, 16);
		contentPane.add(lblNewLabel_5);
		
		JButton ownerButton = new JButton("Owner");
		ownerButton.setBounds(10, 190, 99, 29);
		contentPane.add(ownerButton);
		ownerButton.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        // Create a method named "createFrame()", and set up an new frame there
		        // Call createFrame()
		    	OwnerFrame ownerPanel = null;
				ownerPanel = new OwnerFrame();
		    	ownerPanel.setVisible(true);
		    	dispose();
		    }
			
		});
		
		JButton clientButton = new JButton("Buyer");
		clientButton.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        // Create a method named "createFrame()", and set up an new frame there
		        // Call createFrame()
		    	BuyersFrame buyerPanel = new BuyersFrame();
		    	buyerPanel.setVisible(true);
		    	dispose();
		    }
			
		});
		clientButton.setBounds(10, 140, 99, 29);
		contentPane.add(clientButton);
		
		JButton propertyButton = new JButton("Property");
		propertyButton.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        // Create a method named "createFrame()", and set up an new frame there
		        // Call createFrame()
		    	PropertyFrame propertyPanel = new PropertyFrame();
		    	propertyPanel.setVisible(true);
		    	dispose();
		    }
			
		});
		propertyButton.setBounds(10, 90, 99, 29);;
		contentPane.add(propertyButton);
		
		JButton saleButton = new JButton("Sale");
		saleButton.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        // Create a method named "createFrame()", and set up an new frame there
		        // Call createFrame()
		    	SaleFrame salePanel = new SaleFrame();
		    	salePanel.setVisible(true);
		    	dispose();
		    }
			
		});
		saleButton.setBounds(10, 240, 99, 29);
		contentPane.add(saleButton);
		
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

}
