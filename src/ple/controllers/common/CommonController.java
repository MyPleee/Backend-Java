package ple.controllers.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonController {
	void doGet(HttpServletRequest req, HttpServletResponse resp);
	
	void doPost(HttpServletRequest req, HttpServletResponse resp);
}
