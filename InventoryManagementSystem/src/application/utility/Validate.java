package application.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

public class Validate {
	public static int validateInteger(String str){
		try {
			return Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return -1;
		}
	}
	
	public static double validateDouble(String str){
		try {
			return Double.parseDouble(str);
		}catch(NumberFormatException e) {
			return -1;
		}
	}
	
	public static boolean validateValues(int inv, int min, int max) {
		if(inv < 0 || min < 0 || max < 0 || inv < min || inv > max || min > max) {
			return false;
		}
		return true;
	}
	
	public static boolean emptyFields(TextField name, TextField inv, TextField price, TextField max, TextField min, TextField machineCompany) {
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
	
	public static boolean validateFields(TextField name, TextField inv, TextField price, TextField max, TextField min, TextField machineCompany, boolean check) {
		if(!emptyFields(name, inv, price, max, min, machineCompany)) {
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
}
