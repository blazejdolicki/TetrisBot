package Menu;

import Setup.Main;
import Setup.ScoreReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class creates scene that will visualise all top scores.
 * @author Barbara, Blazej, Jordan, Samuel, Stijn, Yvar
 * @version 1.2
 */
public class HighscoreView extends GridPane{
	
	private String[] names;
	private int[] scores;
	
         /**
         * Constructor uses class ScoreReader and gets all the scores from there and
         * then adds them to a GridPane. There is also a button menu.
         * <p>
         * If there are no high scores or file is missing, a message "no data available will be seen".
         * After the first game, a file will be created with data in it.
         */
	public HighscoreView() {
        ScoreReader sr = new ScoreReader();
		
        names = sr.getNames();
        scores = sr.getScores();
        
        if(names[0]==null||names[0].equals("null")){
            Label scoreLabel = new Label("No data available!");
            scoreLabel.setFont(new Font("Arial", 25));
            scoreLabel.setTextFill(Color.DARKRED);
            add(scoreLabel, 0, 0);
            setHalignment(scoreLabel, HPos.CENTER);
            setVgap(20);
        }
        else{
            for(int i = 0; i < names.length; i++){
                if(!names[i].equals("null")){
                    Label scoreLabel = new Label((i + 1) + ": " + names[i] + " has score: " + scores[i]);
                    scoreLabel.setFont(new Font("Arial", 16));
                    add(scoreLabel, 0, i);
                    setHalignment(scoreLabel, HPos.LEFT);
                    setVgap(10);
                }
            }   
        }
        
        Button back = new Button("Menu");
        back.setMinSize(150, 50);
        back.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        add(back, 0, 10);
        
        setAlignment(Pos.CENTER);
        setHalignment(back, HPos.CENTER);
        
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            /**
             * If button is clicked it will return to main menu.
             * @param e ActionEvent, if the button is clicked it will trigger the code
             */
            public void handle(ActionEvent e) {
        	 
                Stage primaryStage = Main.getStage();
                MenuView menu = new MenuView();
                Scene scene = new Scene(menu);
                
                primaryStage.setScene(scene);
                primaryStage.setWidth(400);
                primaryStage.setHeight(400);

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
                primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
         }});
        
        setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, Insets.EMPTY)));
	}
}
