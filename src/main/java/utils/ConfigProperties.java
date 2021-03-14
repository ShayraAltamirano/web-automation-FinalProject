package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utils.Constants.*;

public class ConfigProperties {
    private static ConfigProperties instance;

    private Properties properties;

    private String chromeDriver;
    private int implicitTime;
    private int explicitTime;

    private ConfigProperties() {
        initialize();
    }

    public static ConfigProperties getInstance() {
        if (instance == null) {
            instance = new ConfigProperties();
        }
        return instance;
    }

    private void initialize() {
        properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(MAIN_RESOURCES_PATH + CONFIG_PROPERTIES);
            properties.load(inputStream);
            chromeDriver = properties.getProperty(CHROME_DRIVER);
            implicitTime = Integer.parseInt(properties.getProperty(IMPLICIT_TIME));
            explicitTime = Integer.parseInt(properties.getProperty(EXPLICIT_TIME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public int getImplicitTime() {
        return implicitTime;
    }

    public int getExplicitTime() {
        return explicitTime;
    }
}
