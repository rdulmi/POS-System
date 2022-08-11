package devUPitemS.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.UIManager;

import devCashier.com.Cashier;

import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class UpdateItemState {

	private JFrame frame;
	private JTextField textField;
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateItemState window = new UpdateItemState();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateItemState() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setBounds(450, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Item State ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(129, 0, 252, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(21, 65, 132, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBackground(new Color(245, 245, 220));
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(207, 65, 206, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 153, 51));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(68, 192, 148, 35);
		frame.getContentPane().add(btnNewButton);
		JComboBox coboxSt = new JComboBox();
		coboxSt.setBackground(new Color(255, 204, 153));
		coboxSt.setModel(new DefaultComboBoxModel(new String[] {"Available", "Unavailable"}));
		coboxSt.setFont(new Font("Tahoma", Font.BOLD, 20));
		coboxSt.setBounds(207, 116, 206, 28);
		frame.getContentPane().add(coboxSt);
		
		JButton btnDone = new JButton("Done");
		btnDone.setForeground(new Color(255, 255, 255));
		btnDone.setBackground(new Color(51, 102, 204));
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection(url,userName,password);
					String avi = coboxSt.getSelectedItem().toString();
					String qry = "update item set States= "+"'"+avi+"'"+" where ID="+textField.getText();
					PreparedStatement ps = con.prepareStatement(qry);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successfull!");
					Cashier window = new Cashier();
					window.main(null);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDone.setBounds(289, 192, 148, 35);
		frame.getContentPane().add(btnDone);
		
		JLabel lblNewLabel_2 = new JLabel("State");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(21, 116, 132, 35);
		frame.getContentPane().add(lblNewLabel_2);
		
		
	}
}
