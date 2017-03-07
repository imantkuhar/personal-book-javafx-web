package controllers;

import api.callback.AddContactCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import model.Contact;
import service.ContactServiceImpl;
import utils.ViewUtil;
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
    private ContactServiceImpl contactService = ContactServiceImpl.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactService.setAddView(this);
        setProgressIndicator(piAddContact);
        setButtonSaveListener();
        setButtonGoBackListener();
    }

    private void setButtonSaveListener() {
        btSave.setOnAction(event -> {
            Contact newContact = new Contact(tfName.getText(), tfPhoneNumber.getText(), tfAddress.getText(), tfGroup.getText());
            if (contactValidator.checkAllTextField(newContact)) {
                contactService.addContact(newContact);
            }
        });
    }

    private void setButtonGoBackListener() {
        btGoBack.setOnAction(event -> {
            ViewUtil.showMainView();
        });
    }

    public void makeTextFieldEmpty(){
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfAddress.setText("");
        tfGroup.setText("");
    }
}