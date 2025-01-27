package application.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import application.abstractClasses.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Products implements Serializable {
	private static final long serialVersionUID = 1L;
	public static AtomicInteger counter = new AtomicInteger(1);
	transient ObservableList<Part> associatedParts;
	int id;
	String name;
	double price;
	int stock;
	int min;
	int max;
	
	public Products(int id, String name, double price, int stock, int min, int max) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
		associatedParts = FXCollections.observableArrayList();
	}
	
	public Products(String name, double price, int stock, int min, int max) {
		this.id = counter.getAndIncrement();
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
		associatedParts = FXCollections.observableArrayList();
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
	
	public void addAssociatedPart(Part part) {
		associatedParts.add(part);
	}
	
	public boolean deleteAssociatedPart(Part part) {
		return associatedParts.remove(part);
	}
	
	public ObservableList<Part> getAllAssociatedParts(){
		return this.associatedParts;
	}
	
	
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(new ArrayList<>(associatedParts));
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        @SuppressWarnings("unchecked")
		List<Part> partsList = (List<Part>) ois.readObject();
        this.associatedParts = FXCollections.observableArrayList(partsList);
    }
}
