package ple.controllers.usercontrollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.controllers.controllerinterfaces.CommonController;
import ple.controllers.usercontrollers.dto.UserDTO;
import ple.controllers.usercontrollers.util.JSONParsing;
import ple.controllers.usercontrollers.util.SessionManager;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptionhandling.ExceptionHandler;

public class LogoutController implements CommonController{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException{
		System.out.println("==> LogoutController, doGet");
	    
		SessionManager.invalidateSession(req, resp);
		
		System.out.println("<== LogoutController, doGet");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException{
		//1. 세션에 존재하는지 체크
		
		//2. 세션에 존재한다면 세션 삭제 (세션에 없다면 예외발생)
		
	}

}
