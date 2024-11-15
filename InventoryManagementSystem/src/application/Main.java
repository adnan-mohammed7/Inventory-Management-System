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

package application;
	
import application.controllers.MainController;
import application.utility.Loader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader((getClass().getResource("views/Main.fxml")));
			BorderPane root = (BorderPane)loader.load();
			MainController controller = loader.getController();
			controller.setStage(primaryStage);
			Scene scene = new Scene(root,400,400);
			Loader.setController(controller);
			Loader.setBorderPane(scene);
			scene.getStylesheets().add(getClass().getResource("styles/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
