/**********************************************
Workshop #4&5
Course:<APD 545> - Fall 2024
Last Name: Mohammed
First Name: Adnan
ID: 174731216
Section: ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date: 15th November 2024
**********************************************/

package application.models;

import application.abstractClasses.Part;

public class InHouse extends Part {
	
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
