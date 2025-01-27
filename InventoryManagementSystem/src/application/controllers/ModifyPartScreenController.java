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

public class ModifyPartScreenController implements StageImp {
	
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
    private int oldId;
    private int selectedIndex;
    
    @FXML void initialize() {
    	partType = new ToggleGroup();
    	inHouseRadioBtn.setToggleGroup(partType);
    	outsourcedRadioBtn.setToggleGroup(partType);
    	
    	partType.selectedToggleProperty().addListener((obj, ov, nv) -> {
    		if(nv == inHouseRadioBtn) {
    			machineCompanyLabel.setText("Machine ID");
    		} else if(nv == outsourcedRadioBtn) {
    			machineCompanyLabel.setText("Company Name");
    		}
    	});
    }

    @FXML
    void handleCancel(ActionEvent event) {
    	if(Validate.showConfirmationAlert("Do you want to cancel editing the part?")) {
        	Loader.openMain(stage);	
    	}
    }

    @FXML
    void handleSave(ActionEvent event) {
    	if(Validate.validatePartFields(nameField, inventoryField, priceField, maxField, minField, machineCompanyField, inHouseRadioBtn.isSelected())) {
    		Part updatedPart;
    		if(inHouseRadioBtn.isSelected()) {
    			updatedPart = new InHouse(oldId, nameField.getText(), Double.parseDouble(priceField.getText()),
    					Integer.parseInt(inventoryField.getText()),
    					Integer.parseInt(minField.getText()),
    					Integer.parseInt(maxField.getText()),
    					Integer.parseInt(machineCompanyField.getText()));
    		}else {
    			updatedPart = new Outsourced(oldId, nameField.getText(), Double.parseDouble(priceField.getText()),
    					Integer.parseInt(inventoryField.getText()),
    					Integer.parseInt(minField.getText()),
    					Integer.parseInt(maxField.getText()),
    					machineCompanyField.getText());
    		}
    		MainController.getInventory().updatePart(selectedIndex, updatedPart);
    		Loader.openMain(stage);	
    	}
    }

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setPart(Part part) {
		if(part.getClass() == InHouse.class) {
    		inHouseRadioBtn.setSelected(true);
    		machineCompanyField.setText(String.valueOf(part.getMachineID()));
    	}else {
    		outsourcedRadioBtn.setSelected(true);
    		machineCompanyField.setText(part.getCompanyName());
    	}
    	
    	oldId = part.getId();
    	idField.setText(String.valueOf(part.getId()));
    	nameField.setText(part.getName());
    	inventoryField.setText(String.valueOf(part.getStock()));
    	priceField.setText(String.valueOf(part.getPrice()));
    	maxField.setText(String.valueOf(part.getMax()));
    	minField.setText(String.valueOf(part.getMin()));
    	nameField.requestFocus();
	}
	
	public void setSelectedIndex(int index) {
		this.selectedIndex = index;
	}
}
