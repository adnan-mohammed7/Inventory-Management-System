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



import java.io.Serializable;

import application.abstractClasses.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory implements Serializable{
	private static final long serialVersionUID = 1L;
	ObservableList<Part> allParts;
	ObservableList<Products> allProducts;
	
	public Inventory() {
		allParts = FXCollections.observableArrayList();
		allProducts = FXCollections.observableArrayList();
	}
	
	public void addPart(Part part) {
		allParts.add(part);
	}
	
	public void addProduct(Products prod) {
		allProducts.add(prod);
	}
	
	public Part searchPartByID(int partId){
		return allParts.stream()
				.filter(part -> part.getId() == partId)
				.findFirst()
				.orElse(null);
	}
	
	public Products searchProductByID(int productId){
		return allProducts.stream()
				.filter(prod -> prod.getId() == productId)
				.findFirst()
				.orElse(null);
	}
	
	
	public ObservableList<Part> searchPartByName(String name){
		ObservableList<Part> matchingParts = FXCollections.observableArrayList();

	    matchingParts.addAll(
	        allParts.stream()
	                 .filter(part -> part.getName().toLowerCase().contains(name.toLowerCase()))
	                 .toList()
	    );

	    return matchingParts;
	}
	
	public ObservableList<Products> searchProductByName(String name){
		ObservableList<Products> matchingproducts = FXCollections.observableArrayList();
		
		matchingproducts.addAll(
				allProducts.stream()
				.filter(prod -> prod.getName().toLowerCase().contains(name.toLowerCase()))
				.toList()
				);
		
		return matchingproducts;
	}
	
	public void updatePart(int index, Part selectedPart) {
		allParts.set(index, selectedPart);
	}
	
	public void updateProduct(int index, Products selectedProduct) {
		allProducts.set(index, selectedProduct);
	}
	
	public boolean deletePart(Part selectedPart) {
		return allParts.remove(selectedPart);
	}
	
	public boolean deleteProducts(Products selectedProduct) {
		return allProducts.remove(selectedProduct);
	}
	
	public ObservableList<Part> getAllParts(){
		return this.allParts;
	}
	
	public ObservableList<Products> getAllProducts(){
		return this.allProducts;
	}
}
