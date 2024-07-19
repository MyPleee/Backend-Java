package ple.controllers.usercontrollers.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionManager {
	private SessionManager() {}
	/**
	 * 세션 생성 후 쿠키 추가
	 * @param userId
	 * @param req
	 * @param resp
	 */
	public static void setValidUserSession(String userId, boolean isAmdin, HttpServletRequest req, HttpServletResponse resp) {
		
		// 클라이언트가 세션 쿠키를 보내지 않았을 경우 null 반환, 세션 하이재킹 방지
        HttpSession session = req.getSession(false); 

        if (session != null) {
            session.invalidate();
        }
        HttpSession newSession = req.getSession(true); // 새로운 세션 생성

        if (newSession != null) {
        	newSession.setMaxInactiveInterval(18000);
        	newSession.setAttribute("userId", userId);
        	newSession.setAttribute("isAdmin", isAmdin);
        } else {
            throw new IllegalStateException("Failed to create a new session.");
        }
        
        Cookie cookie = new Cookie("ple-session", newSession.getId());
        cookie.setPath("/");
        cookie.setMaxAge(18000); // 5시간 (초 단위)
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);
        
        resp.addCookie(cookie);

    }

    /**
     * 세션 무효화하고 쿠키 삭제
     * @param req
     */
    public static void invalidateSession(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // 클라이언트 쪽 쿠키 삭제
        Cookie cookie = new Cookie("ple-session", null);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정
        cookie.setHttpOnly(true);
        //cookie.setSecure(true); // 필요한 경우 사용

        resp.addCookie(cookie);
    }
    
    /**
     * 세션이 유효하다면 true, 유효하지 않다면 false
     * @param req
     * @param userId
     * @return
     */
    public static boolean isValidSession(String userId, HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("userId").equals(userId)) {
            return true;
        }

        return false;
    }
    
    /**
     * admin의 세션이라면 true user의 session이라면 false
     * @param req
     * @return
     */
    public static boolean isAdminSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("isAdmin").equals("true")) {
            return true;
        }

        return false;
    }
}
