package cme2210;


public class User {
	private String username;
	private String password, ID;
	public User(String ID,String username,String password) {
		this.ID = ID;
		this.username = username;
		this.password = password;
	}
	
	public User() {
		this.ID = null;
		this.password = null;
		this.username = null;
	}

	public char getPermissionType(){
		return this.ID.charAt(0);
	}
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean verifyLogin(String username, String password) {
		if (this.username == username && this.password == password) {
			return true;
		}else {
			return false;
		}
	}

	
}
