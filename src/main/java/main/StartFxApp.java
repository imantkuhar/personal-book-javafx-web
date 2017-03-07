package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.properties.PropertiesHolder;

/**
 * Created by Imant on 17.11.16.
 */
public class StartFxApp extends Application {

    private Stage mainStage;
    private Scene mainScene;

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        String mainView = PropertiesHolder.getViewProperty(PropertiesHolder.MAIN_VIEW_KEY);
        Parent mainParent = FXMLLoader.load(getClass().getClassLoader().getResource(mainView));

        mainScene = new Scene(mainParent);
        mainStage = stage;
        mainStage.setTitle("Personal book");
        mainStage.getIcons().add(new Image("/png/ic_action_icon.png"));
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}