package devCashier.com;
import java.util.ArrayList;
import java.sql.*;

public class Query {
	private String url = "jdbc:mysql://127.0.0.1:3306/items";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;
	
	public ArrayList<Item>BindTable() {
		ArrayList<Item> list = new ArrayList();
		
		try {
			con = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Statement st;
		ResultSet rs;
		
		try {
			String query ="SELECT ID,ItemName,Price,States,Picture FROM item";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Item item = new Item(rs.getInt("ID"),rs.getString("ItemName"),rs.getString("Price"),rs.getString("States"),rs.getString("Picture"));
				list.add(item);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
		
	}

	void getConnection() { 
		
		try {
			con= DriverManager.getConnection(url,userName,password);
			System.out.println("Success!");
			
		} catch (SQLException e) {
			System.out.println("Database Connection Faild");
			e.printStackTrace();
		}

	     
	}

}
