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

public class ShowPropertiesFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPropertiesFrame frame = new ShowPropertiesFrame();
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
	public ShowPropertiesFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		String[] columns = {"ID","OwnerID","Property Type","Square Meter","Price","Bedrooms","Bathrooms","Age","Balcony","Garage",
				"Garden", "Pool","Address"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		for (Entry<String, Property> entry : Main.properties.entrySet()) {
			Property p = entry.getValue();
			String[] data = {p.getID(), p.getOwnerID(), p.getPropType(), String.valueOf(p.getSqrMeter()),String.valueOf(p.getPrice()),String.valueOf(p.getBedrooms())
					,String.valueOf(p.getBathrooms()),String.valueOf(p.getAge()), String.valueOf(p.isBalcony()),String.valueOf(p.isGarage())
					,String.valueOf(p.isGarden()), String.valueOf(p.isPool()), p.getAddress()};
			model.addRow(data);
		}
		scrollPane.setViewportView(table);
	}

}
