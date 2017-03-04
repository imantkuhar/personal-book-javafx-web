package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imant on 02.03.17.
 */
public class ContactApi {

    public interface ContactCallback {
        void onSuccess(List<Contact> contacts);

        void onError();

        void onCancel();
    }

    public void addContact(Contact contact, ContactCallback callback) {
        String fullURL = "https://jsonplaceholder.typicode.com/posts/35";
        try {
            Unirest.post(fullURL).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {
//                    String dtr = "{[]}"
//
//                    callback.onSuccess();
                }

                public void failed(UnirestException e) {
                    callback.onError();
                }

                public void cancelled() {
                    callback.onCancel();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllContacts(ContactCallback callback) {
        String fullURL = "https://jsonplaceholder.typicode.com/posts/35";
        try {
            Unirest.get(fullURL).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {
                    String contacts = "[{\"id\":9,\"name\":\"Andrey\",\"phoneNumber\":\"093-243-5861\",\"address\":\"Kharkiv\",\"group\":\"Jobs\",\"date\":\"Nov 23, 2016 2:21:00 AM\"},{\"id\":11,\"name\":\"Ivan\",\"phoneNumber\":\"067-684-2475\",\"address\":\"Lviv\",\"group\":\"Friend\",\"date\":\"Jan 17, 2017 10:24:00 PM\"},{\"id\":12,\"name\":\"Dima\",\"phoneNumber\":\"093-243-5861\",\"address\":\"Odessa\",\"group\":\"Job85\",\"date\":\"Jan 17, 2017 10:24:00 PM\"},{\"id\":13,\"name\":\"Roma\",\"phoneNumber\":\"097-385-8713\",\"address\":\"Kiev\",\"group\":\"Genesis Group\",\"date\":\"Jan 17, 2017 10:24:00 PM\"},{\"id\":14,\"name\":\"Andrey\",\"phoneNumber\":\"093-243-5861\",\"address\":\"Kharkiv\",\"group\":\"Job\",\"date\":\"Jan 17, 2017 10:24:00 PM\"},{\"id\":16,\"name\":\"Dima\",\"phoneNumber\":\"093-243-5861\",\"address\":\"Odessa\",\"group\":\"Job\",\"date\":\"Jan 17, 2017 10:25:00 PM\"},{\"id\":17,\"name\":\"Roma\",\"phoneNumber\":\"097-385-8713\",\"address\":\"Kiev\",\"group\":\"Genesis Group\",\"date\":\"Jan 17, 2017 10:25:00 PM\"},{\"id\":18,\"name\":\"Andrey\",\"phoneNumber\":\"093-243-5861\",\"address\":\"Kharkiv\",\"group\":\"Job\",\"date\":\"Jan 17, 2017 10:25:00 PM\"},{\"id\":20,\"name\":\"Dima\",\"phoneNumber\":\"093-243-5861\",\"address\":\"Odessa\",\"group\":\"Jobli\",\"date\":\"Jan 17, 2017 10:27:00 PM\"},{\"id\":21,\"name\":\"Roma\",\"phoneNumber\":\"097-385-8713\",\"address\":\"Kiev\",\"group\":\"Genesis Group\",\"date\":\"Jan 17, 2017 10:27:00 PM\"},{\"id\":22,\"name\":\"Andrey\",\"phoneNumber\":\"093-243-5861\",\"address\":\"Kharkiv\",\"group\":\"Job\",\"date\":\"Jan 17, 2017 10:27:00 PM\"}]";
                    Type listType =  new TypeToken<ArrayList<Contact>>(){}.getType();
                    List<Contact> contactList = new Gson().fromJson(contacts,  listType);
                    callback.onSuccess(contactList);
                }

                public void failed(UnirestException e) {
                    callback.onError();
                }

                public void cancelled() {
                    callback.onCancel();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getContact(Contact contact, ContactCallback callback) {
        String fullURL = "https://jsonplaceholder.typicode.com/posts/35";
        try {
            Unirest.get(fullURL).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {

                }

                public void failed(UnirestException e) {
                    callback.onError();
                }

                public void cancelled() {
                    callback.onCancel();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact contact, ContactCallback callback) {
        String fullURL = "https://jsonplaceholder.typicode.com/posts/35";
        try {
            Unirest.put(fullURL).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {
                    callback.onSuccess(null);
                }

                public void failed(UnirestException e) {
                    callback.onError();
                }

                public void cancelled() {
                    callback.onCancel();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
