package utils;

import static utils.Constants.*;

public class GradleProperties {
    private static GradleProperties instance;
    private String browser;
    private String site;
    private String email;
    private String password;

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
        browser = System.getProperty(BROWSER);
        site = System.getProperty(SITE);
        email = System.getProperty(EMAIL);
        password = System.getProperty(PASSWORD);
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
