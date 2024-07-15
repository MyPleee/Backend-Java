package ple.controllers.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.exception.exceptions.PleException;

public interface CommonController {
	void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException;
	
	void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException;
}
