package devAdmin.com;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class SalesDetail {

	private JFrame frame;
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;
	DefaultTableModel dm = new DefaultTableModel ();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesDetail window = new SalesDetail();
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
	public SalesDetail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private DefaultTableModel getData() {
		dm.addColumn("ItemName");
		dm.addColumn("Price");
		dm.addColumn("Quentity");
		dm.addColumn("Day");
		
		String sql ="SELECT ItemName, Price, Qty, Day FROM profit";
		
		try {
			con = DriverManager.getConnection(url, userName, password);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String ItemName = rs.getString(1);
				String Price = rs.getString(2);
				String Quentity = rs.getString(3);
				String Day = rs.getString(4);
				
				String[] rawdata = {ItemName,Price,Quentity,Day};
				dm.addRow(rawdata);
				
			}return dm;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	private String getCellValue(int x,int y) {
		return dm.getValueAt(x, y).toString();
		
	}
	

	public void writexcel() {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		SXSSFSheet ws = wb.createSheet();
		
		TreeMap<String,Object[]> data = new TreeMap<>();
		data.put("-1", new Object[] {dm.getColumnName(0),dm.getColumnName(1),dm.getColumnName(2),dm.getColumnName(3)});
		for (int i =0;i<dm.getRowCount();i++) {
			data.put(Integer.toString(i),new Object[] {getCellValue(i,0),getCellValue(i,1),getCellValue(i,2),getCellValue(i,3)});
		}
		Set<String> ids = data.keySet();
		SXSSFRow row;
		int rowID =0;
		for(String key:ids) {
			row = ws.createRow(rowID++);
			Object[] values =data.get(key);
			int cellID=0;
			for(Object o: values) {
				Cell cell =row.createCell(cellID++);
				cell.setCellValue(o.toString());
				
			}
		}
		
		try {
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			FileOutputStream fos = new FileOutputStream(new File("F:\\Project Workspace\\mini project\\excel\\Salsedetails"+sqlDate+".xlsx"));
			wb.write(fos);
			fos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 957, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(764, 0, 183, 613);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Export");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writexcel();
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 100, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(10, 494, 163, 37);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(128, 0, 0));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCancel.setBounds(10, 541, 163, 37);
		panel.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 749, 603);
		frame.getContentPane().add(scrollPane);
		JTable  table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setModel(getData());
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
	
	}

}
