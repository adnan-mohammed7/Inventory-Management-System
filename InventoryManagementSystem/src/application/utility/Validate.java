package application.utility;

import java.util.Optional;

import application.abstractClasses.Part;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

public class Validate {
	static int validateInteger(String str){
		try {
			return Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return -1;
		}
	}
	
	static double validateDouble(String str){
		try {
			return Double.parseDouble(str);
		}catch(NumberFormatException e) {
			return -1;
		}
	}
	
	static boolean validateValues(int inv, int min, int max) {
		if(inv < 0 || min < 0 || max < 0 || inv < min || inv > max || min > max) {
			return false;
		}
		return true;
	}
	
	static boolean emptyPartFields(TextField name, TextField inv, TextField price, TextField max, TextField min, TextField machineCompany) {
		if (name.getText().isBlank() ||
				inv.getText().isBlank() ||
				price.getText().isBlank() ||
				max.getText().isBlank() ||
				min.getText().isBlank() ||
				machineCompany.getText().isBlank()) {
			return false;
		}
		return true;
	}
	
	public static boolean validatePartFields(TextField name, TextField inv, TextField price, TextField max, TextField min, TextField machineCompany, boolean check) {
		if(!emptyPartFields(name, inv, price, max, min, machineCompany)) {
			showAlert("Fields cannot be empty.");
			return false;
		}
		
		if(validateDouble(price.getText()) == -1) {
			showAlert("Please enter a valid price/cost.");
			price.requestFocus();
			return false;
		}
		
		if(!validateValues(validateInteger(inv.getText()), validateInteger(min.getText()), 
				validateInteger(max.getText()))) {
			showAlert("Please enter valid inventory level, min, max. "
					+ "Inventory cannot be less than min or greater than max. "
					+ "Min cannot be greater than max. "
					+ "Max cannot be less than min");
			inv.requestFocus();
			return false;
		}
		
		if (check && (validateInteger(machineCompany.getText())) == -1) {
			showAlert("Please enter a valid machine ID");
			machineCompany.requestFocus();
			return false;
		}
		
		return true;
	}
	
	static int validateProdInventory(String str) {
		if(str.isBlank()) {
			return 0;
		}else {
			try {
				return Integer.parseInt(str);
			}catch(NumberFormatException e) {
				return -1;
			}
		}
	}
	
	public static boolean emptyProductFields(TextField name, TextField price, TextField max, TextField min) {
		if (name.getText().isBlank() ||
				price.getText().isBlank() ||
				max.getText().isBlank() ||
				min.getText().isBlank()) {
			return false;
		}
		return true;
	}
	
	public static boolean validateProductFields(TextField name, TextField inv, TextField price, TextField max, TextField min) {
		if(!emptyProductFields(name, price, max, min)){
			showAlert("Fields cannot be empty.");
			return false;
		}
		
		if(validateDouble(price.getText()) == -1) {
			showAlert("Please enter a valid price/cost.");
			price.requestFocus();
			return false;
		}
		
		if(!validateValues(validateProdInventory(inv.getText()), validateInteger(min.getText()), 
				validateInteger(max.getText()))) {
			showAlert("Please enter valid inventory level, min, max. "
					+ "Inventory cannot be less than min or greater than max. "
					+ "Min cannot be greater than max. "
					+ "Max cannot be less than min");
			inv.requestFocus();
			return false;
		}
		return true;
	}
	
	public static void showAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		DialogPane dialog = alert.getDialogPane();
		dialog.setPrefSize(500, 240);
		dialog.setStyle("-fx-font-size: 20px");
		dialog.lookupButton(ButtonType.OK).setStyle("-fx-font-size: 20px");
		
		alert.show();
	}
	
	public static boolean checkPrice(double cost, ObservableList<Part> selectedParts) {
		double partsCost = 0.0;
		
		for(Part e : selectedParts)
			partsCost += e.getPrice();
		
		return cost >= partsCost;
	}
	
	public static Boolean showConfirmationAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		DialogPane dialog = alert.getDialogPane();
		dialog.setPrefSize(500, 240);
		dialog.setStyle("-fx-font-size: 20px");
		
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		
		alert.getButtonTypes().setAll(yes, no);
		
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent()) {
			if(result.get() == yes) {
				return true;
			}
		}
		return false;
	}
}
