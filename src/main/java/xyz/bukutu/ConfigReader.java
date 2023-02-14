package xyz.bukutu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        this("~/.z2m-cli.properties");
    }

    public ConfigReader(String configFile) {
        properties = new Properties();

        try {
            if(configFile.startsWith("~")) {
                String expandedFilePath = configFile.replaceFirst("^~", System.getProperty("user.home"));
                InputStream inputStream = new FileInputStream(expandedFilePath);
                properties.load(inputStream);
            } else {
                InputStream inputStream = new FileInputStream(configFile);
                properties.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}