package mines;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MinesFX extends Application{

	
	@Override
	public void start(Stage primaryStage) {
		try {

			primaryStage.setTitle("The Amazing Mines Sweeper");
	
			HBox window;
			MyController ctrl;
			FXMLLoader ldr = new FXMLLoader();
			ldr.setLocation(getClass().getResource("MinesFX.fxml"));
			window = ldr.load();
			ctrl = ldr.getController();
			ctrl.setMy_box(window);
			Image image = new Image(getClass().getResourceAsStream("reset.png"));
			ImageView img = new ImageView(image);
			img.setFitHeight(20);
			img.setFitWidth(20);
			ctrl.getReset().setGraphic(img);
			
			ctrl.setPrimaryStage(primaryStage);
			Scene scene = new Scene(window);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.minWidthProperty();
			window.autosize();
			primaryStage.sizeToScene();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
	
}
	
	