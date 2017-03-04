package service;

import db.ContactDao;
import model.Contact;

import java.util.List;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;

    public ContactServiceImpl() {
        contactDao = ContactDao.getInstance();
    }

    public boolean addContact(Contact contact) {
        return contactDao.addContact(contact);
    }

    public boolean deleteContactById(int id) {
        return contactDao.deleteContact(id);
    }

    public boolean updateContact(Contact contact) {
        return contactDao.updateContact(contact);
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public List<Contact> getAllContactByString(String stringForSearch) {
        return contactDao.getAllContactByString(stringForSearch);
    }
}
