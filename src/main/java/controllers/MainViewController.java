package controllers;

import api.ContactApi;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Contact;
import service.ContactServiceImpl;
import main.StartFxApp;
import utils.AlertDialogUtil;
import utils.PropertiesHolder;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class MainViewController implements Initializable {
    @FXML
    private Button btAdd, btDelete, btEdit, btUpdateTable;
    @FXML
    private TextField tfFindContact;
    @FXML
    private TableView<Contact> tvContactList;
    @FXML
    private TableColumn tcId, tcName, tcNumber, tcAddress, tcGroup;
    private ObservableList<Contact> contactList;

    private ContactServiceImpl contactService = new ContactServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initContactListView();
        setTextFieldFindContactListener();
        setButtonAddContactListener();
        setButtonDeleteContactListener();
        setButtonUpdateContactListListener();
        setButtonEditContactListener();
    }

    private void initContactListView() {
        ContactApi contactApi = new ContactApi();
        contactApi.getAllContacts(new ContactApi.ContactCallback() {
            @Override
            public void onSuccess(List<Contact> contacts) {
                contactList = FXCollections.observableArrayList(contacts);
                tcId.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("id"));
                tcName.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
                tcNumber.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
                tcAddress.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
                tcGroup.setCellValueFactory(new PropertyValueFactory<Contact, String>("group"));
                tvContactList.setItems(contactList);
            }

            @Override
            public void onError() {
                AlertDialogUtil.showSuccessDialog("xuy", new AlertDialogUtil.AlertDialogCallback() {
                    @Override
                    public void onConfirm() {

                    }

                    @Override
                    public void onCanceled() {

                    }
                });
            }

            @Override
            public void onCancel() {
                System.out.println("Canceled");
            }
        });

        AlertDialogUtil.showSuccessDialog("xuy", new AlertDialogUtil.AlertDialogCallback() {
            @Override
            public void onConfirm() {

            }

            @Override
            public void onCanceled() {

            }
        });
    }

    private void setButtonAddContactListener() {
        btAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage mainStage = StartFxApp.getInstance().getMainStage();
                Scene addContactScene = StartFxApp.getInstance().getAddContactScene();
                mainStage.setScene(addContactScene);
            }
        });
    }

    private void setButtonDeleteContactListener() {
        btDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = tvContactList.getSelectionModel().getSelectedIndex();
                if (id != -1) {
                    Contact contact = contactList.get(id);
                    contactService.deleteContactById(contact.getId());
                    contactList.remove(id);
                }
            }
        });
    }

    private void setButtonEditContactListener() {
        btEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = tvContactList.getSelectionModel().getSelectedIndex();
                if (id != -1) {
                    Contact contact = contactList.get(id);

                    EditContactController editContactController = new EditContactController();
                    editContactController.setContact(contact);

                    String EDIT_CONTACT_VIEW_ROOT = PropertiesHolder.getProperty("EDIT_CONTACT_VIEW_ROOT");
                    FXMLLoader fxmlEditContact = new FXMLLoader(getClass().getClassLoader().getResource(EDIT_CONTACT_VIEW_ROOT));
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
        });
    }

    private void setButtonUpdateContactListListener() {
        btUpdateTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contactList = FXCollections.observableArrayList(contactService.getAllContacts());
                tvContactList.setItems(contactList);
            }
        });
    }

    private void setTextFieldFindContactListener() {
        tfFindContact.textProperty().addListener((observable, oldValue, newValue) -> {
            contactList = FXCollections.observableArrayList(contactService.getAllContactByString(tfFindContact.getText()));
            tvContactList.setItems(contactList);
        });
    }
}
