/**********************************************
Workshop #4&5
Course:<APD 545> - Fall 2024
Last Name: Mohammed
First Name: Adnan
ID: 174731216
Section: ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date: 15th November 2024
**********************************************/

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
