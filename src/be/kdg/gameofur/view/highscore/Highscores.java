package be.kdg.gameofur.view.highscore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * This viewclass shows the top 5 highscores
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class Highscores extends BorderPane{
    //attributes
    private Label lblHighscore;
    //gridpane in center for al the highscores
    private GridPane gridPane;
    private Label lblName;
    private Label lblScore;
    private VBox vBoxNumbers;
    private Label[] numbers;
    private Label[] lblNames;
    private VBox vBoxesName;
    private Label[] lblScores;
    private VBox vBoxesScore;
    //back button
    private Button btnBack;
    //background
//    private Background background;


    //consturctor
    public Highscores(){
        initialiseNodes();
        layoutNodes();
    }


    //methods
    private void initialiseNodes() {
        lblHighscore = new Label("Highscores");
        //gridpane in center
        gridPane = new GridPane();
        lblName = new Label("Name:");
        lblScore = new Label("Score:");
        vBoxNumbers = new VBox();
        numbers = new Label[5];
        lblNames = new Label[5];
        lblScores = new Label[5];
        vBoxesName = new VBox();
        vBoxesScore = new VBox();
        for (int i = 0; i < lblNames.length; i++) {
            lblNames[i]= new Label();
            lblScores[i]= new Label();
        }

        //back button
        btnBack = new Button("Back");

        //background
//        Image backgroundImage = new Image("/background/.jpg");
//        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true);
//        background = new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,backgroundSize));
    }

    private void layoutNodes() {
        lblHighscore.getStyleClass().add("title");
        setTop(lblHighscore);
        setAlignment(lblHighscore, Pos.CENTER);

        //gridpane center
        lblName.getStyleClass().add("label35");
        lblName.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: black;");
        lblName.setPadding(new Insets(0,3,5,3));
        lblScore.getStyleClass().add("label35");
        lblScore.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: black");
        lblScore.setPadding(new Insets(0,3,5,3));
        for (int i = 0; i < numbers.length; i++) {
            //numbers 1,2,3,4,5
            numbers[i] = new Label(String.valueOf(i+1));
            numbers[i].getStyleClass().add("label30");
            numbers[i].setPadding(new Insets(0,5,0,0));
            vBoxNumbers.getChildren().add(numbers[i]);
            //names
            lblNames[i].getStyleClass().add("label30");
            lblNames[i].setPadding(new Insets(0,0,0, 5));
            vBoxesName.getChildren().addAll(lblNames[i]);
            //scores
            vBoxesScore.getChildren().add(lblScores[i]);
            lblScores[i].getStyleClass().add("label30");
            lblScores[i].setPadding(new Insets(0,0,0,25));
        }

        //add al the vboxes in the gridpane
        gridPane.add(lblName, 1 ,0 );
        gridPane.add(lblScore, 2, 0);
        gridPane.add(vBoxNumbers, 0, 1);
        gridPane.add(vBoxesName, 1,1);
        gridPane.add(vBoxesScore ,2 ,1 );
        gridPane.setAlignment(Pos.CENTER);
        setCenter(gridPane);
        gridPane.setStyle("-fx-border-color: lightgray; -fx-background-color: rgba(255,255,255,0.7); -fx-border-width: 2px");
        gridPane.setMaxWidth(280);
        gridPane.setMaxHeight(300);
        gridPane.setHgap(10);

        //back button
        setBottom(btnBack);
        setAlignment(btnBack,Pos.CENTER);

        //background
//        setBackground(background);

    }

    Label[] getLblNames() {
        return lblNames;
    }

    Label[] getLblScores() {
        return lblScores;
    }

    Button getBtnBack() {
        return btnBack;
    }

}
