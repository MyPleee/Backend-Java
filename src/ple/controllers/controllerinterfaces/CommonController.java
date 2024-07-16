package ple.controllers.controllerinterfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.exceptions.customexceptions.PleException;

public interface CommonController {
	void doGet(HttpServletRequest req, HttpServletResponse resp) throws PleException;
	
	void doPost(HttpServletRequest req, HttpServletResponse resp) throws PleException;
}
