package application.models;

import java.io.Serializable;

import application.abstractClasses.Part;

public class InHouse extends Part implements Serializable {
	private static final long serialVersionUID = 1L;
	int machineID;

	public InHouse(String name, double price, int stock, int min, int max, int machineID) {
		super(name, price, stock, min, max);
		this.machineID = machineID;
	}
	
	public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
		super(id, name, price, stock, min, max);
		this.machineID = machineID;
	}
	
	@Override
	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
}
