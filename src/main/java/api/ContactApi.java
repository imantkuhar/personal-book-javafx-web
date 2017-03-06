package api;

import api.callback.ContactCallback;
import api.callback.DeleteContactCallback;
import api.callback.GetAllContactsCallback;
import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Contact;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Imant on 02.03.17.
 */
public class ContactApi {

    private static ContactApi instance;

    private static final String BASE_URL = "http://localhost:8080";

    public class JsonDateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String s = json.getAsJsonPrimitive().getAsString();
            long l = Long.parseLong(s.substring(6, s.length() - 2));
            Date d = new Date(l);
            return d;
        }
    }

    private ContactApi() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        Unirest.setObjectMapper(new ObjectMapper() {
            public <T> T readValue(String value, Class<T> valueType) {
                return gson.fromJson(value, valueType);
            }

            public String writeValue(Object value) {
                return gson.toJson(value);
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
                    System.out.println("Failed");
                }

                @Override
                public void cancelled() {
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Contact contact, DeleteContactCallback deleteContactCallback) {
        String url = BASE_URL + "/contacts/" + contact.getId();
        try {
            Unirest.delete(url).asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> response) {
                    deleteContactCallback.onSuccess(contact);
                }

                public void failed(UnirestException e) {
                    deleteContactCallback.onError();
                }

                public void cancelled() {
                    deleteContactCallback.onCanceled();
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



}
