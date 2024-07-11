package ple.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.controllers.common.CommonController;
import ple.exception.exceptions.PleException;
import ple.exception.handle.ExceptionHandler;

public class AdminController implements CommonController{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
        	
        } catch (PleException e) {
        	e.printStackTrace();
        	
        	ExceptionHandler handler = new ExceptionHandler();
        	handler.sendToClient(e, resp);
        }
        catch (Throwable t) {
        	t.printStackTrace();
        }
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
        	
        } catch (PleException e) {
        	e.printStackTrace();
        	
        	ExceptionHandler handler = new ExceptionHandler();
        	handler.sendToClient(e, resp);
        }
        catch (Throwable t) {
        	t.printStackTrace();
        }
		
	}

}
