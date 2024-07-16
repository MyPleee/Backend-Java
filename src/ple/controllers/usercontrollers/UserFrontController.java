package ple.controllers.usercontrollers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.controllers.controllerinterfaces.CommonController;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptionhandling.ExceptionHandler;

@WebServlet(name="UserFrontController", urlPatterns="/user/*")
public class UserFrontController extends HttpServlet{
	
	private Map<String, CommonController> controllerMap = new HashMap<>();
	
	public UserFrontController() {
		controllerMap.put("/user/admin", (CommonController) new AdminController());
	}
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        
        CommonController controller = controllerMap.get(requestURI);
        
        if (controller == null) {
        	
        }
        
        try {
        	controller.doGet(req, resp);
        } catch (PleException e) {
        	e.printStackTrace();
        	
        	ExceptionHandler exceptionHandler = new ExceptionHandler();
        	exceptionHandler.sendToClient(e, resp);
        }
        catch (Throwable t) {
        	t.printStackTrace();
        }
        
        

        
    }
	
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        
        CommonController controller = controllerMap.get(requestURI);
        
        if (controller == null) {
        	
        }
        
        try {
        	controller.doPost(req, resp);
        } catch (PleException e) {
        	e.printStackTrace();
        	
        	ExceptionHandler exceptionHandler = new ExceptionHandler();
        	exceptionHandler.sendToClient(e, resp);
        }
        catch (Throwable t) {
        	t.printStackTrace();
        }
        
        
    }
	
}
