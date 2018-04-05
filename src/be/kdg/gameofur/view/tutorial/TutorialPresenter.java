package be.kdg.gameofur.view.tutorial;

import be.kdg.gameofur.model.GameOfUr;
import be.kdg.gameofur.view.mainmenu.MainMenu;
import be.kdg.gameofur.view.mainmenu.MainMenuPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This presenter links the view with the model
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class TutorialPresenter {
    //attributes
    private Tutorial tutorialView;
    private GameOfUr model;


    //constuctor
    public TutorialPresenter(Tutorial tutorialView, GameOfUr model) {
        this.model = model;
        this.tutorialView = tutorialView;
        addEventHandlers();
        updateView();
    }


    //methods
    private void addEventHandlers() {
        //go to the main screen if pressed on back
        tutorialView.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenu mainMenuView = new MainMenu();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, model);
                tutorialView.getScene().setRoot(mainMenuView);
              //  mainMenuView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {

    }
}
