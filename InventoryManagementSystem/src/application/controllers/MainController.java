package application.controllers;

import application.abstractClasses.Part;
import application.interfaces.StageImp;
import application.models.InHouse;
import application.models.Inventory;
import application.models.Outsourced;
import application.models.Products;
import application.utility.Loader;
import application.utility.Validate;
import javafx.application.Platform;
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
    	
    	inventory.addPart(new Outsourced("Cement", 75.0, 50, 10, 100, "Ambuja"));
    	inventory.addPart(new InHouse("Brick", 75.0, 50, 10, 100, 785));
    	inventory.addPart(new InHouse("Plaster", 75.0, 50, 10, 100, 850));
    	inventory.addPart(new InHouse("SenecaBook", 75.0, 50, 10, 100, 900));
    	inventory.addPart(new Outsourced("Seneca Library", 75.0, 50, 10, 100, "Seneca"));
    	
    	inventory.addProduct(new Products("Cement", 75.0, 50, 10, 75));
    	inventory.addProduct(new Products("Brick", 75.0, 50, 10, 100));
    	inventory.addProduct(new Products("Plaster", 75.0, 50, 10, 100));
    	inventory.addProduct(new Products("SenecaBook", 75.0, 50, 10, 100));
    	inventory.addProduct(new Products("Seneca Library", 75.0, 50, 10, 100));
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
			if(Validate.showConfirmationAlert("Do you want to delete the selected part?")) {
				inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());    		
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
    }
    
    public static Inventory getInventory() {
    	return inventory;
    }
}
