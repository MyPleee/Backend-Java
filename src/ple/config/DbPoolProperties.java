package ple.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbPoolProperties {

    private static final String PROPERTIES_FILE = "dbpool.properties";
    private static String dbDriver;
    private static String dbUrl;
    private static String dbSchema;
    private static String dbUser;
    private static String dbPassword;

    private static int initPoolSize;
    private static int maxPoolSize;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        Properties dbPropFile = new Properties();
        try (InputStream inputStream = DbPoolProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream != null) {
                dbPropFile.load(inputStream);
            }

            dbDriver = dbPropFile.getProperty("db.driver");
            dbUrl = dbPropFile.getProperty("db.url");
            dbSchema = dbPropFile.getProperty("db.schema");
            dbUser = dbPropFile.getProperty("db.user");
            dbPassword = dbPropFile.getProperty("db.password");

            initPoolSize = Integer.parseInt(dbPropFile.getProperty("db.pool.initpoolsize"));
            maxPoolSize = Integer.parseInt(dbPropFile.getProperty("db.pool.maxpoolsize"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =============== use when connecting to db (dbpool) =================
    public static String getDbDriver() {
        return dbDriver;
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    // =============== use when making dbpool (dbpool) =================
    public static int getInitPoolSize() {
        return initPoolSize;
    }

    public static int getMaxPoolSize() {
        return maxPoolSize;
    }
    
 // =============== use when making dao =================
    public static String getDbSchema() {
        return dbSchema;
    }
}
