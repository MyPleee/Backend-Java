package ple.controllers.usercontrollers.successhandling;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ple.controllers.usercontrollers.dto.CheckUserValidDTO;
import ple.controllers.usercontrollers.dto.UserDTO;

public class UserSuccessResponseHandler {

    public HttpServletResponse sendToClient(UserDTO userDto, HttpServletResponse resp) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;
        try {
        	json = mapper.writeValueAsString(userDto);
        	
        	resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            
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
    
    public HttpServletResponse sendToClient(CheckUserValidDTO checkUserValidDto, HttpServletResponse resp) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;
        try {
        	json = mapper.writeValueAsString(checkUserValidDto);
        	
        	resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            
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
