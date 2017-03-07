package utils.properties;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Imant on 30.10.16.
 */
public class PropertiesHolder {

    public static final String BASE_URL_KEY = "BASE_URL";

    public static final String MAIN_VIEW_KEY = "MAIN_VIEW_ROOT";
    public static final String EDIT_CONTACT_VIEW_KEY = "EDIT_CONTACT_VIEW_ROOT";
    public static final String ADD_NEW_CONTACT_VIEW_KEY = "ADD_NEW_CONTACT_VIEW_ROOT";

    private static final String API_PROPERTIES_PATH = "/properties/api.properties";
    private static final String VIEW_PROPERTIES_PATH = "/properties/view.properties";

    private static final Properties API_PROPERTIES = loadApiProperties();
    private static final Properties VIEW_PROPERTIES = loadViewProperties();

    private static Properties loadApiProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Properties.class.getResourceAsStream(API_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static Properties loadViewProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Properties.class.getResourceAsStream(VIEW_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getApiProperty(String key) {
        return API_PROPERTIES.getProperty(key);
    }

    public static String getViewProperty(String key) {
        return VIEW_PROPERTIES.getProperty(key);
    }

}
