package ple.controllers.usercontrollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.config.SystemProperties;
import ple.controllers.controllerinterfaces.CommonController;
import ple.controllers.usercontrollers.dao.UserDAO;
import ple.controllers.usercontrollers.dto.CheckUserValidDTO;
import ple.controllers.usercontrollers.dto.UserDTO;
import ple.controllers.usercontrollers.successhandling.UserSuccessResponseHandler;
import ple.controllers.usercontrollers.util.JSONParsing;
import ple.controllers.usercontrollers.util.PasswordHasher;
import ple.controllers.usercontrollers.util.SessionManager;
import ple.db.SelectQueryCondition;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptionhandling.ExceptionHandler;
import ple.exceptions.exceptiontypes.UserErrorType;

public class CheckUserValidController implements CommonController{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException{
		System.out.println("==> CheckUserValidController, doGet");
	    
		JSONParsing jsonParsing = new JSONParsing();
		UserDTO inputUserDto = jsonParsing.getUserDtoFromJson(req);
	    
		String userId = inputUserDto.getId();
        boolean isValid = SessionManager.isValidSession(userId, req);
        boolean isAdmin = SessionManager.isAdminSession(req);
        
        if (isValid) {
        	CheckUserValidDTO checkUserValidDto = new CheckUserValidDTO();
        	checkUserValidDto.setId(userId);
        	
        	if (isValid) {
        		checkUserValidDto.setIsValidUser(true);
        		if (isAdmin) {
        			checkUserValidDto.setIsAdmin(true);
        		}
        	}
        	
        }
		System.out.println("<== CheckUserValidController, doGet");
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException{

	}

}
