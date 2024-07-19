package ple.controllers.usercontrollers.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import ple.controllers.usercontrollers.dto.UserDTO;
import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptiontypes.UserErrorType;

public class JSONParsing {

	public UserDTO getUserDtoFromJson(HttpServletRequest req) throws PleException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    UserDTO dto;
	    try {
	    	dto = objectMapper.readValue(req.getReader(), UserDTO.class);
	    } catch (IOException e) {
	        throw new PleException(e, UserErrorType.JSONParsingError);
	    }
	    
	    return dto;
	}
	
}
