package be.kdg.gameofur;

import be.kdg.gameofur.model.GameOfUr;
import be.kdg.gameofur.view.mainmenu.MainMenu;
import be.kdg.gameofur.view.mainmenu.MainMenuPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class, here starts the application
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameOfUr model = new GameOfUr();
        MainMenu mainMenuView = new MainMenu();
        MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView,model);
        Scene scene = new Scene(mainMenuView);
        scene.getStylesheets().add("css/main.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("The Game Of Ur");
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(900);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
