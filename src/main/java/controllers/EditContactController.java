package controllers;

import api.callback.UpdateContactCallback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

//import service.ContactServiceImpl;

/**
 * Created by Imant on 17.11.16.
 */
public class EditContactController extends BaseController implements Initializable {
    @FXML
    private Label labelName, labelPhoneNumber, labelAddress, labelGroup;
    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSaveChanges, btGoBack;
    @FXML
    private ProgressIndicator piEditContact;

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = new ContactServiceImpl();

    private Contact contact;

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initContactInfo();
        setButtonSaveChangesListener();
        setButtonGoBackListener();
    }

    private void initContactInfo() {
        tfName.setText(contact.getName());
        tfPhoneNumber.setText(contact.getPhoneNumber());
        tfAddress.setText(contact.getAddress());
        tfGroup.setText(contact.getGroup());
    }

    private void setButtonGoBackListener() {
        btGoBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage mainStage = StartFxApp.getInstance().getMainStage();
                Scene mainScene = StartFxApp.getInstance().getMainScene();
                mainStage.setScene(mainScene);
            }
        });
    }

    private void setButtonSaveChangesListener() {
        btSaveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contact.setName(tfName.getText());
                contact.setPhoneNumber(tfPhoneNumber.getText());
                contact.setAddress(tfAddress.getText());
                contact.setGroup(tfGroup.getText());

                if (contactValidator.checkAllTextField(contact)) {
                    showProgress();
                    contactService.updateContact(contact, updateContactCallback);
                }
            }
        });
    }

    private UpdateContactCallback updateContactCallback = new UpdateContactCallback() {
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
        piEditContact.setVisible(true);
    }

    @Override
    void hideProgress() {
        piEditContact.setVisible(false);
    }
}
