package devAdmin.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AddItem {

	private JFrame frame;
	private JTextField itemnamelb;
	private JTextField pricelbl;
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;
	String s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItem window = new AddItem();
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
	public AddItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void imageresize(String path) {
		ImageIcon background = new ImageIcon(path);
		Image image = background.getImage();
		Image resi= image.getScaledInstance( 202, 148,Image.SCALE_SMOOTH);
		background = new ImageIcon(resi);
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(176, 224, 230));
		frame.setBounds(600, 75, 450, 599);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel itemname = new JLabel("Item Name");
		itemname.setFont(new Font("Tahoma", Font.BOLD, 18));
		itemname.setBounds(42, 86, 112, 39);
		frame.getContentPane().add(itemname);
		
		itemnamelb = new JTextField();
		itemnamelb.setFont(new Font("Tahoma", Font.BOLD, 16));
		itemnamelb.setHorizontalAlignment(SwingConstants.RIGHT);
		itemnamelb.setBounds(172, 92, 203, 31);
		frame.getContentPane().add(itemnamelb);
		itemnamelb.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(42, 141, 143, 39);
		frame.getContentPane().add(lblPrice);
		
		pricelbl = new JTextField();
		pricelbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		pricelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pricelbl.setColumns(10);
		pricelbl.setBounds(172, 147, 203, 31);
		frame.getContentPane().add(pricelbl);
		JLabel imagelbl = new JLabel("");
		imagelbl.setBackground(new Color(176, 224, 230));
		imagelbl.setBounds(172, 206, 202, 148);
		frame.getContentPane().add(imagelbl);
		
		JButton addimgebtn = new JButton("Add Image");
		addimgebtn.setForeground(Color.WHITE);
		addimgebtn.setBackground(new Color(0, 0, 205));
		addimgebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechoos = new JFileChooser();
				filechoos.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE","jpg","gif","png");
				filechoos.addChoosableFileFilter(filter);
				int result = filechoos.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					File select = filechoos.getSelectedFile();
					String path = select.getAbsolutePath();
					imagelbl.setIcon(new ImageIcon(path));
					s=path.replace("F:\\Project Workspace\\mini project\\image\\", "");
					
					System.out.println(s);
					
				}
			}
		});
		addimgebtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		addimgebtn.setBounds(172, 364, 203, 39);
		frame.getContentPane().add(addimgebtn);
		
		JButton btncll = new JButton("Clear");
		btncll.setForeground(Color.WHITE);
		btncll.setBackground(new Color(0, 100, 0));
		btncll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pricelbl.setText(null);
				itemnamelb.setText(null);
				imagelbl.setIcon(null);
			}
		});
		btncll.setFont(new Font("Tahoma", Font.BOLD, 15));
		btncll.setBounds(49, 475, 105, 39);
		frame.getContentPane().add(btncll);
		
		JButton btnAdditem = new JButton("Add Item");
		btnAdditem.setForeground(Color.WHITE);
		btnAdditem.setBackground(new Color(0, 0, 205));
		btnAdditem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					con = DriverManager.getConnection(url,userName,password);
					PreparedStatement ps = con.prepareStatement("insert into item values(?,?,?,?,?)");
					InputStream is;
					
					ps.setInt(1, 0);
					ps.setString(2,itemnamelb.getText() );
					ps.setString(3, pricelbl.getText());
					ps.setString(4, "Available");
					ps.setString(5, s);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Success!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
				Admin window = new Admin();
				window.main(null);
				
			}
		});
		btnAdditem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdditem.setBounds(172, 475, 105, 39);
		frame.getContentPane().add(btnAdditem);
		
		JButton exit = new JButton("Exit");
		exit.setForeground(Color.WHITE);
		exit.setBackground(new Color(255, 0, 0));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Admin window = new Admin();
				window.main(null);
			}
		});
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		exit.setBounds(294, 475, 105, 39);
		frame.getContentPane().add(exit);
		
		JLabel lblNewLabel = new JLabel("Add New Item");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(131, 10, 232, 39);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
