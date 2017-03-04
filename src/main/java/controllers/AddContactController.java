package controllers;

import api.ContactApi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.StartFxApp;
import model.Contact;
import service.ContactServiceImpl;
import utils.PropertiesHolder;
import validators.ContactValidator;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class AddContactController implements Initializable {
    @FXML
    private Label labelName, labelPhoneNumber, labelAddress, labelGroup;
    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSave, btGoBack;

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = new ContactServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setButtonSaveListener();
        setButtonGoBackListener();
    }

//    private void setButtonSaveListener() {
//        btSave.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Contact newContact = new Contact(tfName.getText(), tfPhoneNumber.getText(), tfAddress.getText(), tfGroup.getText());
//                if (contactValidator.checkAllTextField(newContact)) {
//
//                    ContactApi contactApi = new ContactApi();
//                    contactApi.addContact(newContact, new ContactApi.ContactCallback() {
//                        @Override
//                        public void onSuccess() {
//                            Stage mainStage = StartFxApp.getInstance().getMainStage();
//                            Scene massageScene = StartFxApp.getInstance().getAddContactScene();
//                            mainStage.setScene(massageScene);
//                            System.out.println("Contact saved successfully");
//                            tfName.setText("");
//                            tfPhoneNumber.setText("");
//                            tfAddress.setText("");
//                            tfGroup.setText("");
//                        }
//
//                        @Override
//                        public void onError() {
//                            System.out.println("Error");
//                        }
//
//                        @Override
//                        public void onCancel() {
//                            System.out.println("Canceled");
//                        }
//                    });
//                }
//            }
//        });
//    }

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
}