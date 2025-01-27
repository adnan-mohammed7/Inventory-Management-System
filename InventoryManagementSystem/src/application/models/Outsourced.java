package application.models;

import java.io.Serializable;

import application.abstractClasses.Part;

public class Outsourced extends Part implements Serializable{
	private static final long serialVersionUID = 1L;
	private String companyName;

	public Outsourced(String name, double price, int stock, int min, int max, String company) {
		super(name, price, stock, min, max);
		this.companyName = company;
	}

	public Outsourced(int id, String name, double price, int stock, int min, int max, String company) {
		super(id, name, price, stock, min, max);
		this.companyName = company;
	}

	
	@Override
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
