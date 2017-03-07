package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Contact;
import service.ContactServiceImpl;
import utils.PropertiesHolder;
import utils.ViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
    private TableColumn tcId, tcName, tcNumber, tcAddress, tcGroup, tcDate;
    private ObservableList<Contact> contactList;
    @FXML
    private ProgressIndicator piContactList;

    private ContactServiceImpl contactService = ContactServiceImpl.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactService.setMainView(this);
        setProgressIndicator(piContactList);
        initContactListView();
        setTextFieldFindContactListener();
        setButtonAddContactListener();
        setButtonDeleteContactListener();
        setButtonUpdateContactListListener();
        setButtonEditContactListener();
    }

    private void initContactListView() {
        contactService.getAllContacts();
    }

    private void setButtonUpdateContactListListener() {
        btUpdateTable.setOnAction(event -> {
            contactService.getAllContacts();
        });
    }

    private void setButtonDeleteContactListener() {
        btDelete.setOnAction(event -> {
            int id = tvContactList.getSelectionModel().getSelectedIndex();
            if (id != -1) {
                Contact contact = contactList.get(id);
                contactService.deleteContact(contact);
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
                ObservableList<Contact> newList = FXCollections.observableArrayList(contactListByString);
                tvContactList.setItems(newList);
            }
        });
    }

    private void setButtonEditContactListener() {
        btEdit.setOnAction(event -> {
            int id = tvContactList.getSelectionModel().getSelectedIndex();
            if (id != -1) {
                Contact contact = contactList.get(id);
                ViewUtil.showEditView(MainViewController.this, contact);
            }
        });
    }

    private void setButtonAddContactListener() {
        btAdd.setOnAction(event -> {
            ViewUtil.showAddView(MainViewController.this);
        });
    }

    public void showContacts(List<Contact> contacts) {
        fillUpContactTable(contacts, tcId, tcName, tcNumber, tcAddress, tcGroup, tcDate, tvContactList, piContactList);
    }

    private void fillUpContactTable(List<Contact> contacts, TableColumn tcId, TableColumn tcName, TableColumn tcNumber, TableColumn tcAddress, TableColumn tcGroup, TableColumn tcDate, TableView tvContactList, ProgressIndicator piContactList) {
        contactList = FXCollections.observableArrayList(contacts);
        tcId.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
        tcNumber.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        tcGroup.setCellValueFactory(new PropertyValueFactory<Contact, String>("group"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Contact, String>("date"));
        tvContactList.setItems(contactList);
        piContactList.setVisible(false);
        tvContactList.setVisible(true);
    }

    public void addContactInTable(Contact contact) {
        contactList.add(contact);
    }

    public void deleteContactFromTable(Contact contact) {
        Iterator<Contact> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact nextContact = iterator.next();
            if (nextContact.getId() == contact.getId())
                contactList.remove(nextContact);
        }
    }

    public void updateContact(Contact contact) {
        Iterator<Contact> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact nextContact = iterator.next();
            if (nextContact.getId() == contact.getId()){
                nextContact.setName(contact.getName());
                nextContact.setPhoneNumber(contact.getPhoneNumber());
                nextContact.setAddress(contact.getAddress());
                nextContact.setGroup(contact.getGroup());
                nextContact.setDate(contact.getDate());
            }
        }
    }
}
