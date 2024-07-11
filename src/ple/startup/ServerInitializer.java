package ple.startup;

import ple.dbpool.DbManager;
import ple.dbpool.DbPoolManager;
import ple.exception.InitialException;

public class ServerInitializer {

	private static ServerInitializer instance = new ServerInitializer();
	
	public static ServerInitializer getInstance() {
		return instance;
	}
	
	public void init() {
		System.out.println("===>[Initializer, init]");

		System.out.println("[Step 1] Initialze dbPool");
		try {
			DbPoolManager dbPoolManager = DbPoolManager.getInstance();
			dbPoolManager.initializePool();	
		} catch (InitialException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("<===[Initializer, init]");
		
	}
}
