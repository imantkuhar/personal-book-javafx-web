package validators;

import model.Contact;

/**
 * Created by Imant on 18.11.16.
 */
public class ContactValidator {
    public boolean checkAllTextField(Contact contact) {
        return !(contact.getName().isEmpty()
                || contact.getPhoneNumber().isEmpty()
                || contact.getAddress().isEmpty()
                || contact.getGroup().isEmpty());
    }
}
