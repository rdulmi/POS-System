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
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class DeleteItem {

	private JFrame frame;
	private JTextField idtxt;
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
					DeleteItem window = new DeleteItem();
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
	public DeleteItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(38, 83, 115, 30);
		frame.getContentPane().add(lblNewLabel);
		
		idtxt = new JTextField();
		idtxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		idtxt.setHorizontalAlignment(SwingConstants.RIGHT);
		idtxt.setBounds(171, 81, 219, 32);
		frame.getContentPane().add(idtxt);
		idtxt.setColumns(10);
		
		JButton backbtn = new JButton("Back");
		backbtn.setForeground(Color.WHITE);
		backbtn.setBackground(new Color(51, 153, 0));
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Admin window = new Admin();
				window.main(null);
			}
		});
		backbtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		backbtn.setBounds(38, 198, 129, 37);
		frame.getContentPane().add(backbtn);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idtxt.getText().length()!=0) {
					if(JOptionPane.showConfirmDialog(null,"Confirm Delete?...." , "Delete Item", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
						try {
							con = DriverManager.getConnection(url,userName,password);
							String query ="DELETE FROM `item` WHERE ID=?";
							PreparedStatement ps = con.prepareStatement(query);
							ps.setString(1, idtxt.getText());
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Successfull Delete!");
							frame.setVisible(false);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Admin window = new Admin();
						window.main(null);
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "ID Field is Empty!");
				}
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(261, 198, 129, 37);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel_1 = new JLabel("Delete Item");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(137, 10, 165, 30);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
