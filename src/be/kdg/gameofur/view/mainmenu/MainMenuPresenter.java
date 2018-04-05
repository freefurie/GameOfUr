package be.kdg.gameofur.view.mainmenu;

import be.kdg.gameofur.model.GameOfUr;
import be.kdg.gameofur.view.highscore.Highscores;
import be.kdg.gameofur.view.highscore.HighscoresPresenter;
import be.kdg.gameofur.view.levelselect.LevelSelect;
import be.kdg.gameofur.view.levelselect.LevelSelectPresenter;
import be.kdg.gameofur.view.tutorial.Tutorial;
import be.kdg.gameofur.view.tutorial.TutorialPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This presenter links the mainmenu view with the model
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class MainMenuPresenter {
    //attributes
    private MainMenu mainMenuView;
    private GameOfUr model;


    //consturctor
    public MainMenuPresenter(MainMenu mainMenuView, GameOfUr model) {
        this.model = model;
        this.mainMenuView = mainMenuView;
        addEventHandlers();
        updateView();
    }


    //methods
    private void addEventHandlers() {
        //if pressed on start go to levelselect
        mainMenuView.getBtnStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LevelSelect levelSelectView = new LevelSelect();
                LevelSelectPresenter levelSelectPresenter = new LevelSelectPresenter(levelSelectView, model);
                mainMenuView.getScene().setRoot(levelSelectView);
            }
        });

        //if pressed on Highscores go to Highscores
        mainMenuView.getBtnHighscore().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Highscores highscoresView = new Highscores();
                HighscoresPresenter highscoresPresenter = new HighscoresPresenter(highscoresView, model);
                mainMenuView.getScene().setRoot(highscoresView);
            }
        });

        //if pressed on tutorial go to tutorial
        mainMenuView.getBtnTutorial().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tutorial tutorialView = new Tutorial();
                TutorialPresenter tutorialPresenter = new TutorialPresenter(tutorialView, model);
                mainMenuView.getScene().setRoot(tutorialView);
               // tutorialView.getScene().getWindow().sizeToScene();
            }
        });

    }

    private void updateView() {

    }
}
