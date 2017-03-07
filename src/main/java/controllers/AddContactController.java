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

/**
 * Created by Imant on 17.11.16.
 */
public class AddContactController extends BaseController implements Initializable {

    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSave;
    @FXML
    private ProgressIndicator piAddContact;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = ContactServiceImpl.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactService.setAddView(this);
        setProgressIndicator(piAddContact);
        setButtonSaveListener();
    }

    private void setButtonSaveListener() {
        btSave.setOnAction(event -> {
            Contact newContact = new Contact(tfName.getText(), tfPhoneNumber.getText(), tfAddress.getText(), tfGroup.getText());
            if (contactValidator.checkAllTextField(newContact)) {
                contactService.addContact(newContact);
            }
        });
    }

    public void makeTextFieldEmpty() {
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfAddress.setText("");
        tfGroup.setText("");
    }

    public void closeStage() {
        stage.close();
    }
}