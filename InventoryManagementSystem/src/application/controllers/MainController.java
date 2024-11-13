package application.controllers;

import application.interfaces.StageImp;
import application.utility.Loader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController implements StageImp {
	
	Stage stage;

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
    private TableColumn<?, ?> partIDCol;

    @FXML
    private TableColumn<?, ?> partInvLvlCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> partPriceCol;

    @FXML
    private TextField partSearchField;

    @FXML
    private TableView<?> partsTable;

    @FXML
    private TableColumn<?, ?> prodIDCol;

    @FXML
    private TableColumn<?, ?> prodInvLvlCol;

    @FXML
    private TableColumn<?, ?> prodNameCol;

    @FXML
    private TableColumn<?, ?> prodPriceCol;

    @FXML
    private TextField productSearchField;

    @FXML
    private TableView<?> productsTable;

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

    }

    @FXML
    void deleteProduct(ActionEvent event) {

    }

    @FXML
    void exitApplication(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void handlePartSearch(ActionEvent event) {

    }

    @FXML
    void handleProductSearch(ActionEvent event) {

    }

    @FXML
    void modifyPart(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/ModifyPartScreen.fxml"));
    		BorderPane root = (BorderPane) loader.load();
        	ModifyPartScreenController controller = loader.getController();
        	controller.setStage(stage);
        	Loader.loadFXML(stage, root);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void modifyProduct(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/ModifyProductScreen.fxml"));
    		BorderPane root = (BorderPane) loader.load();
        	ModifyProductScreenController controller = loader.getController();
        	controller.setStage(stage);
        	Loader.loadFXML(stage, root);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Override
    public void setStage(Stage stage) {
    	this.stage = stage;
    }
}
