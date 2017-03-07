package main;

import controllers.AddContactController;
import controllers.EditContactController;
import controllers.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.PropertiesHolder;

/**
 * Created by Imant on 17.11.16.
 */
public class StartFxApp extends Application {

    private Stage mainStage;
    private Scene mainScene;
    private Scene addContactScene;
    private Scene editContactScene;
    private Scene messageScene;
    private MainViewController mainViewController;
    private EditContactController editContactController;
    private AddContactController addContactController;

    private static StartFxApp instance;

    public static StartFxApp getInstance() {
        return instance;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Scene getAddContactScene() {
        return addContactScene;
    }

    public Scene getEditContactScene() {
        return editContactScene;
    }

    public Scene getMessageScene() {
        return messageScene;
    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }

    public EditContactController getEditContactController() {
        return editContactController;
    }

    public void setEditContactController(EditContactController editContactController) {
        this.editContactController = editContactController;
    }

    public AddContactController getAddContactController() {
        return addContactController;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        String MAIN_VIEW_ROOT = PropertiesHolder.getProperty("MAIN_VIEW_ROOT");
        Parent mainParent = FXMLLoader.load(getClass().getClassLoader().getResource(MAIN_VIEW_ROOT));

        mainScene = new Scene(mainParent);
        mainStage = stage;
        String PERSONAL_BOOK_TITLE = PropertiesHolder.getProperty("PERSONAL_BOOK_TITLE");
        mainStage.setTitle(PERSONAL_BOOK_TITLE);
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}