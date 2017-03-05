package service;

import api.callback.AddContactCallback;
import api.callback.DeleteContactCallback;
import api.callback.GetAllContactsCallback;
import api.callback.UpdateContactCallback;
import model.Contact;

/**
 * Created by Imant on 16.11.16.
 */
public interface ContactService {

    void getAllContacts(GetAllContactsCallback callback);

    void updateContact(Contact contact, UpdateContactCallback callback);

    void deleteContact(Contact contact, DeleteContactCallback callback);

    void addContact(Contact contact, AddContactCallback callback);

}
