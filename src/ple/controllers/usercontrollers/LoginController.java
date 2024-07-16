package ple.controllers.usercontrollers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.config.SystemProperties;
import ple.controllers.controllerinterfaces.CommonController;
import ple.controllers.responsehandling.SuccessResponseHandler;
import ple.controllers.usercontrollers.dto.CheckUserValidDTO;
import ple.controllers.usercontrollers.util.PasswordHasher;
import ple.controllers.usercontrollers.util.SessionManager;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptiontypes.UserErrorType;

public class LoginController implements CommonController{

	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException{

		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException{
		
		
		// 1. Admin인지 체크 후 세션에 저장
		try {
			SystemProperties systemProperties = SystemProperties.getInstance();
			
			String adminId = systemProperties.getAdminId();
			String adminPassword = systemProperties.getAdminPassword(); //이미 해싱 된 값
			
			String userId = req.getParameter("userid");
	        String userPassword = req.getParameter("userpassword");
	        
	        PasswordHasher passwordHasher = new PasswordHasher();
	        String hashedUserPassword = passwordHasher.hash(userPassword);
	        
	        if (adminId.equals(userId) && adminPassword.equals(hashedUserPassword)) {
	            SessionManager.setValidUserSession(req, userId);
	
	            // 클라이언트에게 응답으로 쿠키를 설정
	            Cookie cookie = new Cookie("ple-session", req.getSession().getId());
	            cookie.setPath("/");
	            cookie.setMaxAge(18000); // 5시간 (초 단위)
	            cookie.setHttpOnly(true);
	            resp.addCookie(cookie);
	            
	            CheckUserValidDTO loginDTO = new CheckUserValidDTO();
	            loginDTO.setId(adminId);
	            loginDTO.setIsAdmin(true);
	            loginDTO.setIsValidUser(true);
	            
	            SuccessResponseHandler responseHandler = new SuccessResponseHandler();
	            responseHandler.sendToClient(loginDTO, resp);
	            
	            return;
	        } 
		} catch (Throwable t) {
			t.printStackTrace();
			throw new PleException(UserErrorType.AdminLoginError);
		}
		
		// 2. db 조회하여 존재하는 유저인지 체크
		
		// 3. 존재한다면 세션에 저장 
		
		// 3. dto 세팅후 핸들러 사용하여 반환
		
	}

}
