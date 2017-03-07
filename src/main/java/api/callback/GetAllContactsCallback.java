package api.callback;

import model.Contact;
import java.util.List;

/**
 * Created by Imant on 05.03.17.
 */
public interface GetAllContactsCallback extends ContactCallback{
    void onSuccess(List<Contact> contacts);
}
