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
