module InventoryManagementSystem {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	exports application.controllers to javafx.fxml, javafx.graphics;
	opens application.controllers to javafx.fxml;
	opens application.abstractClasses to javafx.base;
	exports application.utility to javafx.fxml, javafx.graphics;
	exports application.abstractClasses to javafx.fxml, javafx.graphics;
	exports application.models to javafx.fxml, javafx.graphics;
	opens application.models to javafx.base;
}