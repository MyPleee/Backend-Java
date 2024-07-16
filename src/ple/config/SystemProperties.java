package ple.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {
	
	private static final String PROPERTIES_FILE = "system.properties";
	private static SystemProperties instance = new SystemProperties();
	
	private String adminId;
	private String adminPassword;
	
	private String encryptMethod;
	
	private SystemProperties() {
		this.loadProperties();
	}
    
	public static SystemProperties getInstance() {
		if (SystemProperties.instance == null) {
			SystemProperties.instance = new SystemProperties();
		}
		return SystemProperties.instance;
	}
	
    private void loadProperties() {
    	
    	Properties systemPropFile = new Properties();
    	
        try (InputStream inputStream = SystemProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            
        	if (inputStream != null) {
            	systemPropFile.load(inputStream);
            }
            
            this.adminId = systemPropFile.getProperty("admin.id");
            this.adminPassword = systemPropFile.getProperty("admin.password");
            this.encryptMethod = systemPropFile.getProperty("encrypt.method");
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getAdminId() {
    	return this.adminId;
    }
    
    public String getAdminPassword() {
    	return this.adminPassword;
    }
    
    public String getEncryptMethod() {
    	return this.encryptMethod;
    }
}
