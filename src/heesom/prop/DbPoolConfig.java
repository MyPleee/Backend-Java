package heesom.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbPoolConfig {
	
	private static final String PROPERTIES_FILE = "dbpool.properties";
	private static DbPoolConfig instance = new DbPoolConfig();
	
	private String dbDriver;
	private String dbUrl;
	private String user;
	private String password;
	
	private DbPoolConfig() {
		this.loadProperties();
	}
    
	public static DbPoolConfig getInstance() {
		if (DbPoolConfig.instance == null) {
			DbPoolConfig.instance = new DbPoolConfig();
		}
		return DbPoolConfig.instance;
	}
	
    private void loadProperties() {
    	Properties dbPropFile = new Properties();
        try (InputStream inputStream = DbPoolConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream != null) {
                dbPropFile.load(inputStream);
            }
            
            this.dbDriver = dbPropFile.getProperty("db.driver");
            this.dbUrl = dbPropFile.getProperty("db.url");
            this.user = dbPropFile.getProperty("db.user");
            this.password = dbPropFile.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getDbDriver() {
    	return this.dbDriver;
    }
    public String getDbUrl() {
    	return this.dbUrl;
    }
    
    public String getUser() {
    	return this.user;
    }
	   
    public String getPassword() {
    	return this.password;
    }
}
