package api.callback;


import model.Contact;

/**
 * Created by Imant on 05.03.17.
 */
public interface AddContactCallback extends ContactCallback {
    void onSuccess(Contact contact);
}
