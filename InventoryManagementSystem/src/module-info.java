module InventoryManagementSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	exports application.controllers to javafx.fxml;
	opens application.controllers to javafx.fxml;
}