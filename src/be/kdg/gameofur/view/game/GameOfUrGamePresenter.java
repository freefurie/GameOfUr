package be.kdg.gameofur.view.game;

import be.kdg.gameofur.model.*;
import be.kdg.gameofur.view.mainmenu.MainMenu;
import be.kdg.gameofur.view.mainmenu.MainMenuPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * This is the presenter of the actual game
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class GameOfUrGamePresenter {
    //attributes
    private GameOfUrGame view;
    private GameOfUr model;

    //constuctor
    public GameOfUrGamePresenter(GameOfUrGame gameOfUrGameView, GameOfUr model) {
        this.view = gameOfUrGameView;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    //methodes


    private void addEventHandlers() {
        //move selected piece
        view.getGridPane().getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    model.move(GridPane.getRowIndex(item), GridPane.getColumnIndex(item));
                    updateView();
                }
            });
        });

        //roll the dice if pressed on the throw button
        view.getBtnThrow().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.getDice().roll();
                model.getDice().setRolled(true);
                if (model.canMovePiece()) {
                    model.getPlayerTurn().setMoved(false);
                } else {
                    model.getPlayerTurn().setMoved(true);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("You can't move any pieces, please end your turn");
                    alert.showAndWait();
                }
                view.animate();
                updateView();
            }
        });

        //undo turn
        view.getBtnUndo().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.undo();
                updateView();
            }
        });

        //end turn and switch players
        view.getBtnEndTurn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    if (model.getPlayerTurn() == model.getPlayers(2)) {
                        model.addTurns();
                    }
                    model.switchTurn();
                    model.getDice().setRolled(false);
                    if (model.isSingleplayer()) {
                        model.moveComputer();
                        model.addTurns();
                        model.switchTurn();
                    }
                    updateView();
            }
        });

        //new game
        view.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Start a new game?");
                alert.getButtonTypes().clear();
                ButtonType ja = new ButtonType("Yes");
                ButtonType nee = new ButtonType("No, keep playing");
                alert.getButtonTypes().addAll(ja, nee);
                alert.showAndWait();
                if (alert.getResult().equals(ja)) {
                    model.reset();
                    updateView();
                }
            }
        });

        //leave game when exit is pressed
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //scherm met opties om op te slaan
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Do you want to save this game?");
                alert.getButtonTypes().clear();
                ButtonType save = new ButtonType("Save");
                ButtonType noSave = new ButtonType("Don't save");
                ButtonType keepPlaying = new ButtonType("Keep playing");
                alert.getButtonTypes().addAll(save, noSave, keepPlaying);
                alert.showAndWait();
                if (alert.getResult().equals(noSave)) {
                    //if players doens't save, exit and reset game
                    model.reset();
                    model.setGesaved(false);
                    MainMenu mainMenuView = new MainMenu();
                    MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, model);
                    view.getScene().setRoot(mainMenuView);
                } else if (alert.getResult().equals(save)) {
                    model.setGesaved(true);
                    model.getReaderWriter().write(false, model.getPlayers(1).toString(), model.getPlayers(2).toString(), model.getTurns(), model.getPlayers(1).getTurn() ? 1 : 2);
                    MainMenu mainMenuView = new MainMenu();
                    MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, model);
                    view.getScene().setRoot(mainMenuView);
                } else {
                    alert.close();
                }
            }
        });

    }

    private void updateView() {
        //menu
        //dices
        if (model.getDice().getRolled()) {
            //dice 1
            if (model.getDice().getValueDice1() == 0) {
                view.getDices()[0].setImage(new Image("/misc/dice" + (model.getDice().getValueDice1() + 2) + ".png"));
            } else if (model.getDice().getValueDice1() == 1) {
                view.getDices()[0].setImage(new Image("/misc/dice" + model.getDice().getValueDice1() + ".png"));
            }
            //dice 2
            if (model.getDice().getValueDice2() == 0) {
                view.getDices()[1].setImage(new Image("/misc/dice" + (model.getDice().getValueDice2() + 2) + ".png"));
            } else if (model.getDice().getValueDice1() == 1) {
                view.getDices()[1].setImage(new Image("/misc/dice" + model.getDice().getValueDice2() + ".png"));
            }
            //dice 3
            if (model.getDice().getValueDice3() == 0) {
                view.getDices()[2].setImage(new Image("/misc/dice" + (model.getDice().getValueDice3() + 2) + ".png"));
            } else if (model.getDice().getValueDice1() == 1) {
                view.getDices()[2].setImage(new Image("/misc/dice" + model.getDice().getValueDice3() + ".png"));
            }
            view.getLblMoves().setText("Moves: " + model.getDice());
        } else {
            Image image = new Image("misc/dice2.png");
            view.getDices()[0].setImage(image);
            view.getDices()[1].setImage(image);
            view.getDices()[2].setImage(image);
            view.getLblMoves().setText("Throw dice");
        }


        //turn
        view.getLblPlayer().setText(model.getPlayerTurn().getName());
        if (!model.getPlayers(1).getTurn()) {
            view.getImagePion().setImage(view.getPionPicMoon());
        } else if (model.getPlayers(1).getTurn()) {
            view.getImagePion().setImage(view.getPionPicSun());
        }

        //disable/enable buttons
        //disable dice when player has thrown
        if (model.getDice().getRolled() && !model.getPlayerTurn().hasMoved()) { //player has rolled but hasn't moved
            view.getBtnThrow().setDisable(true);
            view.getBtnUndo().setDisable(true);
            view.getBtnEndTurn().setDisable(true);
        } else if (model.getDice().getRolled() && model.getPlayerTurn().hasMoved()) { //player has rolled and has moved
            view.getBtnThrow().setDisable(true);
            view.getBtnUndo().setDisable(false);
            view.getBtnEndTurn().setDisable(false);
        } else if (!model.canMovePiece()) { //player can't move a piece
            view.getBtnThrow().setDisable(true);
            view.getBtnUndo().setDisable(true);
            view.getBtnEndTurn().setDisable(false);
        } else { //player has to throw
            view.getBtnThrow().setDisable(false);
            view.getBtnUndo().setDisable(true);
            view.getBtnEndTurn().setDisable(true);
        }

        //update pieces you still have to finish
        view.getLblPiecesMoon().setText(model.getPlayers(2).PiecesRemaining());
        view.getLblPiecesSun().setText(model.getPlayers(1).PiecesRemaining());

        //update label what you have to do
        if (!model.getDice().getRolled()) {
            view.getLblDoThis().setText("Throw the dice");
        } else if (!model.getPlayerTurn().hasMoved()) {
            view.getLblDoThis().setText("Move a piece");
        } else if (model.getDice().getRolled() && model.getPlayerTurn().hasMoved()) {
            view.getLblDoThis().setText("End your turn");
        } else if (!model.canMovePiece()) {
            view.getLblDoThis().setText("No moves avaible, end turn");
        }

        view.getLblTurnsDone().setText(String.valueOf(model.getTurns()));


        //board
        //remove old piece positions
        view.getGridPane().getChildren().removeAll(view.getPiecesSun());
        view.getGridPane().getChildren().removeAll(view.getPiecesMoon());

        //add new piece positions
        int tellerSun = 0;
        for (Piece piece : model.getPlayers(1).getPieces()) {
            int column = model.getPieceColumn(piece.getIndex(), model.getPlayers(1));
            int row = model.getPieceRow(piece.getIndex(), model.getPlayers(1));
            view.getGridPane().add(view.getPiecesSun()[tellerSun], column, row);
            tellerSun++;
        }

        int tellerMoon = 0;
        for (Piece piece : model.getPlayers(2).getPieces()) {
            int column = model.getPieceColumn(piece.getIndex(), model.getPlayers(2));
            int row = model.getPieceRow(piece.getIndex(), model.getPlayers(2));
            view.getGridPane().add(view.getPiecesMoon()[tellerMoon], column, row);
            tellerMoon++;
        }

        if (model.getPlayerTurn().isFinished()) {
            //screen game finished
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You won!!!!");
            alert.showAndWait();

            //check if score is highscore
            model.getReaderWriter().readHighscore();
            String[] stringsmoves = model.getReaderWriter().readHighscore();
            String[] moves;
            int[] beurt = new int[5];
            for (int i = 0; i < stringsmoves.length; i++) {
                moves = stringsmoves[i].split(";");
                beurt[i] = Integer.parseInt(moves[1]);
            }
            boolean checkHighscore = false;
            for (int i = 0; i < 5; i++) {
                if (model.getTurns() < beurt[i] && !checkHighscore && !model.getPlayerTurn().getName().equalsIgnoreCase("computer")) {
                    model.getReaderWriter().write(true, model.getPlayerTurn().getName(), "", model.getTurns(), 0);
                    checkHighscore = true;
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText("Congrats! You made it to the highscores, you're on the "
                            + i + 1 + "th place !");
                    alert1.getButtonTypes().clear();
                    ButtonType great = new ButtonType("Great !");
                    alert1.getButtonTypes().add(great);
                    alert1.showAndWait();
                }
            }

            //reset the game
            model.reset();
        }
    }

}
