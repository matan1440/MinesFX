package SimpleFx2;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Voting Machine");
			primaryStage.setMinHeight(150);
			primaryStage.setMinWidth(270);
			GridPane root;
			@SuppressWarnings("unused")
			MyController ctrl;
			FXMLLoader ldr = new FXMLLoader();
			ldr.setLocation(getClass().getResource("SimpleFx2.fxml"));
			root = ldr.load();
			ctrl = ldr.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
