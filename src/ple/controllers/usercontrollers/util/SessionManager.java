package ple.controllers.usercontrollers.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
	
	public static void setValidUserSession(HttpServletRequest req, String userId) {
		
		// 클라이언트가 세션 쿠키를 보내지 않았을 경우 null 반환, 세션 하이재킹 방지
        HttpSession session = req.getSession(false); 
        
        if (session != null) {
            session.invalidate();
            session = req.getSession(true); // 새로운 세션 생성
        }
        
        session.setMaxInactiveInterval(18000); // 5시간 (300분, 18000초)
        
        // https 옵션
        // session.getServletContext().getSessionCookieConfig().setSecure(true);
        session.getServletContext().getSessionCookieConfig().setHttpOnly(true);
         

        session.setAttribute("userId", userId);

    }

    /**
     * 세션 무효화
     * @param req
     */
    public static void invalidateSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    
    /**
     * 세션 유효성 체크
     * @param req
     * @param userId
     * @return
     */
    public static boolean isValidSession(HttpServletRequest req, String userId) {
        HttpSession session = req.getSession(false);

        if (session != null && !session.getAttribute("userId").equals(userId)) {
            return true;
        }

        return false;
    }
}
