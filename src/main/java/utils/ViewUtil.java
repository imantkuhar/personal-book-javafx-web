package utils;

import controllers.EditContactController;
import controllers.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.StartFxApp;
import model.Contact;

import java.io.IOException;

/**
 * Created by Imant on 06.03.17.
 */
public class ViewUtil {

    public static void showMainView() {
        Stage mainStage = StartFxApp.getInstance().getMainStage();
        Scene mainScene = StartFxApp.getInstance().getMainScene();
        mainStage.setScene(mainScene);
    }

    public static void showAddView(MainViewController mainViewController) {
        String ADD_NEW_CONTACT_VIEW_ROOT = PropertiesHolder.getProperty("ADD_NEW_CONTACT_VIEW_ROOT");
        Parent addContactParent = null;
        try {
            addContactParent = FXMLLoader.load(mainViewController.getClass().getClassLoader().getResource(ADD_NEW_CONTACT_VIEW_ROOT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initStyle(StageStyle.UNIFIED);
        stage.setTitle("Add New Contact");
        Scene addContactScene = new Scene(addContactParent);
        stage.setScene(addContactScene);
        stage.show();
    }

    public static void showEditView(MainViewController mainViewController, Contact contact) {

        EditContactController editContactController = new EditContactController();
        editContactController.setContact(contact);

        String EDIT_CONTACT_VIEW_ROOT = PropertiesHolder.getProperty("EDIT_CONTACT_VIEW_ROOT");
        FXMLLoader fxmlEditContact = new FXMLLoader(mainViewController.getClass().getClassLoader().getResource(EDIT_CONTACT_VIEW_ROOT));
        fxmlEditContact.setController(editContactController);
        Parent parent = null;

        try {
            parent = fxmlEditContact.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene editContactScene = new Scene(parent);
        Stage mainStage = StartFxApp.getInstance().getMainStage();
        mainStage.setScene(editContactScene);
    }
}
