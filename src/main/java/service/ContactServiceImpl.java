package service;

import api.ContactApi;
import api.callback.AddContactCallback;
import api.callback.DeleteContactCallback;
import api.callback.GetAllContactsCallback;
import api.callback.UpdateContactCallback;
import controllers.AddContactController;
import controllers.EditContactController;
import controllers.MainViewController;
import javafx.application.Platform;
import model.Contact;

import java.util.List;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactServiceImpl implements ContactService {

    private ContactApi contactApi;
    private MainViewController mainView;
    private EditContactController editView;
    private AddContactController addView;

    private static ContactServiceImpl instance;

    public void setAddView(AddContactController addView) {
        this.addView = addView;
    }

    public void setMainView(MainViewController mainView) {
        this.mainView = mainView;
    }

    public void setEditView(EditContactController editView) {
        this.editView = editView;
    }

    public static ContactServiceImpl getInstance() {
        if (instance == null)
            instance = new ContactServiceImpl();
        return instance;
    }

    public ContactServiceImpl() {
        contactApi = ContactApi.getInstance();
    }

    public void getAllContacts() {
        mainView.showProgress();
        contactApi.getAllContacts(new GetAllContactsCallback() {
            @Override
            public void onSuccess(List<Contact> contacts) {
                Platform.runLater(() -> {
                    mainView.showContacts(contacts);
                    mainView.hideProgress();
                });
            }

            @Override
            public void onError() {
                mainView.hideProgress();
            }

            @Override
            public void onCanceled() {
                mainView.hideProgress();
            }
        });
    }

    public void deleteContact(Contact contact) {
        mainView.showProgress();
        contactApi.deleteContact(contact, new DeleteContactCallback() {
            @Override
            public void onSuccess(Contact contact) {
                mainView.hideProgress();
                mainView.deleteContactFromTable(contact);
            }

            @Override
            public void onError() {
                mainView.hideProgress();
            }

            @Override
            public void onCanceled() {
                mainView.hideProgress();
            }
        });
    }

    public void addContact(Contact contact) {
        addView.showProgress();
        contactApi.addContact(contact, new AddContactCallback() {
            @Override
            public void onSuccess(Contact contact) {
                addView.makeTextFieldEmpty();
                addView.hideProgress();
                mainView.addContactInTable(contact);
                Platform.runLater(() -> addView.closeStage());
            }

            @Override
            public void onError() {
                addView.hideProgress();
            }

            @Override
            public void onCanceled() {
                addView.hideProgress();
            }
        });
    }

    public void updateContact(Contact contact) {
        editView.showProgress();
        contactApi.updateContact(contact, new UpdateContactCallback() {
            @Override
            public void onSuccess(Contact contact) {
                Platform.runLater(() -> {
                    editView.hideProgress();
                    Platform.runLater(() -> editView.closeStage());
                    mainView.updateContact();
                });
            }

            @Override
            public void onError() {
                editView.hideProgress();
            }

            @Override
            public void onCanceled() {
                editView.hideProgress();
            }
        });
    }
}
