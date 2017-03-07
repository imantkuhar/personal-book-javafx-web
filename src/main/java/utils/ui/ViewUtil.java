package utils.ui;

import controllers.AddContactController;
import controllers.EditContactController;
import controllers.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Contact;
import utils.properties.PropertiesHolder;

import java.io.IOException;

/**
 * Created by Imant on 06.03.17.
 */
public class ViewUtil {


    public static void showAddView(MainViewController mainViewController) {

        String addViewPath = PropertiesHolder.getViewProperty(PropertiesHolder.ADD_NEW_CONTACT_VIEW_KEY);
        FXMLLoader loader = new FXMLLoader(mainViewController.getClass().getClassLoader().getResource(addViewPath));
        Parent addContactParent = null;

        try {
            addContactParent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new contact");
        stage.getIcons().add(new Image("/png/ic_action_icon.png"));
        Scene addContactScene = new Scene(addContactParent);

        stage.setScene(addContactScene);
        stage.show();
        AddContactController addContactController = loader.getController();
        addContactController.setStage(stage);
    }

    public static void showEditView(MainViewController mainViewController, Contact contact) {

        String editViewPath = PropertiesHolder.getViewProperty(PropertiesHolder.EDIT_CONTACT_VIEW_KEY);
        FXMLLoader loader = new FXMLLoader(mainViewController.getClass().getClassLoader().getResource(editViewPath));
        Parent editContactParent = null;

        try {
            editContactParent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit contact");
        stage.getIcons().add(new Image("/png/ic_action_icon.png"));
        Scene editContactScene = new Scene(editContactParent);
        stage.setScene(editContactScene);
        stage.show();
        EditContactController editContactController = ((EditContactController) loader.getController());
        editContactController.setStage(stage);
        editContactController.initContactInfo(contact);
    }
}
