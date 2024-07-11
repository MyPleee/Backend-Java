package ple.exception.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ple.exception.exceptions.PleException;

public class ExceptionHandler {
	public String pleExceptionToJson(PleException pleException) {
         
		ExceptionDTO exceptionDTO = new ExceptionDTO(pleException);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;
        try {
        	json = mapper.writeValueAsString(exceptionDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); 
        }
        
        return json;
    }
}
