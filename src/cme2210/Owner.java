package cme2210;

import java.util.ArrayList;
import java.util.HashMap;

public class Owner extends User {
	private String name;
	private String phone;
	private String address;
	private String email;
	//ownerProperties can be Hash later**
	public HashMap<String,Property> ownerProperties = new HashMap<>();
	public Owner(String ID, String username, String password,String name, String phone, String email, String address)  {
		// TODO Auto-generated constructor stub
		super(ID,username,password);
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}
	public void addOwnerProperty (Property data) {
		this.ownerProperties.put(data.getID(),data);
	}
	public String getName() {
		return name;
	}


	public void setName(String name)  {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone)  {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
