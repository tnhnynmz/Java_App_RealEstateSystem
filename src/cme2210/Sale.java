package cme2210;

public class Sale {
	private Property propToSale;
	private Buyer saleToBuyer;
	private String ID, finalPrice;
	public Sale(String iD,Property propToSale, Buyer saleToBuyer,  String finalPrice) {
		this.propToSale = propToSale;
		this.saleToBuyer = saleToBuyer;
		ID = iD;
		this.finalPrice = finalPrice;
	}
	public Property getPropToSale() {
		return propToSale;
	}
	public void setPropToSale(Property propToSale) {
		this.propToSale = propToSale;
	}
	public Buyer getSaleToBuyer() {
		return saleToBuyer;
	}
	public void setSaleToBuyer(Buyer saleToBuyer) {
		this.saleToBuyer = saleToBuyer;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}
	
}
