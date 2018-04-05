package be.kdg.gameofur.view.highscore;

import be.kdg.gameofur.model.GameOfUr;
import be.kdg.gameofur.view.mainmenu.MainMenu;
import be.kdg.gameofur.view.mainmenu.MainMenuPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This presenter links the higscore view with the readwriter model
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class HighscoresPresenter {
    //attributes
    private Highscores highscoresView;
    private GameOfUr model;


    //constructor
    public HighscoresPresenter(Highscores highscoresView, GameOfUr model) {
        this.highscoresView = highscoresView;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    //methods
    private void addEventHandlers() {
        //to read the highscores in the document
        // stringarray to read all the lines
        String[] stringsmoves = model.getReaderWriter().readHighscore();
        String[] moves;
        for (int i = 0; i < stringsmoves.length; i++) {
            //split line to get the second part (score)
            moves = stringsmoves[i].split(";");
            highscoresView.getLblScores()[i].setText(moves[1]);
        }

        //to read the highscorenames in the document
        //stringarray to read all the lines
        String[] stringsnames = model.getReaderWriter().readHighscore();
        String[] names;
        for (int i = 0 ; i<stringsnames.length; i++){
            //split line to get the first part (name)
            names = stringsnames[i].split(";");
            highscoresView.getLblNames()[i].setText(names[0]);
        }

        //go to the main screen if pressed on back
        highscoresView.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenu mainMenuView = new MainMenu();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, model);
                highscoresView.getScene().setRoot(mainMenuView);
                //    highscoresView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {

    }
}
