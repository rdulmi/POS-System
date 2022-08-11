package dev.com;

public class User {
	
	private String username;
	private String passwordU;
	
	public User(String itemname,String passwordU) {
		// TODO Auto-generated constructor stub
	
	this.username = itemname;
	this.passwordU = passwordU;
	
	}
	
	public void setUsername() {
		this.username= username;
	}
	public String getUsername()  {
		return  username ;
	}
	public void setPasswordU() {
		this.passwordU= passwordU;
	}
	public String getPasswordU()  {
		return  passwordU ;
	}

}
