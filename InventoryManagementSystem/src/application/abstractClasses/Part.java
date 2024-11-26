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

package application.abstractClasses;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Part implements Serializable {
	private static final long serialVersionUID = 1L;
	public static AtomicInteger counter = new AtomicInteger(1);
	int id;
	String name;
	double price;
	int stock;
	int min;
	int max;
	public Part(String name, double price, int stock, int min, int max){
		this.id = counter.getAndIncrement();
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
	}
	
	public Part(int id, String name, double price, int stock, int min, int max){
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMachineID() {
		return 0;
	}
	public String getCompanyName() {
		return "";
	}
}
