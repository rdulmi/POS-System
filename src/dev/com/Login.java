package dev.com;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

import devAdmin.com.Admin;
import devCashier.com.Cashier;



public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField txtpassword;
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String passwords = "";
	private java.sql.Connection con = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(400, 200, 618, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(243, 10, 112, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel username = new JLabel("Username");
		username.setFont(new Font("Tahoma", Font.BOLD, 14));
		username.setBounds(40, 67, 79, 13);
		frame.getContentPane().add(username);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.BOLD, 14));
		password.setBounds(40, 115, 72, 13);
		frame.getContentPane().add(password);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(129, 64, 226, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		txtpassword.setBounds(127, 112, 228, 19);
		frame.getContentPane().add(txtpassword);
		
		JButton resetbtn = new JButton("RESET");
		resetbtn.setBackground(Color.BLUE);
		resetbtn.setForeground(Color.WHITE);
		resetbtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpassword.setText(null);
				textField.setText(null);
			}
		});
		resetbtn.setBounds(152, 188, 85, 31);
		frame.getContentPane().add(resetbtn);
		
		JButton btnLoin = new JButton("LOGIN");
		btnLoin.setForeground(Color.WHITE);
		btnLoin.setBackground(new Color(0, 100, 0));
		btnLoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String username = textField.getText();
				String password = txtpassword.getText();
				try {
					if(username.contains("Admin")) {
						
						con = DriverManager.getConnection(url,userName,passwords);
						String sql = "SELECT * FROM userlogin WHERE UserName=? AND Password=?";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, username);
						ps.setString(2, password);
						ResultSet st = ps.executeQuery();
						if(st.next()) {
							
							frame.setVisible(false);
							Admin windo = new Admin();
							windo.main(null);
						}else {
							JOptionPane.showMessageDialog(null, "Unmached Username And Password");
							textField.setText(null);
							txtpassword.setText(null);
						}
						
						
					}if(username.contains("Cashier") ) {
						con = DriverManager.getConnection(url,userName,passwords);
						String sql = "SELECT * FROM userlogin WHERE UserName=? AND Password=?";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, username);
						ps.setString(2, password);
						ResultSet st = ps.executeQuery();
						if(st.next()) {
							
							frame.setVisible(false);
							Cashier wind = new Cashier();
							wind.main(null);
						}else {
							JOptionPane.showMessageDialog(null, "Unmached Username And Password");
							textField.setText(null);
							txtpassword.setText(null);
						}
						
						
					}
					
				}catch(Exception e1) {
					JOptionPane.showConfirmDialog(null, "Incorrect Password or Username");
					
				}
				
			}
		});
		btnLoin.setBounds(25, 189, 85, 31);
		frame.getContentPane().add(btnLoin);
		
		JButton cancelbtn = new JButton("EXIT");
		cancelbtn.setForeground(Color.WHITE);
		cancelbtn.setBackground(new Color(139, 0, 0));
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frameExit = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frameExit,"Confirm exit ?...." , "Login System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		cancelbtn.setBounds(270, 189, 85, 31);
		frame.getContentPane().add(cancelbtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 161, 370, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 41, 370, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel NewLabel_1 = new JLabel();
		ImageIcon background =new ImageIcon("F:\\Project Workspace\\mini project\\image\\Login page image.png");
		
		Image image = background.getImage();
		Image resi= image.getScaledInstance(154, 177,Image.SCALE_SMOOTH);
		background = new ImageIcon(resi);
		NewLabel_1.setIcon(background);
		
		NewLabel_1.setBounds(416, 33, 154, 177);
		frame.getContentPane().add(NewLabel_1);
	}

}
