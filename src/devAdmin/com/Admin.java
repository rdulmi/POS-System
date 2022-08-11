package devAdmin.com;
import java.awt.Color;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dev.com.Login;
import devCashier.com.Item;
import devCashier.com.Query;

import javax.swing.SwingConstants;

// click the positive mark in the second line
// image path in lines 268 and 341 should be changed
// open the project folder-> image folder
// copy the path -> paste to the 268 ,341
public class Admin {

	private JFrame frame;
	private JTable table;
	private JTextField username;
	private JPasswordField passwords;
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;
	private JPasswordField conform;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	public Admin() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Admin Panel");
		frame.setBackground(SystemColor.activeCaption);
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(20, 10, 1500, 750);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1360, 50);
		panel.setBackground(Color.ORANGE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Panel");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(605, 1, 218, 40);
		panel.add(lblNewLabel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// logout button function
				Login outlg = new Login();
				outlg.main(null);
				frame.setVisible(false);
			}
		});
		btnLogout.setBackground(Color.RED);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogout.setBounds(1235, 0, 115, 50);
		panel.add(btnLogout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 550, 576);
		frame.getContentPane().add(scrollPane);
		Query qr = new Query();
		ArrayList<Item> list = qr.BindTable();
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		// create columns 
		String[] columns = {"ID", "Item Image", "Item Name", "Price", "Item State"};
		// create raws
		Object[][]  raws = new Object[list.size()][5];
		// get data from the array list
		for(int i=0;i<list.size();i++) {
			int id = list.get(i).getID();
			raws[i][0] = "       " + id;
			if(list.get(i).getPicture()!= null) {			
		    raws[i][1]=list.get(i).getPicture();
			}else {
				raws[i][1]=null;
			}
			
			String name  = list.get(i).getItemname().toString();
			raws[i][2] = "   " + name;
			//raws[i][2]=list.get(i).getItemname();
			String price = list.get(i).getPrice();
			raws[i][3] = "        " + price;
			//raws[i][3]=list.get(i).getPrice();
			String state = list.get(i).getState().toString();
			raws[i][4]="         " + state;
			//raws[i][4]=list.get(i).getState();
		}
		table.setModel(new DefaultTableModel(raws,columns));
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setMinWidth(125);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRander());
		table.setCellSelectionEnabled(true);
		table.setRowHeight(125);
		scrollPane.setViewportView(table);
		
		JButton updatebtn = new JButton("Update Price");
		updatebtn.setForeground(Color.WHITE);
		updatebtn.setBackground(new Color(0, 0, 255));
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create update item button function
				frame.setVisible(false);
				UpdateItem w = new UpdateItem();
				w.main(null);
			}
		});
		updatebtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		updatebtn.setBounds(10, 642, 165, 40);
		frame.getContentPane().add(updatebtn);
		
		JButton additbtn = new JButton("Add New Item");
		additbtn.setForeground(Color.WHITE);
		additbtn.setBackground(new Color(0, 0, 205));
		additbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create add item button function
				frame.setVisible(false);
				AddItem add = new AddItem();
				add.main(null);
			}
		});
		additbtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		additbtn.setBounds(185, 642, 201, 40);
		frame.getContentPane().add(additbtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setForeground(Color.RED);
		panel_1.setBounds(570, 62, 790, 570);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblChangePass = new JLabel("Change Password");
		lblChangePass.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChangePass.setBounds(21, 47, 759, 37);
		panel_1.add(lblChangePass);
		
		JLabel lblUseName = new JLabel("Username");
		lblUseName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUseName.setBounds(57, 128, 691, 37);
		panel_1.add(lblUseName);
		
		JLabel lblNewPass = new JLabel("New Password");
		lblNewPass.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewPass.setBounds(57, 185, 691, 37);
		panel_1.add(lblNewPass);
		
		username = new JTextField();
		username.setHorizontalAlignment(SwingConstants.RIGHT);
		username.setFont(new Font("Tahoma", Font.BOLD, 18));
		username.setBounds(260, 131, 496, 32);
		panel_1.add(username);
		username.setColumns(10);
		
		passwords = new JPasswordField();
		passwords.setHorizontalAlignment(SwingConstants.RIGHT);
		passwords.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwords.setColumns(10);
		passwords.setEchoChar('*');
		passwords.setBounds(260, 188, 496, 32);
		panel_1.add(passwords);
		
		JLabel lblConPass = new JLabel("Confirm Password");
		lblConPass.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConPass.setBounds(57, 244, 691, 37);
		panel_1.add(lblConPass);
		
		conform = new JPasswordField();
		conform.setHorizontalAlignment(SwingConstants.RIGHT);
		conform.setFont(new Font("Tahoma", Font.BOLD, 18));
		conform.setColumns(10);
		conform.setEchoChar('*');

		conform.setBounds(260, 247, 496, 32);
		panel_1.add(conform);
		
		JButton upPass = new JButton("Update Password");
		upPass.setForeground(Color.WHITE);
		upPass.setBackground(new Color(0, 128, 128));
		
		
		upPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create update password function
				//System.out.println(passwords.getText().toString()!="9784");
				//System.out.println();
				///System.out.println();
				//System.out.println(conform.getText().trim());
				String confir = conform.getText();
				String paw = passwords.getText();
				
				
				
				if(passwords.getText().length()==0 && conform.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Please Enter the Password");
				}else if(passwords.getText().length()<=6 && passwords.getText().length()>=4) {
					if(confir.equals(paw)) {

						try {
							// connect to the database
							con = DriverManager.getConnection(url,userName,password);
							// create the update query
							String query ="UPDATE user SET Password= "+"'"+passwords.getText()+"'"+ " WHERE UserName="+"'"+username.getText()+"'";
							PreparedStatement ps = con.prepareStatement(query);
							try {
								ps.executeUpdate();
							}catch(Exception e1) {
								JOptionPane.showMessageDialog(null, e1);
							}
							
							username.setText(null);
							passwords.setText(null);
							JOptionPane.showConfirmDialog(null, "Success Update");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else if(!confir.equals(paw)) {
						JOptionPane.showMessageDialog(null, "Please Enter  Password");
					}
					
					
					
				
				}else if(passwords.getText().length()>=6 || passwords.getText().length()<=4){
					
					JOptionPane.showMessageDialog(null, "Please Check Whether You Have Entered Correct Details And each Fields Should be Filled!");
				}
				
				
			}
		});
		upPass.setFont(new Font("Tahoma", Font.BOLD, 20));
		upPass.setBounds(498, 289, 258, 37);
		panel_1.add(upPass);
		
		JButton btnclc = new JButton("Clear");
		btnclc.setForeground(new Color(255, 255, 255));
		btnclc.setBackground(new Color(128, 0, 0));
		btnclc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create clear button function
				username.setText(null);
				passwords.setText(null);
				conform.setText(null);
			}
		});
		btnclc.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnclc.setBounds(270, 289, 191, 37);
		panel_1.add(btnclc);
		
		JLabel lblinfo1 = new JLabel("*If You Want to Change the Password Please Enter Admin Username as \"Admin\" And Cashier  Username as \"Cashier\" ");
		lblinfo1.setBounds(21, 85, 759, 32);
		panel_1.add(lblinfo1);
		lblinfo1.setBackground(Color.RED);
		lblinfo1.setForeground(new Color(204, 0, 0));
		lblinfo1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel shopIcon = new JLabel("");
		// this file path should be changed
		ImageIcon backg = new ImageIcon("F:\\Project Workspace\\mini project\\image\\Cashier interface heading2.png");
		Image img = backg.getImage();
		Image resi= img.getScaledInstance(263, 209,Image.SCALE_SMOOTH);
		backg = new ImageIcon(resi);
		shopIcon.setIcon(backg);
		shopIcon.setBounds(386, 337, 263, 209);
		panel_1.add(shopIcon);
		
		JLabel lblInfo2 = new JLabel("*Please Enter a Password between 4 and 6 Charactors");
		lblInfo2.setForeground(Color.RED);
		lblInfo2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInfo2.setBounds(260, 218, 520, 20);
		panel_1.add(lblInfo2);
		
		
		
		JButton deletebtn = new JButton("Delete Item");
		deletebtn.setForeground(Color.WHITE);
		deletebtn.setBackground(new Color(128, 0, 0));
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create delete item button function
				frame.setVisible(false);
				DeleteItem win = new DeleteItem();
				win.main(null);
			}
		});
		deletebtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		deletebtn.setBounds(396, 643, 166, 40);
		frame.getContentPane().add(deletebtn);
		
		JButton slsd = new JButton("Download Sales Details");
		slsd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SalesDetail window = new SalesDetail();
				window.main(null);
			}
		});
		slsd.setForeground(Color.WHITE);
		slsd.setBackground(new Color(0, 128, 0));
		slsd.setFont(new Font("Tahoma", Font.BOLD, 20));
		slsd.setBounds(1054, 642, 306, 40);
		frame.getContentPane().add(slsd);
		
		JButton getcus = new JButton("Download Coustomer Details");
		getcus.setFont(new Font("Tahoma", Font.BOLD, 20));
		getcus.setForeground(Color.WHITE);
		getcus.setBackground(new Color(0, 128, 0));
		getcus.setBounds(705, 642, 339, 40);
		frame.getContentPane().add(getcus);
		
		getcus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create customer details button function
				CustomerDe window = new CustomerDe();
					window.main(null);
			}
		});
		
	}
	// table image column Randering function
	// JLabel is added to the image column row and then image is inserted to the JLabel
	private class ImageRander extends DefaultTableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String imageName = value.toString();
			// this file path should be changed
			ImageIcon image = new ImageIcon(new ImageIcon("F:\\Project Workspace\\mini project\\image\\"+imageName).getImage().getScaledInstance(125, 125, Image.SCALE_DEFAULT));
			return new JLabel(image);
		}
		
	}
}
