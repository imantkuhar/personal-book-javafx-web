package controllers;

import api.callback.DeleteContactCallback;
import api.callback.GetAllContactsCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import main.StartFxApp;
import model.Contact;
import service.ContactServiceImpl;
import utils.PropertiesHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class MainViewController extends BaseController implements Initializable {
    @FXML
    private Button btAdd, btDelete, btEdit, btUpdateTable;
    @FXML
    private TextField tfFindContact;
    @FXML
    private TableView<Contact> tvContactList;
    @FXML
    private TableColumn tcId, tcName, tcNumber, tcAddress, tcGroup;
    private ObservableList<Contact> contactList;
    @FXML
    private ProgressIndicator piContactList;

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
        showProgress();
        contactService.getAllContacts(getAllContactsCallback);
    }

    private void setButtonUpdateContactListListener() {
        btUpdateTable.setOnAction(event -> {
            showProgress();
            contactService.getAllContacts(getAllContactsCallback);
        });
    }

    private void setButtonDeleteContactListener() {
        btDelete.setOnAction(event -> {
            int id = tvContactList.getSelectionModel().getSelectedIndex();
            if (id != -1) {
                Contact contact = contactList.get(id);
                contactService.deleteContact(contact, deleteContactCallback);
            }
        });
    }

    private void setTextFieldFindContactListener() {
        tfFindContact.textProperty().addListener((observable, oldValue, newValue) -> {
            String stringForSearch = tfFindContact.getText();

            if (stringForSearch.isEmpty()) {
                tvContactList.setItems(contactList);
            } else {
                List<Contact> contactListByString = new ArrayList<Contact>();
                for (Contact contact : contactList) {
                    if (contact.toStringForSearch().contains(stringForSearch))
                        contactListByString.add(contact);
                }
                ObservableList<Contact> newList =  FXCollections.observableArrayList(contactListByString);
                tvContactList.setItems(newList);
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

    private void setButtonAddContactListener() {
        btAdd.setOnAction(event -> {
            Stage mainStage = StartFxApp.getInstance().getMainStage();
            Scene addContactScene = StartFxApp.getInstance().getAddContactScene();
            mainStage.setScene(addContactScene);
        });
    }

    private void fillUpContactTable(List<Contact> contacts, TableColumn tcId, TableColumn tcName, TableColumn tcNumber, TableColumn tcAddress, TableColumn tcGroup, TableView tvContactList, ProgressIndicator piContactList) {
        contactList = FXCollections.observableArrayList(contacts);
        tcId.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
        tcNumber.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        tcGroup.setCellValueFactory(new PropertyValueFactory<Contact, String>("group"));
        tvContactList.setItems(contactList);
        piContactList.setVisible(false);
        tvContactList.setVisible(true);
    }

    private GetAllContactsCallback getAllContactsCallback = new GetAllContactsCallback() {
        @Override
        public void onSuccess(List<Contact> contacts) {
            hideProgress();
            fillUpContactTable(contacts, tcId, tcName, tcNumber, tcAddress, tcGroup, tvContactList, piContactList);
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

    private DeleteContactCallback deleteContactCallback = new DeleteContactCallback() {
        @Override
        public void onSuccess() {
            // TODO: 05.03.17 just show alert
        }

        @Override
        public void onError() {
//            TODO:05.03 .17 just show alert
        }

        @Override
        public void onCanceled() {
        }
    };

    @Override
    void showProgress() {
        piContactList.setVisible(true);
    }

    @Override
    void hideProgress() {
        piContactList.setVisible(false);
    }
}
