package be.kdg.gameofur.view.levelselect;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * In this viewclass you choose your playername and if you want to play single or multiplayer
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class LevelSelect extends BorderPane {
    //attributes
    //gridpane in center for al the attributes
    private GridPane gridPane;
    //for singleplayer
    private RadioButton btnSingle;
    private Label lblPlayer1;
    private TextField txfPlayer1;
    //for multiplayer
    private RadioButton btnMulti;
    private Label lblPlayer2;
    private TextField txfPlayer2;
    private Button btnStart;
    private Button btnBack;
    //HBox for start and back
    private VBox vBox;

    //background
//    private Background background;


    //constuctor
    public LevelSelect(){
        initialiseNodes();
        layoutNodes();
    }


    //methodes
    private void initialiseNodes() {
        //gridpane in center
        gridPane = new GridPane();
        btnSingle = new RadioButton("Singleplayer");
        lblPlayer1 = new Label("Name player: ");
        txfPlayer1 = new TextField();
        btnMulti = new RadioButton("Multiplayer");
        lblPlayer2 = new Label("Name player 2:");
        txfPlayer2 = new TextField();
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(btnMulti,btnSingle);

        //buttons
        vBox = new VBox();
        btnStart = new Button("Start Game");
        btnBack = new Button("Back");

        //background
//        Image backgroundImage = new Image("/background/");
//        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true);
//        background = new Background(new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,backgroundSize));
    }

    private void layoutNodes() {
        //add all the attributes to the gridpane
        gridPane.add(btnSingle,0,0);
        //singleplayer (default)
        btnSingle.setSelected(true);
        gridPane.add(lblPlayer1,0,1);
        setMargin(lblPlayer1,new Insets(10));
        gridPane.add(txfPlayer1,0,2);
        //multiplayer
        gridPane.add(btnMulti,1,0);
        gridPane.add(lblPlayer2,1,1);
        setMargin(lblPlayer2,new Insets(10));
        lblPlayer2.setVisible(false);
        gridPane.add(txfPlayer2,1,2);
        txfPlayer2.setVisible(false);
        setCenter(gridPane);
        setAlignment(gridPane,Pos.CENTER);
        //start and back
        vBox.getChildren().addAll(btnStart, btnBack);
        setBottom(vBox);
        setAlignment(vBox, Pos.CENTER);
        vBox.setPadding(new Insets(10));
//        setRight(btnStart);
//        setAlignment(btnStart,Pos.CENTER);
//        setBottom(btnBack);
//        setAlignment(btnBack,Pos.CENTER);

        gridPane.setHgap(40);
        gridPane.setVgap(20);
        setPadding(new Insets(40));
        setMargin(gridPane, new Insets(100,10,10,120));

        //background
//        setBackground(background);

    }

    RadioButton getBtnSingle() {
        return btnSingle;
    }

    TextField getTxfPlayer1() {
        return txfPlayer1;
    }

    RadioButton getBtnMulti() {
        return btnMulti;
    }

    TextField getTxfPlayer2() {
        return txfPlayer2;
    }

    Label getLblPlayer2() {
        return lblPlayer2;
    }

    Button getBtnStart() {
        return btnStart;
    }

    Button getBtnBack() {
        return btnBack;
    }

}
