package be.kdg.gameofur.view.mainmenu;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * This is the main screen where the application starts, here you can choose to which screen you want to go
 *
 * @author William Van Bouwel
 * @author Anouk As
 */

public class MainMenu extends VBox{
    //attributes
    private Label lblTGoU;
    private Button btnStart;
    private Button btnHighscore;
    private Button btnTutorial;
    //background
//    private Background background;


    //contructor
    public MainMenu(){
        initialiseNodes();
        layoutNodes();
    }


    //methods
    private void initialiseNodes() {
        lblTGoU = new Label("The Royal Game Of Ur");
        btnStart = new Button("Start");
        btnHighscore = new Button("Highscore");
        btnTutorial = new Button("Tutorial");
        //background
//        Image backgroundImage = new Image("/background/");
//        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true);
//        background = new Background(new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,backgroundSize));
    }

    private void layoutNodes() {
        this.getChildren().addAll(lblTGoU,btnStart,btnHighscore, btnTutorial);
        this.setSpacing(75);
        lblTGoU.getStyleClass().add("title");
        lblTGoU.setStyle("-fx-font-style: italic");
        lblTGoU.setTextFill(javafx.scene.paint.Color.GOLD);
        setAlignment(Pos.CENTER);
        //background
//        setBackground(background);
    }

    Button getBtnStart() {
        return btnStart;
    }

    Button getBtnHighscore() {
        return btnHighscore;
    }


    Button getBtnTutorial() {
        return btnTutorial;
    }
}
