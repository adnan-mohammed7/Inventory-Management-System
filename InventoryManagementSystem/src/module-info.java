module InventoryManagementSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	exports application.controllers to javafx.fxml;
	opens application.controllers to javafx.fxml;
}