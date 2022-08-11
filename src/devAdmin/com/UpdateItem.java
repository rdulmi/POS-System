package devAdmin.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class UpdateItem {
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;

	private JFrame frame;
	private JTextField idfi;
	private JTextField pricefi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateItem window = new UpdateItem();
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
	public UpdateItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(176, 224, 230));
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(37, 86, 146, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(37, 158, 151, 41);
		frame.getContentPane().add(lblNewLabel_1);
		
		idfi = new JTextField();
		idfi.setHorizontalAlignment(SwingConstants.RIGHT);
		idfi.setFont(new Font("Tahoma", Font.BOLD, 20));
		idfi.setBounds(178, 86, 190, 41);
		frame.getContentPane().add(idfi);
		idfi.setColumns(10);
		
		pricefi = new JTextField();
		pricefi.setHorizontalAlignment(SwingConstants.RIGHT);
		pricefi.setFont(new Font("Tahoma", Font.BOLD, 20));
		pricefi.setColumns(10);
		pricefi.setBounds(178, 158, 190, 41);
		frame.getContentPane().add(pricefi);
		
		JButton backbtn = new JButton("Back");
		backbtn.setForeground(Color.WHITE);
		backbtn.setBackground(new Color(0, 128, 0));
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Admin window = new Admin();
				window.main(null);
			}
		});
		backbtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		backbtn.setBounds(122, 248, 114, 41);
		frame.getContentPane().add(backbtn);
		
		JButton donebtn = new JButton("Done");
		donebtn.setForeground(Color.WHITE);
		donebtn.setBackground(new Color(0, 0, 255));
		donebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idfi.getText().length()!=0 && pricefi.getText().length()!=0 ) {
					try {
						con = DriverManager.getConnection(url,userName,password);
						String query ="UPDATE item SET Price= "+pricefi.getText()+ " WHERE ID="+idfi.getText();
						PreparedStatement ps = con.prepareStatement(query);
						ps.executeUpdate();
						JOptionPane.showConfirmDialog(null, "Success Update");
					} catch (SQLException e1) {
						
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.setVisible(false);
					Admin window = new Admin();
					window.main(null);
				}else {
					JOptionPane.showMessageDialog(null, "Both Fields are Required!");
				}
				
				
			}
		});
		donebtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		donebtn.setBounds(277, 248, 114, 41);
		frame.getContentPane().add(donebtn);
		
		JLabel lblNewLabel_2 = new JLabel("Update Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(159, 23, 232, 41);
		frame.getContentPane().add(lblNewLabel_2);
	}

}
