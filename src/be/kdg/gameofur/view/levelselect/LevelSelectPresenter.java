package be.kdg.gameofur.view.levelselect;

import be.kdg.gameofur.model.GameOfUr;
import be.kdg.gameofur.model.GameOfUrException;
import be.kdg.gameofur.view.game.GameOfUrGame;
import be.kdg.gameofur.view.game.GameOfUrGamePresenter;
import be.kdg.gameofur.view.mainmenu.MainMenu;
import be.kdg.gameofur.view.mainmenu.MainMenuPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 * This presenter links the levelselect view with the model
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class LevelSelectPresenter {
    //attributes
    private LevelSelect levelSelectView;
    private GameOfUr model;


    //constuctor
    public LevelSelectPresenter(LevelSelect levelSelectView, GameOfUr model) {
        this.levelSelectView = levelSelectView;
        this.model = model;
        addEventHandlers();
        updateView();
    }


    //methods
    private void addEventHandlers() {
        //set player 2 textfield and label invisible if singleplayer is selected (default)
        levelSelectView.getBtnSingle().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                levelSelectView.getLblPlayer2().setVisible(false);
                levelSelectView.getTxfPlayer2().setVisible(false);
            }
        });

        //set player 2 textfield and label visible if multiplayer is selected
        levelSelectView.getBtnMulti().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                levelSelectView.getLblPlayer2().setVisible(true);
                levelSelectView.getTxfPlayer2().setVisible(true);
            }
        });

        //go to the game if start is selected
        levelSelectView.getBtnStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if singleplayer is selected
                if (levelSelectView.getBtnSingle().isSelected()) {
                    //if single is selected give player 2 the name Computer
                    try {
                        model.setSingleplayer();
                        model.getPlayers(2).setName("Computer");
                        model.getPlayers(1).setName(levelSelectView.getTxfPlayer1().getText());
                    } catch (GameOfUrException e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(e.getMessage());
                        alert.showAndWait();
                    }
                }
                //if multiplayer is selected
                else if (levelSelectView.getBtnMulti().isSelected()){
                    try {
                        model.getPlayers(1).setName(levelSelectView.getTxfPlayer1().getText());
                        model.getPlayers(2).setName(levelSelectView.getTxfPlayer2().getText());
                    } catch (GameOfUrException e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(e.getMessage());
                        alert.showAndWait();
                    }
                }

                //if both the names are given than go to the game screen
                if (!model.isGesaved()){
                    if (!model.getPlayers(1).getName().isEmpty() && !model.getPlayers(2).getName().isEmpty()) {
                        GameOfUrGame gameOfUrGameView = new GameOfUrGame();
                        GameOfUrGamePresenter gameOfUrGamePresenter = new GameOfUrGamePresenter(gameOfUrGameView, model);
                        levelSelectView.getScene().setRoot(gameOfUrGameView);
                    }
                }
                else {
                    model.load();
                    if (!model.getPlayers(1).getName().isEmpty() && !model.getPlayers(2).getName().isEmpty()) {
                        GameOfUrGame gameOfUrGameView = new GameOfUrGame();
                        GameOfUrGamePresenter gameOfUrGamePresenter = new GameOfUrGamePresenter(gameOfUrGameView, model);
                        levelSelectView.getScene().setRoot(gameOfUrGameView);
                    }
                    updateView();
                }
            }
        });

        //go back to mainscreen if back is selected
        levelSelectView.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenu mainMenuView = new MainMenu();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView,model);
                levelSelectView.getScene().setRoot(mainMenuView);
                //      mainMenuView.getScene().getWindow().sizeToScene();
            }
        });

    }

    private void updateView() {

    }
}
