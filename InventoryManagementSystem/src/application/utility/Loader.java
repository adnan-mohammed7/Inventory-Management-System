package application.utility;

import application.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Loader {
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
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/Main.fxml"));
    		BorderPane root = (BorderPane) loader.load();
        	MainController controller = loader.getController();
        	controller.setStage(stage);
        	Loader.loadFXML(stage, root);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
	}
}
