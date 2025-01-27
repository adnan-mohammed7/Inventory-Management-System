package application.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.abstractClasses.Part;
import application.database.Database;
import application.interfaces.StageImp;
import application.models.Inventory;
import application.models.Products;
import application.utility.Loader;
import application.utility.Validate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController implements StageImp {
	
	private Stage stage;
	private static Inventory inventory = new Inventory();

    @FXML
    private Button addProdBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private Button deleteProdBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button modifyPartBtn;

    @FXML
    private Button modifyProdBtn;

    @FXML
    private Button partAddBtn;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, Integer> partInvLvlCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField partSearchField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Products, Integer> prodIDCol;

    @FXML
    private TableColumn<Products, Integer> prodInvLvlCol;

    @FXML
    private TableColumn<Products, String> prodNameCol;

    @FXML
    private TableColumn<Products, Double> prodPriceCol;

    @FXML
    private TextField productSearchField;

    @FXML
    private TableView<Products> productsTable;
    
    @FXML
    private Button loadDB;

    @FXML
    private Button loadFile;
    
    @FXML
    private Button saveDB;

    @FXML
    private Button saveFile;
    
    @FXML
    void initialize() {
    	partIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
    	partInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
    	partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));;
        
        partsTable.setItems(inventory.getAllParts());
        
        prodIDCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
    	prodInvLvlCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("stock"));
    	prodNameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<Products, Double>("price"));;
        
        productsTable.setItems(inventory.getAllProducts());
    	
    	partSearchField.textProperty().addListener((obj,ov,nv)->{
    		partsTable.setItems(inventory.searchPartByName(nv));
    	});
    	
    	productSearchField.textProperty().addListener((obj,ov,nv)->{
    		productsTable.setItems(inventory.searchProductByName(nv));
    	});
    }

    @FXML
    void addPart(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/AddPartScreen.fxml"));
    		BorderPane root = (BorderPane) loader.load();
        	AddPartScreenController controller = loader.getController();
        	controller.setStage(stage);
        	Loader.loadFXML(stage, root);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void addProduct(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/AddProductScreen.fxml"));
    		BorderPane root = (BorderPane) loader.load();
        	AddProductScreenController controller = loader.getController();
        	controller.setStage(stage);
        	Loader.loadFXML(stage, root);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void deletePart(ActionEvent event) {
    	if(partsTable.getSelectionModel().getSelectedItem() != null) {
    		Part selected = partsTable.getSelectionModel().getSelectedItem();
			if(Validate.showConfirmationAlert("Do you want to delete the selected part?")) {
				deleteNoPartProduct(selected);
				inventory.deletePart(selected);    		
			}
    	}else {
			Validate.showAlert("Please select the product to delete!");
		}
    }

    @FXML
    void deleteProduct(ActionEvent event) {
    	if(productsTable.getSelectionModel().getSelectedItem() != null) {
    		Products selected =  productsTable.getSelectionModel().getSelectedItem();
    		if(selected.getAllAssociatedParts().size() == 0) { 		
        		if(Validate.showConfirmationAlert("Do you want to delete the selected product?")) {
        			inventory.deleteProducts(selected);
        		}
        	}else {
    			Validate.showAlert("Please remove all the associated parts of the selected product!");
    		}
    	}else {
    		Validate.showAlert("Please select the product to delete!");
    	}
    }

    @FXML
    void exitApplication(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void modifyPart(ActionEvent event) {
    	if(partsTable.getSelectionModel().getSelectedItem() != null) {
    		try {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/ModifyPartScreen.fxml"));
        		BorderPane root = (BorderPane) loader.load();
            	ModifyPartScreenController controller = loader.getController();
            	controller.setStage(stage);
            	controller.setPart(partsTable.getSelectionModel().getSelectedItem());
            	controller.setSelectedIndex(partsTable.getSelectionModel().getSelectedIndex());
            	Loader.loadFXML(stage, root);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	}else {
    		Validate.showAlert("Please Select a Part to Modify!");
    	}
    }

    @FXML
    void modifyProduct(ActionEvent event) {
    	if(productsTable.getSelectionModel().getSelectedItem() != null) {
    		try {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/ModifyProductScreen.fxml"));
        		BorderPane root = (BorderPane) loader.load();
            	ModifyProductScreenController controller = loader.getController();
            	controller.setStage(stage);
            	controller.setProduct(productsTable.getSelectionModel().getSelectedItem());
            	controller.setSelectedIndex(productsTable.getSelectionModel().getSelectedIndex());
            	Loader.loadFXML(stage, root);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	}else {
    		Validate.showAlert("Please Select a Product to Modify!");
    	}
    }
    
    @Override
    public void setStage(Stage stage) {
    	this.stage = stage;
    	partsTable.setItems(inventory.getAllParts());
    	productsTable.setItems(inventory.getAllProducts());
    }
    
    public static Inventory getInventory() {
    	return inventory;
    }
    
    void deleteNoPartProduct(Part part) {
    	ObservableList<Products> productsToRemove = FXCollections.observableArrayList();
    	for (Products e : inventory.getAllProducts()) {
    		for(Part p : e.getAllAssociatedParts()) {
    			if(p.equals(part)) {
    				productsToRemove.add(e);
    			}
    		}
    	}
    	for (Products e : productsToRemove) {
    		inventory.deleteProducts(e);
    	}
    }
    
    @FXML
    void loadDataFromDB(ActionEvent event) {
    	Database db = new Database();
    	inventory.getAllParts().setAll(db.loadParts());
    	inventory.getAllProducts().setAll(db.loadProducts());
    }

    @FXML
    void loadDataFromFile(ActionEvent event) {
    	try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("inventory.ser"))){
    		@SuppressWarnings("unchecked")
			List<Part> parts = (List<Part>) in.readObject();
    		int count = 0;
    		for(Part e : parts) {
    			if (count < e.getId()) {
    				count = e.getId();
    			}
    		}
    		Part.counter.set(++count);
    		count = 0;
    		@SuppressWarnings("unchecked")
			List<Products> products = (List<Products>) in.readObject();
    		for(Products e : products) {
    			if (count < e.getId()) {
    				count = e.getId();
    			}
    		}
    		Products.counter.set(++count);
    		inventory.getAllParts().setAll(parts);
    		inventory.getAllProducts().setAll(products);
    		
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}catch(ClassNotFoundException ex) {
    		ex.printStackTrace();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    @FXML
    void saveDataToDB(ActionEvent event) {
    	Database db = new Database();
    	db.createPartsTable();
    	db.createProductTables();
    	db.insertParts(inventory.getAllParts());
    	db.insertProducts(inventory.getAllProducts());
    }

    @FXML
    void saveDataToFile(ActionEvent event) {
    	try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("inventory.ser"))){
    		List<Part> parts = new ArrayList<Part>(inventory.getAllParts());
    		List<Products> products = new ArrayList<Products>(inventory.getAllProducts());
    		out.writeObject(parts);
    		out.writeObject(products);
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}