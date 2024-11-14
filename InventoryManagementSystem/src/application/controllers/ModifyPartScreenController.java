package application.controllers;

import application.abstractClasses.Part;
import application.interfaces.StageImp;
import application.utility.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyPartScreenController implements StageImp {
	
	private Stage stage;
	
	private Part part;

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

    @FXML
    void handleCancel(ActionEvent event) {
    	Loader.openMain(stage);
    }

    @FXML
    void handleSave(ActionEvent event) {
    	Loader.openMain(stage);
    }

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setPart(Part part) {
		this.part = part;
	}

}
