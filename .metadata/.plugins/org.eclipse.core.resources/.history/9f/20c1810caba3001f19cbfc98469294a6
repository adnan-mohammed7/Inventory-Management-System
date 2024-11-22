package application.utility;

import application.controllers.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Loader {
	public static Scene scene;
	public static MainController controller;
	public static void loadFXML(Stage ps, BorderPane root) {
		try {			
			Scene scene = new Scene(root);
			ps.close();
			ps.setScene(scene);
			ps.setMaximized(true);
			ps.setResizable(false);
			ps.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void openMain(Stage stage) {
		try {
        	controller.setStage(stage);
			stage.close();
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.setResizable(false);
			stage.show();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public static void setBorderPane(Scene sc) {
		scene = sc;
	}
	
	public static void setController(MainController control) {
		controller = control;
	}
}
