package service;

import api.ContactApi;
import api.callback.AddContactCallback;
import api.callback.DeleteContactCallback;
import api.callback.GetAllContactsCallback;
import api.callback.UpdateContactCallback;
import model.Contact;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactServiceImpl implements ContactService {

    private ContactApi contactApi;

    public ContactServiceImpl() {
        contactApi = ContactApi.getInstance();
    }

    public void getAllContacts(GetAllContactsCallback callback) {
        contactApi.getAllContacts(callback);
    }

    public void updateContact(Contact contact, UpdateContactCallback callback) {
        contactApi.updateContact(contact, callback);
    }

    public void deleteContact(Contact contact, DeleteContactCallback callback) {
        contactApi.deleteContact(contact, callback);
    }

    public void addContact(Contact contact, AddContactCallback callback) {
        contactApi.addContact(contact, callback);
    }
}
