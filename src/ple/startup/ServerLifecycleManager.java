package ple.startup;

import ple.dbpool.DbPoolManager;
import ple.exception.exceptions.FinalizeException;
import ple.exception.exceptions.InitialException;

public class ServerLifecycleManager {

	private static ServerLifecycleManager instance = new ServerLifecycleManager();
	
	public static ServerLifecycleManager getInstance() {
		return instance;
	}
	
	public void initialize() {
		System.out.println("===>[ServerLifecycleManager, initialize]");

		System.out.println("[Step 1] Initialze dbPool");
		try {
			DbPoolManager dbPoolManager = DbPoolManager.getInstance();
			dbPoolManager.initializePool();	
		} catch (InitialException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		System.out.println("<===[ServerLifecycleManager, initialize]");
		
	}
	
	public void finalize() {
		System.out.println("===>[ServerLifecycleManager, finalize]");

		try {
			DbPoolManager dbPoolManager = DbPoolManager.getInstance();
			dbPoolManager.closePool();	
		} catch (FinalizeException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		System.out.println("<===[ServerLifecycleManager, finalize]");
	}
}
