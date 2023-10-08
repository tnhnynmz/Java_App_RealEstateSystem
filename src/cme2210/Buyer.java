package cme2210;

public class Buyer extends User {
	private String name, phone, email, address;

	public Buyer(String ID,String username, String password, String name, String phone, String email, String address) {
		super(ID,username, password);
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
