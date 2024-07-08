package heesom.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbPoolConfig {
	
	private static final String PROPERTIES_FILE = "dbpool.properties";
	private static DbPoolConfig instance = new DbPoolConfig();
	
	private String dbDriver;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	
	private int initPoolSize;
	private int maxPoolSize;
	
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
            this.dbUser = dbPropFile.getProperty("db.user");
            this.dbPassword = dbPropFile.getProperty("db.password");
            
            this.initPoolSize = Integer.parseInt(dbPropFile.getProperty("db.pool.initpoolsize"));
            this.maxPoolSize = Integer.parseInt(dbPropFile.getProperty("db.pool.maxpoolsize"));
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
    
    public String getDbUser() {
    	return this.dbUser;
    }
	   
    public String getDbPassword() {
    	return this.dbPassword;
    }
    
    public int getInitPoolSize() {
    	return this.initPoolSize;
    }
    
    public int getMaxPoolSize() {
    	return this.maxPoolSize;
    }
}
