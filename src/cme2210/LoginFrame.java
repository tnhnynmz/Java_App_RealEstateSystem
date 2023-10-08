package cme2210;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField,passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 538, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(234, 37, 61, 26);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(278, 190, 187, 26);
		contentPane.add(passwordField);

		JLabel lblNewLabel_3 = new JLabel("Please enter empty fields!");
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(186, 331, 187, 16);
		contentPane.add(lblNewLabel_3);
		
		
		JRadioButton adminSelect = new JRadioButton("Admin");
		adminSelect.setOpaque(false);
		adminSelect.setContentAreaFilled(false);
		adminSelect.setBorderPainted(false);
		adminSelect.setForeground(Color.WHITE);
		adminSelect.setBounds(143, 242, 89, 23);
		contentPane.add(adminSelect);
		
		JRadioButton buyerSelect = new JRadioButton("Buyer");
		buyerSelect.setOpaque(false);
		buyerSelect.setContentAreaFilled(false);
		buyerSelect.setBorderPainted(false);
		buyerSelect.setForeground(Color.WHITE);
		buyerSelect.setBounds(278, 242, 78, 23);
		contentPane.add(buyerSelect);
		
		JRadioButton ownerSelect = new JRadioButton("Owner");
		ownerSelect.setOpaque(false);
		ownerSelect.setContentAreaFilled(false);
		ownerSelect.setBorderPainted(false);
		ownerSelect.setForeground(Color.WHITE);
		ownerSelect.setBounds(386, 242, 89, 23);
		contentPane.add(ownerSelect);
		
		
		
		ButtonGroup usergroup=new ButtonGroup();
		usergroup.add(adminSelect);
	    usergroup.add(buyerSelect);
	    usergroup.add(ownerSelect);
		

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if(usernameField.getText().equals("")||passwordField.getText().equals("")||usergroup.isSelected(null)) {

					lblNewLabel_3.setText("Please enter empty fields! ");
					lblNewLabel_3.setVisible(true);
					lblNewLabel_3.setEnabled(true);
				}
				else if (adminSelect.isSelected()) {
					Boolean usernameFlag = true;
					for (Entry<String, Admin> entry : Main.admins.entrySet()) {
						usernameFlag = false;
						Admin a = entry.getValue();
						if (usernameField.getText().equals(a.getUsername())) {
							usernameFlag = true;
							if (a.getPassword().equals(passwordField.getText())) {
								MainFrame mf = new MainFrame();
								mf.setVisible(true);
								dispose();
								break;
							}
							else
								JOptionPane.showMessageDialog(contentPane, "Invalid Password!");
						}
					}
					if (!usernameFlag)
						JOptionPane.showMessageDialog(contentPane, "Invalid Username!");
				}
				else if (buyerSelect.isSelected()) {
					Boolean usernameFlag = true;
					for (Entry<String, Buyer> entry : Main.buyers.entrySet()) {
						usernameFlag = false;
						Buyer b = entry.getValue();
						if (usernameField.getText().equals(b.getUsername())) {
							usernameFlag = true;
							if (b.getPassword().equals(passwordField.getText())) {
								Main.currentUserID = b.getID();
								BuyerMenu bm = new BuyerMenu();
								bm.setVisible(true);
								dispose();
								break;
							}
							else
								JOptionPane.showMessageDialog(contentPane, "Invalid Password!");
						}
					}
					if (!usernameFlag)
						JOptionPane.showMessageDialog(contentPane, "Invalid Username!");
				}

				else if (ownerSelect.isSelected()) {
					Boolean usernameFlag = true;
					for (Entry<String, Owner> entry : Main.owners.entrySet()) {
						usernameFlag = false;
						Owner o = entry.getValue();
						if (usernameField.getText().equals(o.getUsername())) {
							usernameFlag = true;
							if (o.getPassword().equals(passwordField.getText())) {
								Main.currentUserID = o.getID();
								OwnerMenu om = new OwnerMenu();
								om.setVisible(true);
								dispose();
								break;
							}
							else
								JOptionPane.showMessageDialog(contentPane, "Invalid Password!");
						}
					}
					if (!usernameFlag)
						JOptionPane.showMessageDialog(contentPane, "Invalid Username!");
				}
			}
		});
		btnNewButton.setBounds(136, 290, 117, 29);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(154, 143, 99, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(154, 195, 99, 16);
		contentPane.add(lblNewLabel_2);
		
		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				
				char c=e.getKeyChar();
				if(!(Character.isLetter(c)||Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
					
				}
			}
		});
		
		usernameField.setBounds(278, 138, 187, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				dispose();
				RegisterFrame register=new RegisterFrame();
				register.setVisible(true);
				
				
			}
		});
		btnNewButton_1.setBounds(295, 290, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img//adobespark.png"));
		lblNewLabel_4.setBounds(0, 0, 538, 384);
		contentPane.add(lblNewLabel_4);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            try {
						Main.writeUsers();
						Main.writeProperties();
						Main.writeSales();
						System.exit(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        else {
		        	LoginFrame lf = new LoginFrame();
		        	lf.setVisible(true);
		        }
		    }
		});
		/*JButton adminButton = new JButton("ADMIN");
		adminButton.setBounds(161, 61, 88, 23);
		adminButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AdminMenu af = new AdminMenu();
				af.setVisible(true);
			}	
		});*/
	}
}
