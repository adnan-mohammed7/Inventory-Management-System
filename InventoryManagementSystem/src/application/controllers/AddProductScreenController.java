package application.controllers;

import application.abstractClasses.Part;
import application.interfaces.StageImp;
import application.utility.Loader;
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

public class AddProductScreenController implements StageImp {
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
    private TextField inventoryFIeld;

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
    }

    @FXML
    void addPart(ActionEvent event) {
    	if(allPartsTable.getSelectionModel().getSelectedItem() != null) {
    		selectedParts.add(allPartsTable.getSelectionModel().getSelectedItem());
    	}
    }

    @FXML
    void handleCancel(ActionEvent event) {
    	Loader.openMain(stage);
    }

    @FXML
    void handleSave(ActionEvent event) {
    	Loader.openMain(stage);
    }

    @FXML
    void removePart(ActionEvent event) {
    	if(selectedPartsTable.getSelectionModel().getSelectedItem() != null) {
    		selectedParts.remove(selectedPartsTable.getSelectionModel().getSelectedItem());
    	}
    }

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
