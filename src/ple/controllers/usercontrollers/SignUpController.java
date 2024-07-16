package ple.controllers.usercontrollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.controllers.controllerinterfaces.CommonController;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptionhandling.ExceptionHandler;

public class SignUpController implements CommonController{

	//TODO
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException{

		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException{
		// 1. id랑 pwd등 받아서 db에 저장
	}

}
