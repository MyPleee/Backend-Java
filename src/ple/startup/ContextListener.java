package ple.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ple.common.PleConstants;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("");
		System.out.println("************************************************");
		System.out.println("          Starting Server........");
		System.out.println("          " 
							+ PleConstants.PRODUCT_NAME + "-"
							+ PleConstants.PRDOCUT_MAJOR_VERSION + "-" + PleConstants.PRDOCUT_MINOR_VERSION);
		System.out.println("************************************************");
		System.out.println("");
		
		ServerLifecycleManager serverLifecycleManager = new ServerLifecycleManager();
		serverLifecycleManager.initialize();
		
		System.out.print("\n\n");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent var1) {
		System.out.print("\n\n");
		
		ServerLifecycleManager serverLifecycleManager = new ServerLifecycleManager();
		serverLifecycleManager.finalize();
		
		System.out.println("");
		System.out.println("************************************************");
		System.out.println("          Shutting Down Server........");
		System.out.println("          " 
							+ PleConstants.PRODUCT_NAME + "-"
							+ PleConstants.PRDOCUT_MAJOR_VERSION + "-" + PleConstants.PRDOCUT_MINOR_VERSION);
		System.out.println("************************************************");
		System.out.println("");
		
	}

	/*
    private void initLog() {
		try{
			Log4jProperties log4jp = Log4jProperties.getInstance();
			log4jp.loadProperties();
		}catch(Throwable t){
			t.printStackTrace();
		}
	}
	*/

}
