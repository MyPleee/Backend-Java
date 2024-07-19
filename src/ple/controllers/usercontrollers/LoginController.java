package ple.controllers.usercontrollers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import ple.config.SystemProperties;
import ple.controllers.controllerinterfaces.CommonController;
import ple.controllers.usercontrollers.dao.UserDAO;
import ple.controllers.usercontrollers.dto.CheckUserValidDTO;
import ple.controllers.usercontrollers.dto.UserDTO;
import ple.controllers.usercontrollers.successhandling.UserSuccessResponseHandler;
import ple.controllers.usercontrollers.util.PasswordHasher;
import ple.controllers.usercontrollers.util.SessionManager;
import ple.db.SelectQueryCondition;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptiontypes.UserErrorType;

public class LoginController implements CommonController{

	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException{        
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException{
		System.out.println("==> LoginController, doPost");
		
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    UserDTO inputUserDto;
	    try {
	    	inputUserDto = objectMapper.readValue(req.getReader(), UserDTO.class);
	    } catch (IOException e) {
	        throw new PleException(e, UserErrorType.JSONParsingError);
	    }
	    
		String userId = inputUserDto.getId();
        String userPassword = inputUserDto.getPassword();
        
		// 1. Admin인지 체크 후 세션에 저장
		try {
			SystemProperties systemProperties = SystemProperties.getInstance();
			
			String adminId = systemProperties.getAdminId();
			String adminPassword = systemProperties.getAdminPassword(); //이미 해싱 된 값

	        PasswordHasher passwordHasher = new PasswordHasher();
	        String hashedUserPassword = passwordHasher.hash(userPassword);
	        
	        if (adminId.equals(userId) && adminPassword.equals(hashedUserPassword)) {
	            SessionManager.setValidUserSession(userId, req, resp);
	            
	            CheckUserValidDTO checkUserValidDto = new CheckUserValidDTO();
	            checkUserValidDto.setId(userId);
	            checkUserValidDto.setIsAdmin(true);
	            checkUserValidDto.setIsValidUser(true);
	            
	            UserSuccessResponseHandler responseHandler = new UserSuccessResponseHandler();
	            responseHandler.sendToClient(checkUserValidDto, resp);
	        
	            return;
	        } 
		} catch (Throwable t) {
			t.printStackTrace();
			throw new PleException(t, UserErrorType.AdminLoginError);
		}
		
		// 2. db 조회하여 존재하는 유저인지 체크하고 존재한다면 세션에 저장
		try {
			UserDAO userDao = new UserDAO();
			SelectQueryCondition selectQuery = new SelectQueryCondition();
			selectQuery.addWhereCondition("id", userId);
			selectQuery.addWhereCondition("AND", "password", userPassword);
		
			UserDTO userDto = userDao.selectUser(selectQuery);
			
			if (userDto != null) {
				
				SessionManager.setValidUserSession(userId, req, resp);
				
				CheckUserValidDTO checkUserValidDto = new CheckUserValidDTO();
	            checkUserValidDto.setId(userId);
	            checkUserValidDto.setIsAdmin(false);
	            checkUserValidDto.setIsValidUser(true);
	            
	            UserSuccessResponseHandler responseHandler = new UserSuccessResponseHandler();
	            responseHandler.sendToClient(checkUserValidDto, resp);
	        
	            return;
			} 
			
			System.out.println("user id - " + userId + "는 없는 유저입니다");
		}  catch (Throwable t) {
			t.printStackTrace();
			throw new PleException(t, UserErrorType.AdminLoginError);
		}

		System.out.println("<== LoginController, doPost");
		
	}

}
