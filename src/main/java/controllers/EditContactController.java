package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import service.ContactServiceImpl;
import utils.validators.ContactValidator;

import java.net.URL;
import java.util.ResourceBundle;

//import service.ContactServiceImpl;

/**
 * Created by Imant on 17.11.16.
 */
public class EditContactController extends BaseController implements Initializable {
    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSave;
    @FXML
    private ProgressIndicator piEditContact;

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = ContactServiceImpl.getInstance();

    private Contact contact;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactService.setEditView(this);
        setProgressIndicator(piEditContact);
        setButtonSaveChangesListener();
    }

    public void initContactInfo(Contact contact) {
        this.contact = contact;
        tfName.setText(contact.getName());
        tfPhoneNumber.setText(contact.getPhoneNumber());
        tfAddress.setText(contact.getAddress());
        tfGroup.setText(contact.getGroup());
    }

    private void setButtonSaveChangesListener() {
        btSave.setOnAction(event -> {
            contact.setName(tfName.getText());
            contact.setPhoneNumber(tfPhoneNumber.getText());
            contact.setAddress(tfAddress.getText());
            contact.setGroup(tfGroup.getText());
            if (contactValidator.checkAllTextField(contact)) {
                contactService.updateContact(contact);
            }
        });
    }

    public void closeStage() {
        stage.close();
    }
}
