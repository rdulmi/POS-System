package devCashier.com;

public class Item {
	private int id;
	private String itemname;
	private String price;
	private String state;
	private String picture;
	public Item() {
		// TODO Auto-generated constructor stub
	
	}

	
	public Item(int id,String itemname,String price,String state,String picture) {
			// TODO Auto-generated constructor stub
		this.id = id;
		this.itemname = itemname;
		this.price = price;
		this.state = state;
		this.picture = picture;
		}

	public void setID() {
		this.id= id;
	}
	public int getID() {
		return  id ;
	}
	public void setItemname() {
		this.itemname = itemname;
		
	}
	public String getItemname() {
		return itemname;
	}
	public void setPrice() {
		this.price = price;

	}
	public String getPrice() {
		return price;
	}
	public void setState() {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setPicture() {
		this.picture = picture;
	}
	public String getPicture() {
		return picture;
	}

}
