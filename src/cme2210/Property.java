package cme2210;

public class Property {
	private int sqrMeter,price,bedrooms,bathrooms,age;
	private boolean balcony,garage,garden,pool;
	private	String propType;
	private String ID,ownerID,address,description;
	public Property(String ID,String ownerID,String propType,int sqrMeter, int price, int bedrooms, int bathrooms, int age, boolean balcony, boolean garage,
			boolean garden, boolean pool,  String address, String description) {
		this.ID = ID;
		this.sqrMeter = sqrMeter;
		this.price = price;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.age = age;
		this.balcony = balcony;
		this.garage = garage;
		this.garden = garden;
		this.pool = pool;
		this.propType = propType;
		this.ownerID = ownerID;
		this.address = address;
		this.description = description;
	}
	public String getID() {
		return ID;
	}
	public int getSqrMeter() {
		return sqrMeter;
	}
	public void setSqrMeter(int sqrMeter) {
		this.sqrMeter = sqrMeter;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public int getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isBalcony() {
		return balcony;
	}
	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}
	public boolean isGarage() {
		return garage;
	}
	public void setGarage(boolean garage) {
		this.garage = garage;
	}
	public boolean isGarden() {
		return garden;
	}
	public void setGarden(boolean garden) {
		this.garden = garden;
	}
	public boolean isPool() {
		return pool;
	}
	public void setPool(boolean pool) {
		this.pool = pool;
	}
	public String getPropType() {
		return propType;
	}
	public void setPropType(String propType) {
		this.propType = propType;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
