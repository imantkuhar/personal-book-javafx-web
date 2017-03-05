package controllers;

import api.callback.AddContactCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.StartFxApp;
import model.Contact;
import service.ContactServiceImpl;
import validators.ContactValidator;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class AddContactController extends BaseController implements Initializable {
    @FXML
    private Label labelName, labelPhoneNumber, labelAddress, labelGroup;
    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSave, btGoBack;
    @FXML
    private ProgressIndicator piAddContact;

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = new ContactServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setButtonSaveListener();
        setButtonGoBackListener();
    }

    private void setButtonSaveListener() {
        btSave.setOnAction(event -> {
            Contact newContact = new Contact(tfName.getText(), tfPhoneNumber.getText(), tfAddress.getText(), tfGroup.getText());
            if (contactValidator.checkAllTextField(newContact)) {
                showProgress();
                contactService.addContact(newContact, addContactCallback);
            }
        });
    }

    private void setButtonGoBackListener() {
        btGoBack.setOnAction(event -> {
            Stage mainStage = StartFxApp.getInstance().getMainStage();
            Scene mainScene = StartFxApp.getInstance().getMainScene();
            mainStage.setScene(mainScene);
        });
    }

    private AddContactCallback addContactCallback = new AddContactCallback() {
        @Override
        public void onSuccess() {
            hideProgress();
        }

        @Override
        public void onError() {
            hideProgress();
        }

        @Override
        public void onCanceled() {
            hideProgress();
        }
    };

    @Override
    void showProgress() {
        piAddContact.setVisible(true);
    }

    @Override
    void hideProgress() {
        piAddContact.setVisible(false);
    }
}