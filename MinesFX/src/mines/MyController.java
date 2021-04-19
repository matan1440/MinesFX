package mines;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyController {
	
	protected Mines My_game;
	private int height;
	private int width;
	private int mines=0;
	private boolean flag_win_open;
	private boolean click;
	private Stage primaryStage;
	private Button[][] Mat_b;

	
    @FXML
    private HBox my_box;
    
    public void setMy_box(HBox my_box) {					//to allow access to change size of the window
		this.my_box = my_box;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	@FXML
    private GridPane my_grid1;

    @FXML
    private Button Reset;


    public Button getB() {
		return b;
	}

	@FXML
    private TextField TF_width;

    public Button getReset() {
		return Reset;
	}


	@FXML
    private TextField TF_height;

    @FXML
    private TextField TF_mines;

    @FXML
    private Text B_width;

    @FXML
    private Text B_height;

    @FXML
    private Text B_mines;

    @FXML
    void set_grid(ActionEvent event) {	//func of Button Rest
    		
    		Alert alert1 = new Alert(AlertType.ERROR);
			GridPane my_grid = new GridPane();
			click = true;
			
			
    		if(flag_win_open) 
    			my_box.getChildren().remove(1);				//remove window of game when we open new game
    		
    		try {
    		height = Integer.parseInt(TF_height.getCharacters().toString());
    		width = Integer.parseInt(TF_width.getCharacters().toString());
    		mines = Integer.parseInt(TF_mines.getCharacters().toString());
    		
    		if(height <= 0 || width <= 0 || mines < 0 || mines > height * width) {			//if the user try to enter Wrong number
				alert1.setContentText("On the TextFields you have entered a wrong number.");
				alert1.show();
				return;
				}}catch (Exception e) {};
				

    		My_game = new Mines(height, width, mines);
    		
    		my_box.getChildren().add(1, my_grid);
    		flag_win_open = true;
 
    		
    		Mat_b = new Button[height][width];
    			for(int i=0; i<height; i++)
    				for(int j=0; j<width; j++) {
    					int index_i=i;
    					int index_j=j;
    					Button b = new Button(My_game.get(i,j));
    					Mat_b[i][j]= b;
    					Mat_b[i][j].setMinWidth(50);
    					Mat_b[i][j].setMinHeight(50);
    					Mat_b[i][j].setFont(new Font("Ariel", 10));
    					
    					
    					b.setOnMouseClicked(new EventHandler<MouseEvent>(){
    						@Override
    						public void handle(MouseEvent event) {
    							
    							
    							
    							
    							if(click) {
    							if(event.getButton() == MouseButton.PRIMARY) {				//if left click
    								
    								if(My_game.open(index_i, index_j)==false) {
    									if(My_game.get(index_i, index_j).equals("F")) {} else {		//if we try open button with "F" - failed									
    																				
    									My_game.setShowAll(true);		//msg - failed
    									
        								for(int i=0; i<height; i++)						//updating screen
        									for(int j=0; j<width; j++) {
        										if(My_game.get(i, j).equals("X")) {
        											Image image = new Image(getClass().getResourceAsStream("bombimage.jpg"));
        											ImageView img = new ImageView(image);
        											img.setFitHeight(20);
        											img.setFitWidth(20);
        											Mat_b[i][j].setGraphic(img);
        											My_game.Mat_Open[i][j]="";
        										}else
        											Mat_b[i][j].setGraphic(null);
        									}
    									
        								
    									Label msg_lose = new Label("Sorry, you lose!!!\nTry again");
    									Stage newStage = new Stage();
    									Scene newScene = new Scene(msg_lose);
    									newStage.setScene(newScene);
    									newStage.show();
    									click=false;
    									
    									}
    									
    								}else if(My_game.isDone()) {					//msg - win
    									
        								for(int i=0; i<height; i++)						//updating screen
        									for(int j=0; j<width; j++) {
        										Mat_b[i][j].setGraphic(null);
        										My_game.setShowAll(true);
       											Mat_b[i][j].setText(My_game.get(i,j));
        									}
    									
    									My_game.setShowAll(My_game.showAll);
    									Label msg_win = new Label("YOU WIN!!!");
    									Stage newStage = new Stage();
    									Scene newScene = new Scene(msg_win);
    									newStage.setScene(newScene);
    									newStage.show();
    									click=false;
    								}
    										
    							
    								for(int i=0; i<height; i++)						//updating screen
    									for(int j=0; j<width; j++) 
    										if(!My_game.get(i,j).equals("F"))			//that the image of flag not show
    											Mat_b[i][j].setText(My_game.get(i,j));
    											
    									}
    						
    					
    							
       							if(event.getButton() == MouseButton.SECONDARY) {				//put Right click on button - "F" 
    								if(!My_game.Mat_Close[index_i][index_j].equals("F")) {			//if is first of press on "F" 
        								Image image = new Image(getClass().getResourceAsStream("flag.jpg"));
        								ImageView img = new ImageView(image);
        								img.setFitHeight(20);
        								img.setFitWidth(20);
        								b.setGraphic(img);
        								b.setText("");
    								}else {							//if is press on "F" one again
    									b.setGraphic(null);
    									b.setText(".");
    								}
       								My_game.toggleFlag(index_i, index_j);

       							}
    							
    							}
    						}
    						
    						
    					});
    					my_grid.add(Mat_b[i][j],j,i);			//insert of game board
    				}

    	
    			my_grid.setAlignment(Pos.CENTER);
    			my_grid.setPadding(new Insets(10));
    			
    			my_box.autosize();				//create auto change in main stage when create a new game
    			primaryStage.sizeToScene();
    		
	
    		
    }
    
    public int getMines() {
		return mines;
	}

	public Button[][] getMat_b() {
		return Mat_b;
	}

	@FXML
    private Button b;
    
  

}