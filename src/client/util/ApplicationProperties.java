package client.util;

import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {

    INSTANCE;

    private final Properties properties;
    private String filePath = "client/util/application.properties";

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDefaultFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        if (!filePath.isEmpty())
            this.filePath = filePath;
    }

    public String getAppName() {
        return properties.getProperty("app.name");
    }

    public String getDbUser() {
        return properties.getProperty("db.user");
    }

    public String getDbPassword() {
        return properties.getProperty("db.password");
    }

    public String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public String getDbDriver() {return properties.getProperty("db.driver");

    }

}
