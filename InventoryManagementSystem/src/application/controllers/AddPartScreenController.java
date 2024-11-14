package application.controllers;

import application.abstractClasses.Part;
import application.interfaces.StageImp;
import application.models.InHouse;
import application.utility.Loader;
import application.utility.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AddPartScreenController implements StageImp {
	private Stage stage;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField idField;

    @FXML
    private RadioButton inHouseRadioBtn;

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
    private RadioButton outsourcedRadioBtn;

    @FXML
    private TextField priceField;

    @FXML
    private Button saveBtn;
    
    private ToggleGroup partType;

    @FXML void initialize() {
    	partType = new ToggleGroup();
    	inHouseRadioBtn.setToggleGroup(partType);
    	outsourcedRadioBtn.setToggleGroup(partType);
    	
    	idField.setText("" + Part.counter.get());
    	
    	partType.selectedToggleProperty().addListener((obj, ov, nv) -> {
    		if(nv == inHouseRadioBtn) {
    			machineCompanyLabel.setText("Machine ID");
    		} else if(nv == outsourcedRadioBtn) {
    			machineCompanyLabel.setText("Company Name");
    		}
    	});
    	
    	inHouseRadioBtn.setSelected(true);
    	nameField.requestFocus();
    }
    
    @FXML
    void handleCancel(ActionEvent event) {
    	Loader.openMain(stage);
    }

    @FXML
    void handleSave(ActionEvent event) {
    	if(Validate.validateFields(nameField, inventoryField, priceField, maxField, minField, machineCompanyField, inHouseRadioBtn.isSelected())) {
    		MainController.getInventory().addPart(Validate.getPart(nameField, inventoryField, priceField, maxField, minField, machineCompanyField, inHouseRadioBtn.isSelected()));
    		Loader.openMain(stage);	
    	}
    }

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
