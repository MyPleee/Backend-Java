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
		
		ServerInitializer serverInitializer = new ServerInitializer();
		serverInitializer.init();
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent var1) {

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
