package simpleFX;
	
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane; 
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class Main extends Application {

	 int counter=0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(createRoot());
		stage.setScene(scene);
		stage.setTitle("Voting Machine");
		stage.setMinWidth(210);				//min of size window 
		stage.setMinHeight(150);
		stage.show();
	}
	
	private GridPane createRoot() {
		GridPane root = new GridPane();
		Button a = new Button("Ofra Haza");
		a.setFont(new Font("Arial",12));
		root.add(a, 0, 0);
		Button b = new Button("Yardena Arazi");
		b.setFont(new Font("Arial",12));
		root.add(b, 1, 0);
		
		a.setMinHeight(30);
		a.setMaxHeight(30);
		
		b.setMinHeight(30);
		b.setMaxHeight(30);


		root.setPadding(new Insets(10));	//Set space between elements to window
		root.setHgap(10);					//Set space between elements in rows
		root.setVgap(10);					//Set space between elements in cols
		
		Label label = new Label((counter + ""));
	
		BackgroundFill p = new BackgroundFill(Color.RED, null, null);
		label.setBackground(new Background(p));
		label.setAlignment(Pos.CENTER);
		label.setPadding(new Insets(10));
		
		label.setWrapText(true);								//allowing label to expand 
		label.prefWidthProperty().bind(root.widthProperty());	//
		
		ColumnConstraints c = new ColumnConstraints();
		c.setHalignment(HPos.CENTER);
		root.getColumnConstraints().add(c);
		
		label.setMaxWidth(Double.MAX_VALUE);
		root.add(label,0,2,2,1);
		
	

		a.setOnAction(new EventHandler<ActionEvent>() {			// Click on "Ofra Haza"
			@Override
			public void handle(ActionEvent event) {
				counter++;
				label.setText(counter+"");
			}
		});
		
		b.setOnAction(new EventHandler<ActionEvent>() {			// Click on "Yardena Arazi"
			@Override
			public void handle(ActionEvent event) {
				counter--;
				label.setText(counter+"");	
			}
		});
		
		
		return root;
	}


	

}
