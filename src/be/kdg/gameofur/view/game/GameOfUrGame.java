package be.kdg.gameofur.view.game;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;


/**
 * This viewclass contains the actual game
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class GameOfUrGame extends BorderPane {
    //Attributes
    //Right vbox buttons
    private VBox vBoxRechts;
    private Label lblPlayer;
    private ImageView imagePion;
    private Image pionPicSun;
    private Image pionPicMoon;
    private Button btnThrow;
    //HBox to put dices next to each other
    private HBox hBoxDices;
    private ImageView[] dices;
    //next part right vbox
    private Label lblMoves;
    private Button btnUndo;
    private Button btnEndTurn;
    private Button btnNewGame;
    private Button btnExit;

    //Bottom hbox for information
    private HBox hBoxOnder;
    private Label lblAction;
    private Label lblDoThis;
    private Label lblPiecesLeft;
    private Label lblPiecesMoon;
    private ImageView imPionLeftMoon;
    private Label lblPiecesSun;
    private ImageView imPionLeftSun;
    private Label lblTurns;
    private Label lblTurnsDone;

    //gridpane in center for the board
    private GridPane gridPane;
    //pieces
    private ImageView[] piecesSun;
    private ImageView[] piecesMoon;
    //actual board
    private ImageView[] tiles;

    //background
//    private Background backgroundVBox;
//    private Background backgroundBoard;


    //constructor
    public GameOfUrGame() {
        initialiseNodes();
        layoutNodes();
    }


    //methods
    private void initialiseNodes() {
        //Right vbox buttons
        vBoxRechts = new VBox();
        lblPlayer = new Label();
        imagePion = new ImageView();
        pionPicMoon = new Image("/pieces/pieceMoon.png");
        pionPicSun = new Image("/pieces/pieceSun.png");
        btnThrow = new Button("Throw");
        //HBox for the dices
        hBoxDices = new HBox(5);
        dices = new ImageView[]{
                new ImageView(),
                new ImageView(),
                new ImageView(),
        };
        //next part right vbox
        lblMoves = new Label();
        btnUndo = new Button("Undo");
        btnEndTurn = new Button("End Turn");
        btnNewGame = new Button("New Game");
        btnExit = new Button("Exit");

        //Bottom hbox for information
        hBoxOnder = new HBox();
        lblAction = new Label("Action:");
        lblDoThis = new Label();
        lblPiecesLeft = new Label("    Pieces left:");
        lblPiecesMoon = new Label();
        imPionLeftMoon = new ImageView(new Image("pieces/pieceMoon.png"));
        lblPiecesSun = new Label();
        imPionLeftSun = new ImageView(new Image("pieces/pieceSun.png"));
        lblTurns = new Label("Turns: ");
        lblTurnsDone = new Label("0 ");

        //gridpane in center for the board
        gridPane = new GridPane();
        piecesSun = new ImageView[7];
        piecesMoon = new ImageView[7];
        //create imageViews for pieces
        for (int i = 0; i < piecesSun.length; i++) {
            piecesSun[i] = new ImageView(new Image("/pieces/pieceSun.png"));
            piecesMoon[i] = new ImageView(new Image("/pieces/pieceMoon.png"));
        }
        tiles = new ImageView[24];

        //backgroundVBox

//        Image backgroundBoardImage = new Image("/background/");
//        Image backgroundVBoxImage = new Image("/background/");
//        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
//        backgroundVBox = new Background(new BackgroundImage(backgroundVBoxImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
//        backgroundBoard = new Background(new BackgroundImage(backgroundBoardImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }

    private void layoutNodes() {
        //right vbox buttons

        vBoxRechts.getChildren().addAll(lblPlayer, imagePion, hBoxDices, btnThrow, lblMoves, btnUndo, btnEndTurn, btnNewGame, btnExit);
        vBoxRechts.setPrefWidth(200);
        vBoxRechts.setSpacing(10);
        vBoxRechts.setStyle("-fx-border-width: 2px; -fx-border-color: black; -fx-background-color: darkgrey");
//        vBoxRechts.setBackground(backgroundVBox);
        setRight(vBoxRechts);
        vBoxRechts.setAlignment(Pos.CENTER);
        lblPlayer.getStyleClass().add("label35");
        lblPlayer.setStyle("-fx-font-weight: bold");
        lblPlayer.setTextFill(Color.WHITE);
        //hbox dices
        hBoxDices.getChildren().addAll(dices[0], dices[1], dices[2]);
        hBoxDices.setAlignment(Pos.CENTER);
        hBoxDices.setPadding(new Insets(2));
        //next part right vbox buttons
        lblMoves.setStyle("-fx-font-weight: bold");
        lblMoves.getStyleClass().add("label35");
        lblMoves.setTextFill(Color.WHITE);
        btnEndTurn.setDisable(true);

        //hbox bottom
        hBoxOnder.getChildren().addAll(lblAction ,lblDoThis, lblPiecesLeft, lblPiecesMoon, imPionLeftMoon , lblPiecesSun, imPionLeftSun, lblTurns, lblTurnsDone);
        setBottom(hBoxOnder);
        hBoxOnder.setAlignment(Pos.CENTER);
        hBoxOnder.setStyle("-fx-border-width: 2px; -fx-border-color: black; -fx-background-color: darkgrey");
        hBoxOnder.setSpacing(10);
        lblAction.setPadding(new Insets(0,5,0,0));
        lblAction.getStyleClass().add("label30");
        lblAction.setTextFill(Color.GRAY);
        lblDoThis.getStyleClass().add("label30");
        lblPiecesLeft.getStyleClass().add("label30");
        lblPiecesLeft.setTextFill(Color.GRAY);
        lblPiecesMoon.getStyleClass().add("label30");
        lblPiecesSun.getStyleClass().add("label30");
        lblTurns.getStyleClass().add("label30");
        lblTurns.setTextFill(Color.GRAY);
        lblTurnsDone.getStyleClass().add("label30");

        //initialise tiles with right images
        for (int i = 0; i < tiles.length; i++) {
            //Rosette tiles
            if (i == 0 || i == 2 || i == 10 || i == 18 || i == 20) {
                tiles[i] = new ImageView(new Image("/tiles/tileSquareBlue.png"));
            } else if (i == 12 || i == 14 || i == 15 || i == 17) {
                //start and end positions
                tiles[i] = new ImageView(new Image("tiles/tileBlank.png"));
            } else {
                //the other tiles with some variations
                if (i % 2 == 0) {
                    tiles[i] = new ImageView(new Image("/tiles/tileSquareBrown.png"));
                } else {
                    tiles[i] = new ImageView(new Image("/tiles/tileBase.png"));
                }
            }
        }

        //put tiles in right order
        int k = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                gridPane.add(tiles[k], j, i);
                tiles[k].setDisable(true);
                k++;
            }
        }

        //add pieces to board beginning
        for (int i = 0; i < piecesSun.length; i++) {
            gridPane.add(piecesSun[i], 0, 4);
            gridPane.add(piecesMoon[i], 2, 4);
        }

        //set gridpane in the center
        setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);
//        gridPane.setBackground(backgroundBoard);
    }

    void animate() {
        //animation dices
        RotateTransition rotate;
        for (int i = 0 ; i < dices.length ; i++ ){
            rotate = new RotateTransition();
            rotate.setNode(dices[i]);
            rotate.setDuration( Duration.seconds((0.8 + (double) i/2)));
            rotate.setByAngle(720);
            rotate.setInterpolator(Interpolator.EASE_OUT);
            rotate.play();
        }
    }

    //vbox right

    Label getLblPlayer() {
        return lblPlayer;
    }

    ImageView getImagePion() {
        return imagePion;
    }

    ImageView[] getDices() {
        return dices;
    }

    Button getBtnThrow() {
        return btnThrow;
    }

    Label getLblMoves() {
        return lblMoves;
    }

    Button getBtnUndo() {
        return btnUndo;
    }

    Button getBtnEndTurn() {
        return btnEndTurn;
    }

    Button getBtnNewGame() {
        return btnNewGame;
    }

    Button getBtnExit() {
        return btnExit;
    }

    //hbox bottom
    Label getLblDoThis() {
        return lblDoThis;
    }

    Label getLblPiecesMoon() {
        return lblPiecesMoon;
    }

    Image getPionPicMoon() {
        return pionPicMoon;
    }

    Label getLblPiecesSun() {
        return lblPiecesSun;
    }

    Image getPionPicSun() {
        return pionPicSun;
    }

    Label getLblTurnsDone() {
        return lblTurnsDone;
    }

    //gridpane center

    GridPane getGridPane() {
        return gridPane;
    }

    ImageView[] getPiecesSun() {
        return piecesSun;
    }

    ImageView[] getPiecesMoon() {
        return piecesMoon;
    }
}