package application.models;

import application.abstractClasses.Part;

public class InHouse extends Part {
	
	int machineID;

	public InHouse(String name, double price, int stock, int min, int max, int machineID) {
		super(name, price, stock, min, max);
		this.machineID = machineID;
	}

	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
}
