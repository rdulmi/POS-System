package dev.com;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import devCashier.com.Item;

public class Qury {
	private String url = "jdbc:mysql://127.0.0.1:3306/itemlist";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "";
	private java.sql.Connection con = null;
			
	public ArrayList<Item>EindTable() {
		ArrayList<Item> lists = new ArrayList();
		
		try {
			con = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Statement st;
		ResultSet rs;
		
		try {
			String query ="SELECT UserName,Password FROM user";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				 User us = new  User(rs.getString("UserName"),rs.getString("Password"));
				//lists.addAll(us);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lists;
		
		
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
