package application.controllers;

import application.abstractClasses.Part;
import application.interfaces.StageImp;
import application.models.InHouse;
import application.models.Outsourced;
import application.utility.Loader;
import application.utility.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    	if(Validate.showConfirmationAlert("Do you want to cancel adding the part?")) {
        	Loader.openMain(stage);	
    	}
    }

    @FXML
    void handleSave(ActionEvent event) {
    	if(Validate.validatePartFields(nameField, inventoryField, priceField, maxField, minField, machineCompanyField, inHouseRadioBtn.isSelected())) {
    		Part newPart;
    		if(inHouseRadioBtn.isSelected()) {
    			newPart = new InHouse(nameField.getText(), Double.parseDouble(priceField.getText()),
    					Integer.parseInt(inventoryField.getText()),
    					Integer.parseInt(minField.getText()),
    					Integer.parseInt(maxField.getText()),
    					Integer.parseInt(machineCompanyField.getText()));
    		}else {
    			newPart = new Outsourced(nameField.getText(), Double.parseDouble(priceField.getText()),
    					Integer.parseInt(inventoryField.getText()),
    					Integer.parseInt(minField.getText()),
    					Integer.parseInt(maxField.getText()),
    					machineCompanyField.getText());
    		}
    		MainController.getInventory().addPart(newPart);
    		Loader.openMain(stage);	
    	}
    }

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
