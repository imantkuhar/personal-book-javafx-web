package controllers;

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
    private ContactServiceImpl contactService = ContactServiceImpl.getInstance();

    private Contact contact;

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactService.setEditView(this);
        setProgressIndicator(piEditContact);
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
        btGoBack.setOnAction(event -> ViewUtil.showMainView());
    }

    private void setButtonSaveChangesListener() {
        btSaveChanges.setOnAction(event -> {
            contact.setName(tfName.getText());
            contact.setPhoneNumber(tfPhoneNumber.getText());
            contact.setAddress(tfAddress.getText());
            contact.setGroup(tfGroup.getText());
            if (contactValidator.checkAllTextField(contact)) {
                contactService.updateContact(contact);
            }
        });
    }
}
