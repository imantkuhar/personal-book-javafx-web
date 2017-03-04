package service;

import model.Contact;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Imant on 16.11.16.
 */
public interface ContactService {

    boolean addContact(Contact contact);

    boolean deleteContactById(int id);

    boolean updateContact(Contact contact);

    List<Contact> getAllContacts();

    List<Contact> getAllContactByString(String string);
}
