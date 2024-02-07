package compilateurnewversion;

import javafx.application.Application;					
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class launcher extends Application {

	@Override
	  public void start(Stage stage) throws Exception {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("launcher.fxml"));
		    Parent root = loader.load();
		    ControlerFx controller = loader.getController();
		    controller.setStage(stage);
		    Scene scene = new Scene(root, 1207, 619);
	        stage.setScene(scene);
	        stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
