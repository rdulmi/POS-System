package devCashier.com;
import java.sql.*;
import java.util.*;
import java.util.Date;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dev.com.Login;
import devUPitemS.com.UpdateItemState;


import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Cashier {

	private JFrame frame;
	private JTable table;
	private JTextField totlaP;
	private JTextField cash;
	private JTextField change;
	private JTable table_5;
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;
	java.sql.Date today;
	java.util.Date thisdate;
	private JTextField itemTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cashier window = new Cashier();
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
	public Cashier() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/*==================cost function=================================*/
	public void costTotal() {
		double sum = 0;
		for(int i=0; i<table.getRowCount();i++) {
			sum = sum + Double.parseDouble(table.getValueAt(i, 2).toString());
		}
		totlaP.setText(Double.toString(sum));
		
	}

	/*==================change function=================================*/
	public void changet() {
		double sum= 0;
		double cashw = Double.parseDouble(cash.getText().toString());
		for(int i=0; i<table.getRowCount();i++) {
			sum = sum+Double.parseDouble(table.getValueAt(i,2 ).toString());
		}
		double changec = (cashw -sum);
		String changeww = String.format("%.2f", changec);
		change.setText(changeww);
	}
	public void itemTotal() {
		int sum = 0;
		for(int i= 0; i<table.getRowCount();i++) {
			sum = sum + Integer.parseInt((String) table.getValueAt(i, 1));
			
		}
		itemTotal.setText(Integer.toString(sum));
	}
	
	public void initialize() {
		frame = new JFrame("ICE-CREAM SHOP");
		frame.getContentPane().setBackground(new Color(112, 128, 144));
		frame.setBounds(0, 10, 1425, 750);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(10, 73, 354, 411);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn2 = new JButton("2");
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn2.getText());
				}else {
					enternum = cash.getText() + btn2.getText();
					cash.setText(enternum);
				}
			}
		});
		
		btn2.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn2.setBounds(139, 42, 85, 76);
		panel.add(btn2);
		
		JButton btn1 = new JButton("1");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn1.getText());
				}else {
					enternum = cash.getText() + btn1.getText();
					cash.setText(enternum);
				}
			}
		});
		btn1.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn1.setBounds(29, 42, 85, 76);
		panel.add(btn1);
		
		JButton btn3 = new JButton("3");
		btn3.setBackground(Color.BLACK);
		btn3.setForeground(Color.WHITE);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn3.getText());
				}else {
					enternum = cash.getText() + btn3.getText();
					cash.setText(enternum);
				}
			}
		});
		btn3.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn3.setBounds(245, 42, 85, 76);
		panel.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.setBackground(Color.BLACK);
		btn4.setForeground(Color.WHITE);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn4.getText());
				}else {
					enternum = cash.getText() + btn4.getText();
					cash.setText(enternum);
				}
			}
		});
		btn4.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn4.setBounds(29, 128, 85, 76);
		panel.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setBackground(Color.BLACK);
		btn5.setForeground(Color.WHITE);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn5.getText());
				}else {
					enternum = cash.getText() + btn5.getText();
					cash.setText(enternum);
				}
			}
		});
		btn5.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn5.setBounds(139, 128, 85, 76);
		panel.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setBackground(Color.BLACK);
		btn6.setForeground(Color.WHITE);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn6.getText());
				}else {
					enternum = cash.getText() + btn6.getText();
					cash.setText(enternum);
				}
			}
		});
		btn6.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn6.setBounds(245, 128, 85, 76);
		panel.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.setForeground(Color.WHITE);
		btn7.setBackground(Color.BLACK);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn7.getText());
				}else {
					enternum = cash.getText() + btn7.getText();
					cash.setText(enternum);
				}
			}
		});
		btn7.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn7.setBounds(29, 214, 85, 76);
		panel.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setBackground(Color.BLACK);
		btn8.setForeground(Color.WHITE);
		btn8.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn8.getText());
				}else {
					enternum = cash.getText() + btn8.getText();
					cash.setText(enternum);
				}
			}
		});
		btn8.setBounds(139, 214, 85, 76);
		panel.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setForeground(Color.WHITE);
		btn9.setBackground(Color.BLACK);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn9.getText());
				}else {
					enternum = cash.getText() + btn9.getText();
					cash.setText(enternum);
				}
			}
		});
		btn9.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn9.setBounds(245, 214, 85, 76);
		panel.add(btn9);
		
		JButton btnc = new JButton("C");
		btnc.setForeground(Color.WHITE);
		btnc.setBackground(new Color(139, 0, 0));
		btnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash.setText(null);
				change.setText(null);
			}
		});
		btnc.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnc.setBounds(245, 300, 85, 76);
		panel.add(btnc);
		
		JButton btn0 = new JButton("0");
		btn0.setBackground(Color.BLACK);
		btn0.setForeground(Color.WHITE);
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enternum= cash.getText();
				if(enternum =="") {
					cash.setText(btn0.getText());
				}else {
					enternum = cash.getText() + btn0.getText();
					cash.setText(enternum);
				}
			}
		});
		btn0.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn0.setBounds(29, 300, 85, 76);
		panel.add(btn0);
		
		JButton btndot = new JButton(".");
		btndot.setBackground(Color.BLACK);
		btndot.setForeground(Color.WHITE);
		btndot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(! cash.getText().contains(".")) {
					cash.setText(cash.getText() + btndot.getText());
				}
			}
		});
		btndot.setFont(new Font("Tahoma", Font.BOLD, 25));
		btndot.setBounds(139, 300, 85, 76);
		panel.add(btndot);
		
		
		ImageIcon background = new ImageIcon("D:\\Workplace\\mini project3\\image\\1.jpg");
		Image image = background.getImage();
		Image resi= image.getScaledInstance(190, 169,Image.SCALE_SMOOTH);
		background = new ImageIcon(resi);
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(112, 128, 144));
		panel_2.setBounds(0, 496, 1476, 204);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 10, 382, 184);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblTotal.setBounds(10, 106, 124, 29);
		panel_3.add(lblTotal);
		
		totlaP = new JTextField();
		totlaP.setHorizontalAlignment(SwingConstants.RIGHT);
		totlaP.setFont(new Font("Tahoma", Font.BOLD, 15));
		totlaP.setColumns(10);
		totlaP.setBounds(153, 106, 219, 29);
		panel_3.add(totlaP);
		
		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTotalItems.setBounds(10, 45, 136, 29);
		panel_3.add(lblTotalItems);
		
		itemTotal = new JTextField();
		itemTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		itemTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		itemTotal.setBounds(153, 45, 219, 29);
		panel_3.add(itemTotal);
		itemTotal.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(413, 10, 523, 184);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblPayMethod = new JLabel("Payment Method");
		lblPayMethod.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblPayMethod.setBounds(10, 15, 228, 31);
		panel_4.add(lblPayMethod);
		
		JLabel lblCash = new JLabel("Cash");
		lblCash.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblCash.setBounds(10, 76, 141, 31);
		panel_4.add(lblCash);
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblChange.setBounds(10, 143, 141, 31);
		panel_4.add(lblChange);
		
		cash = new JTextField();
		cash.setHorizontalAlignment(SwingConstants.RIGHT);
		cash.setFont(new Font("Tahoma", Font.BOLD, 14));
		cash.setBounds(233, 76, 280, 31);
		panel_4.add(cash);
		cash.setColumns(10);
		
		change = new JTextField();
		change.setHorizontalAlignment(SwingConstants.RIGHT);
		change.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		change.setColumns(10);
		change.setBounds(233, 143, 280, 31);
		panel_4.add(change);
		
		JComboBox cmBox = new JComboBox();
		cmBox.setFont(new Font("Tahoma", Font.BOLD, 23));
		cmBox.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Master Card", "Visa Card"}));
		cmBox.setBounds(233, 15, 280, 31);
		panel_4.add(cmBox);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBounds(957, 10, 471, 184);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setForeground(Color.WHITE);
		btnPay.setBackground(new Color(51, 102, 0));
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(totlaP.getText().length()==0) {

					JOptionPane.showMessageDialog(null, "Please Place the Order!");
				}else {
					if(cmBox.getSelectedItem().equals("Cash")) {
						if(cash.getText().length()==0) {
							JOptionPane.showMessageDialog(null, "Cash Field is Empty!");
						}else {
							changet();
							UserDtails wind = new UserDtails();
							wind.main(null);
						}
						
					
						
					}else {
						change.setText(null);
						cash.setText(null);
						Payment p = new Payment();
						p.main(null);
						
						
						
					}
					
				}
				
				
			}
		});
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPay.setBounds(10, 10, 169, 46);
		panel_5.add(btnPay);
		
		JButton reset = new JButton("Reset");
		reset.setForeground(new Color(255, 255, 255));
		reset.setBackground(new Color(255, 204, 0));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change.setText(null);
				cash.setText(null);
				totlaP.setText(null);
				itemTotal.setText(null);
				DefaultTableModel model1 = (DefaultTableModel)table.getModel();
				model1.setRowCount(0);;
			}
		});
		reset.setFont(new Font("Tahoma", Font.BOLD, 20));
		reset.setBounds(233, 10, 169, 46);
		panel_5.add(reset);
		
		JButton print = new JButton("Print");
		print.setForeground(Color.WHITE);
		print.setBackground(new Color(51, 153, 0));
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(totlaP.getText()!="") {
					MessageFormat header = new MessageFormat("=========Welcome Come Again!========");
					MessageFormat footer = new MessageFormat("=========Thank You!========");
					try {
						table.print(JTable.PrintMode.NORMAL,header,footer);
					}catch(Exception e1) {
						System.out.println("Error:"+e1);
					}
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Please fill", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		print.setFont(new Font("Tahoma", Font.BOLD, 20));
		print.setBounds(10, 78, 169, 46);
		panel_5.add(print);
		
		JButton remove = new JButton("Remove");
		remove.setForeground(Color.WHITE);
		remove.setBackground(new Color(204, 51, 0));
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int remove = table.getSelectedRow();
				if(remove>=0) {
					model.removeRow(remove);
				}
				costTotal();
				itemTotal();
			}
		});
		remove.setFont(new Font("Tahoma", Font.BOLD, 20));
		remove.setBounds(233, 78, 169, 46);
		panel_5.add(remove);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login outlg = new Login();
				outlg.main(null);
				frame.setVisible(false);
				
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(165, 42, 42));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(10, 135, 391, 46);
		panel_5.add(btnExit);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(736, 73, 624, 364);
		frame.getContentPane().add(scrollPane1);
		table_5 = new JTable();
		table_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		Query qr = new Query();
		ArrayList<Item> list = qr.BindTable();
		String[] columns = {"ID", "Item Image", "Item Name", "Price", "Item State"};
		Object[][]  raws = new Object[list.size()][5];
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
			String price = list.get(i).getPrice();
			raws[i][3] = "        " + price;
			String state = list.get(i).getState().toString();
			raws[i][4]="           " + state;
		}
		table_5.setModel(new DefaultTableModel(
			raws,
			columns
		));
		table_5.getColumnModel().getColumn(0).setPreferredWidth(25);
		table_5.getColumnModel().getColumn(1).setPreferredWidth(125);
		table_5.getColumnModel().getColumn(3).setPreferredWidth(45);
		table_5.getColumnModel().getColumn(4).setPreferredWidth(100);
		table_5.setBounds(10, 10, 719, 370);
		table_5.getColumnModel().getColumn(1).setCellRenderer(new ImageRander());
		table_5.setCellSelectionEnabled(true);
		table_5.setRowHeight(125);
		scrollPane1.setViewportView(table_5);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 73, 346, 411);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));
		table.setModel(new DefaultTableModel(new Object[][] {  },new String[] {"Items", "Quantity", "Amount"}));
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(0).setMinWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		scrollPane.setViewportView(table);
		
		JButton update = new JButton("Update Item State");
		update.setForeground(Color.WHITE);
		update.setBackground(new Color(0, 0, 204));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				UpdateItemState up = new UpdateItemState();
				up.main(null);
			}
		});
		update.setBounds(1104, 449, 256, 35);
		frame.getContentPane().add(update);
		update.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JButton addItem = new JButton("Add Item");
		addItem.setForeground(Color.WHITE);
		addItem.setBackground(new Color(0, 0, 255));
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table_5.getModel();
				int selectraw = table_5.getSelectedRow();
				
					
					String name = model.getValueAt(selectraw, 2).toString();
					String amount = model.getValueAt(selectraw, 3).toString();
					DefaultTableModel model1 = (DefaultTableModel)table.getModel();
					model1.addRow(new Object[] {name,"1",amount});
					costTotal() ;
					itemTotal();
					java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
					
					
					try {
						con = DriverManager.getConnection(url,userName,password);
						PreparedStatement ps = con.prepareStatement("insert into profit values(?,?,?,?)");
						ps.setString(1, name);
						ps.setString(2,amount );
						ps.setInt(3, 1);
						ps.setDate(4, sqlDate);
						ps.executeUpdate();
						
						
						
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				
				
				
				
				
			}
		});
		addItem.setFont(new Font("Tahoma", Font.BOLD, 17));
		addItem.setBounds(736, 448, 256, 35);
		frame.getContentPane().add(addItem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 128, 144));
		panel_1.setBounds(0, 0, 1486, 40);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scoop By Spot");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(700, 0, 192, 40);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JLabel lblNewLabel_3 = new JLabel("Item List");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(1020, 32, 199, 35);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Order");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3_1.setBounds(529, 40, 92, 35);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		// this file path should be changed
		ImageIcon background1 =new ImageIcon("F:\\Project Workspace\\mini project\\image\\newlog.jpeg");
		Image image2 = background1.getImage();
		Image resi1= image2.getScaledInstance(45, 35,Image.SCALE_SMOOTH);
		background1 = new ImageIcon(resi);
		
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
