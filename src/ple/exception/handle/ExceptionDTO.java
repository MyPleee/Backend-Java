package ple.exception.handle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ple.exception.exceptions.PleException;
import ple.exception.type.PleErrorType;

public class ExceptionDTO {
	
	private String timestamp;
	private String className;
    private String errorName;
    private int code;
    private String message;
    private int status;

    public ExceptionDTO(PleException e) {
    	PleErrorType pleErrorType = e.getErrorType();
    	
        this.timestamp = this.getFormattedTimestamp(LocalDateTime.now());
        this.className = e.getClass().getSimpleName();
        this.errorName = pleErrorType.getErrorName();
        this.code = pleErrorType.getErrorCode();
        this.message = pleErrorType.getErrorMessage();
        this.status = pleErrorType.getErrorStatus();
    }

    public String getFormattedTimestamp(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss,SSS");
        return now.format(formatter);
    }
    
    public int getStatus() {
    	return this.status;
    }
}
