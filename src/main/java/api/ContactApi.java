package api;

import api.callback.ContactCallback;
import api.callback.DeleteContactCallback;
import api.callback.GetAllContactsCallback;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Contact;

import java.util.Arrays;

/**
 * Created by Imant on 02.03.17.
 */
public class ContactApi {

    private static ContactApi instance;

    private static final String BASE_URL = "http://localhost:8080";

    private ContactApi() {
        Unirest.setObjectMapper(new ObjectMapper() {
            public <T> T readValue(String value, Class<T> valueType) {
                return new Gson().fromJson(value, valueType);
            }

            public String writeValue(Object value) {
                return new Gson().toJson(value);
            }
        });
    }

    public static ContactApi getInstance() {
        if (instance == null)
            instance = new ContactApi();
        return instance;
    }

    public void getAllContacts(GetAllContactsCallback callback) {
        String url = BASE_URL + "/contacts";
        try {
            Unirest.get(url).asObjectAsync(Contact[].class, new Callback<Contact[]>() {
                @Override
                public void completed(HttpResponse<Contact[]> response) {
                    callback.onSuccess(Arrays.asList(response.getBody()));
                }

                @Override
                public void failed(UnirestException e) {

                }

                @Override
                public void cancelled() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteContact(Contact contact, DeleteContactCallback deleteContactCallback) {
        try {
            Unirest.delete(BASE_URL).body(contact).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {

                }

                public void failed(UnirestException e) {

                }

                public void cancelled() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact contact, ContactCallback callback) {
        try {
            Unirest.put(BASE_URL).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {

                }

                public void failed(UnirestException e) {

                }

                public void cancelled() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addContact(Contact contact, ContactCallback callback) {
        try {
            Unirest.post(BASE_URL).body(contact).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {

                }

                public void failed(UnirestException e) {

                }

                public void cancelled() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
