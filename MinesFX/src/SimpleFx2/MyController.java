package SimpleFx2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyController {
	private int counter=0;
	
    @FXML
    private Button a;

    @FXML
    private Button b;

    @FXML
    private Label label;

    @FXML
    void DownCounter(ActionEvent event) {
    	counter--;
    	label.setText(counter + "");
    }

    @FXML
    void upCounter(ActionEvent event) {
    	counter++;
    	label.setText(counter +"");

    }

}