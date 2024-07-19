package ple.controllers.usercontrollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.controllers.controllerinterfaces.CommonController;
import ple.controllers.usercontrollers.dao.UserDAO;
import ple.controllers.usercontrollers.dto.UserDTO;
import ple.controllers.usercontrollers.util.JSONParsing;
import ple.controllers.usercontrollers.util.PasswordHasher;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptiontypes.UserErrorType;

public class SignUpController implements CommonController{

	//TODO
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException{

		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException{
		// 1. id랑 pwd 등을 받아서 db에 저장
		System.out.println("==> SignInController, doPost");
		
		JSONParsing jsonParsing = new JSONParsing();
		UserDTO inputUserDto = jsonParsing.getUserDtoFromJson(req);
        
		try {

	        PasswordHasher passwordHasher = new PasswordHasher();
	        String hashedUserPassword = passwordHasher.hash(inputUserDto.getPassword());
	        inputUserDto.setPassword(hashedUserPassword);
	        
	        UserDAO userDao = new UserDAO();
	        userDao.insertUser(inputUserDto);
	        
		} catch (Throwable t) {
			t.printStackTrace();
			throw new PleException(UserErrorType.AdminLoginError);
		}
		
		System.out.println("<== SignInController, doPost");
	}

}
