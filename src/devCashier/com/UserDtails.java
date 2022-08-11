package devCashier.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class UserDtails {

	private JFrame frame;
	private JTextField pnnum;
	private JTextField email;
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDtails window = new UserDtails();
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
	public UserDtails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 153));
		frame.setBounds(850, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone Number");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(21, 93, 156, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(21, 149, 156, 32);
		frame.getContentPane().add(lblEmail);
		name = new JTextField();
		name.setBackground(new Color(204, 255, 153));
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setFont(new Font("Tahoma", Font.BOLD, 15));
		name.setColumns(10);
		name.setBounds(187, 41, 228, 32);
		frame.getContentPane().add(name);
		
		pnnum = new JTextField();
		pnnum.setBackground(new Color(204, 255, 153));
		pnnum.setHorizontalAlignment(SwingConstants.RIGHT);
		pnnum.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnnum.setBounds(187, 96, 228, 32);
		frame.getContentPane().add(pnnum);
		pnnum.setColumns(10);
		
		email = new JTextField();
		email.setBackground(new Color(204, 255, 153));
		email.setHorizontalAlignment(SwingConstants.RIGHT);
		email.setFont(new Font("Tahoma", Font.BOLD, 15));
		email.setColumns(10);
		email.setBounds(187, 152, 228, 32);
		frame.getContentPane().add(email);
		
		JButton btnclc = new JButton("Clear");
		btnclc.setForeground(new Color(255, 255, 255));
		btnclc.setBackground(new Color(51, 153, 51));
		btnclc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email.setText(null);
				pnnum.setText(null);
				name.setText(null);
			}
		});
		btnclc.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnclc.setBounds(64, 199, 117, 36);
		frame.getContentPane().add(btnclc);
		
		JButton btnDone = new JButton("Done");
		btnDone.setForeground(new Color(255, 255, 255));
		btnDone.setBackground(new Color(0, 0, 204));
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().length()==0 && pnnum.getText().length()==0 && email.getText().length()==0) {
					frame.setVisible(false);
				}else {
					try {
						con = DriverManager.getConnection(url,userName,password);
						PreparedStatement ps = con.prepareStatement("insert into userdetal values(?,?,?)");
						ps.setString(1,pnnum.getText());
						ps.setString(2,email.getText());
						ps.setString(3,name.getText());
						ps.executeUpdate();
						frame.setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDone.setBounds(253, 199, 117, 36);
		frame.getContentPane().add(btnDone);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName.setBounds(21, 38, 156, 32);
		frame.getContentPane().add(lblName);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(129, 0, 214, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}

}
