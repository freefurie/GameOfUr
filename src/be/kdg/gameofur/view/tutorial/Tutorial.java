package be.kdg.gameofur.view.tutorial;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * This class shows the tutorial of the game with a picture of the board with the two paths and an text explanation under it
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class Tutorial extends BorderPane {
    //attributes
    private Label lblTutorial;
    private ImageView ivBordTut;
    private VBox vBox;
    private Button btnBack;
    //background
//    private Background background;


    //constructor
    public Tutorial(){
        initialiseNodes();
        layoutNodes();
    }


    //methods
    private void initialiseNodes() {
        lblTutorial = new Label("tutorial");
        Image bordTutorial = new Image("/misc/bordTutorial.png");
        ivBordTut = new ImageView(new Image("/misc/bordTutorial.png"));
        vBox = new VBox();
        btnBack = new Button("Back");
        //background
//        Image backgroundImage = new Image("/background/");
//        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true);
//        background = new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,backgroundSize));
    }

    private void layoutNodes() {
        lblTutorial.getStyleClass().add("title");
        setTop(lblTutorial);
        setAlignment(lblTutorial,Pos.CENTER);
        //center: image Bord
        setCenter(ivBordTut);
        setAlignment(ivBordTut,Pos.CENTER);
        ivBordTut.preserveRatioProperty().setValue(true);
        ivBordTut.setPreserveRatio(true);
        //bottom: back
        setAlignment(btnBack, Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(btnBack);
        vBox.setSpacing(5);
        setBottom(vBox);
        //padding of borderpane
        this.setPadding(new Insets(20));
        //background
//        setBackground(background);
    }

    Button getBtnBack() {
        return btnBack;
    }
}
