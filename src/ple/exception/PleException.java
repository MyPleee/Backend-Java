package ple.exception;

import ple.type.ErrorType;

public class PleException extends Exception{

	private static final long serialVersionUID = -7123725858716889012L;

	protected ErrorType errorType;
	
	public PleException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public PleException(Throwable cause) {
		super(cause);
	}
	
	public PleException(ErrorType errorType, String msg) {
		super(msg);
		this.errorType = errorType;
	}
	
	public PleException(ErrorType errorType, Throwable cause) {
		super(cause);
		this.errorType = errorType;
	}
	
    public ErrorType getErrorType() {
        return errorType;
    }

}
