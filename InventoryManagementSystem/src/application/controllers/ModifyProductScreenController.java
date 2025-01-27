package application.controllers;

import application.abstractClasses.Part;
import application.interfaces.StageImp;
import application.models.Products;
import application.utility.Loader;
import application.utility.Validate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModifyProductScreenController implements StageImp {
	private Stage stage;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<Part, Integer> allInvLvlCol;

    @FXML
    private TableColumn<Part, Integer> allPartIDCol;

    @FXML
    private TableColumn<Part, String> allPartNameCol;

    @FXML
    private TableView<Part> allPartsTable;

    @FXML
    private TableColumn<Part, Double> allPriceCol;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField idField;

    @FXML
    private TextField inventoryField;

    @FXML
    private TextField machineCompanyField;

    @FXML
    private Label machineCompanyLabel;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Button removeBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Part, Integer> selInvLvlCol;

    @FXML
    private TableColumn<Part, Integer> selPartIDCol;

    @FXML
    private TableColumn<Part, String> selPartNameCol;

    @FXML
    private TableColumn<Part, Double> selPriceCol;

    @FXML
    private TableView<Part> selectedPartsTable;
    
    ObservableList<Part> selectedParts;
    int index;
    int oldId;
    
    @FXML
    void initialize() {
    	allPartIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
    	allInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
    	allPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        allPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));;
        
        selPartIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
    	selInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
    	selPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        selPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));;
        
        allPartsTable.setItems(MainController.getInventory().getAllParts());
        
        searchField.textProperty().addListener((obj, ov, nv) ->{
        	allPartsTable.setItems(MainController.getInventory().searchPartByName(nv));
        });
        
        selectedParts = FXCollections.observableArrayList();
        selectedPartsTable.setItems(selectedParts);
        inventoryField.setText("0");
        
        idField.setText(String.valueOf(Products.counter.get()));
    }

    @FXML
    void addPart(ActionEvent event) {
    	if(allPartsTable.getSelectionModel().getSelectedItem() != null) {
    		selectedParts.add(allPartsTable.getSelectionModel().getSelectedItem());
    	}else {
    		Validate.showAlert("Please select a part to add!");
    	}
    }

    @FXML
    void handleCancel(ActionEvent event) {
    	if(Validate.showConfirmationAlert("Do you want to cancel editing the part?")) {
        	Loader.openMain(stage);	
    	}
    }

    @FXML
    void handleSave(ActionEvent event) {
    	if(Validate.validateProductFields(nameField, inventoryField, priceField, maxField, minField)) {
    		if(Validate.checkPrice(Double.parseDouble(priceField.getText()), selectedParts)) {
				Products newProduct;
        		
        		newProduct = new Products(oldId, nameField.getText(), Double.parseDouble(priceField.getText()),
    					Integer.parseInt(inventoryField.getText().isBlank() ? "0" : inventoryField.getText()),
    					Integer.parseInt(minField.getText()),
    					Integer.parseInt(maxField.getText()));
        		
        		if(selectedParts.size() > 0) {
        			for (Part e : selectedParts)
            			newProduct.addAssociatedPart(e);
        		}
        			
        		MainController.getInventory().updateProduct(index, newProduct);
        		Loader.openMain(stage);	
			}else {
				Validate.showAlert("Product cost cannot be less than parts cost!");
				priceField.requestFocus();
			}
    	}
    }

    @FXML
    void removePart(ActionEvent event) {
    	if(selectedPartsTable.getSelectionModel().getSelectedItem() != null) {
    		selectedParts.remove(selectedPartsTable.getSelectionModel().getSelectedItem());
    	}else {
    		Validate.showAlert("Please select a part to delete!");
    	}
    }

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setProduct(Products prod) {
		oldId = prod.getId();
    	idField.setText(String.valueOf(prod.getId()));
    	nameField.setText(prod.getName());
    	inventoryField.setText(String.valueOf(prod.getStock()));
    	priceField.setText(String.valueOf(prod.getPrice()));
    	maxField.setText(String.valueOf(prod.getMax()));
    	minField.setText(String.valueOf(prod.getMin()));
    	nameField.requestFocus();
    	selectedParts.clear();
    	selectedParts.addAll(prod.getAllAssociatedParts());
	}
	
	public void setSelectedIndex(int index) {
		this.index = index;
	}
}