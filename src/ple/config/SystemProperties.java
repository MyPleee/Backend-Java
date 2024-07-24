package ple.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {

    private static final String PROPERTIES_FILE = "system.properties";
    
    private static String adminId;
    private static String adminPassword;
    private static String encryptMethod;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        Properties systemPropFile = new Properties();
        try (InputStream inputStream = SystemProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream != null) {
                systemPropFile.load(inputStream);
            }

            adminId = systemPropFile.getProperty("admin.id");
            adminPassword = systemPropFile.getProperty("admin.password");
            encryptMethod = systemPropFile.getProperty("encrypt.method");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAdminId() {
        return adminId;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static String getEncryptMethod() {
        return encryptMethod;
    }
}
