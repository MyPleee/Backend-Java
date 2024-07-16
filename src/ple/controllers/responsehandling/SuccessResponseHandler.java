package ple.controllers.responsehandling;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ple.dto.CommonDTO;

public class SuccessResponseHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    public HttpServletResponse sendToClient(CommonDTO commonDto, HttpServletResponse resp) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;
        try {
        	json = mapper.writeValueAsString(commonDto.getOriginalDto());
        	
        	resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            
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