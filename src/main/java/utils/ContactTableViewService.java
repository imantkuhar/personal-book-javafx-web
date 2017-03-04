package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Contact;

import java.util.List;

/**
 * Created by Imant on 04.03.17.
 */
public class ContactTableViewService {

    public static void fillUpContactTable(ObservableList<Contact> contactList, List<Contact> contacts, TableColumn tcId, TableColumn tcName, TableColumn tcNumber, TableColumn tcAddress, TableColumn tcGroup, TableView tvContactList, ProgressIndicator piContactList) {
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
}
