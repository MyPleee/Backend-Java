package ple.controllers.usercontrollers.util;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
	
	public static void setValidUserSession(HttpServletRequest req, String userId) {
		
		// 클라이언트가 세션 쿠키를 보내지 않았을 경우 null 반환, 세션 하이재킹 방지
        HttpSession session = req.getSession(false); 

        if (session != null) {
            session.invalidate();
        }
        session = req.getSession(true); // 새로운 세션 생성

        if (session != null) {
            session.setMaxInactiveInterval(18000);
            session.setAttribute("userId", userId);
        } else {
            throw new IllegalStateException("Failed to create a new session.");
        }

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
