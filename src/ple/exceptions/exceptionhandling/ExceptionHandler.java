package ple.exceptions.exceptionhandling;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ple.dto.ExceptionDTO;
import ple.exceptions.customexceptions.PleException;

public class ExceptionHandler {
	public HttpServletResponse sendToClient(PleException pleException, HttpServletResponse resp) {
         
		ExceptionDTO exceptionDTO = new ExceptionDTO(pleException);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;
        try {
        	json = mapper.writeValueAsString(exceptionDTO);
        	
        	resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(exceptionDTO.getStatus());
            
            resp.getWriter().write(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); 
        } catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
        
        return resp;
    }
}
