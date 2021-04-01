package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utils.Constants.*;

public class GradleProperties {
    private static GradleProperties instance;
    private String browser;
    private String site;
    private String email;
    private String password;
    Properties properties;

    private GradleProperties() {
        initialize();
    }

    public static GradleProperties getInstance() {
        if (instance == null) {
            instance = new GradleProperties();
        }
        return instance;
    }

    private void initialize() {

        properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(GRADLE_PROPERTIES);
            properties.load(inputStream);
            browser = System.getProperty(BROWSER)== null?properties.getProperty(BROWSER):System.getProperty(BROWSER);
            site = System.getProperty(SITE)== null?properties.getProperty(SITE):System.getProperty(SITE);
            email = System.getProperty(EMAIL)== null?properties.getProperty(EMAIL):System.getProperty(EMAIL);
            password = System.getProperty(PASSWORD)== null?properties.getProperty(PASSWORD):System.getProperty(PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getBrowser() {
        return browser;
    }

    public String getSite() {
        return site;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
