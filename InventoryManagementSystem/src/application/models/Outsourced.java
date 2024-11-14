package application.models;

import application.abstractClasses.Part;

public class Outsourced extends Part{
	
	private String companyName;

	public Outsourced(int id, String name, double price, int stock, int min, int max, String company) {
		super(id, name, price, stock, min, max);
		this.companyName = company;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
