package utils;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Imant on 16.11.16.
 */
public class PropertiesHolderTest {

    @Test
    public void testGetProperty() throws Exception {
        String key = "DATE_TIME_FORMAT";
        String newKey = PropertiesHolder.getProperty(key);
        assertNotNull(newKey);
    }
}