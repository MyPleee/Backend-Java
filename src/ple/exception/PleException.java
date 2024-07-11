package ple.exception;

import ple.type.ErrorType;

public class PleException extends Exception{

	private static final long serialVersionUID = -7123725858716889012L;

	protected ErrorType errorType;
	
	@Deprecated
	public PleException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Deprecated
	public PleException(Throwable cause) {
		super(cause);
	}
	
	@Deprecated
	public PleException(ErrorType errorType, String msg) {
		super(msg);
		this.errorType = errorType;
	}
	
	public PleException(Throwable cause, ErrorType errorType) {
		super(errorType.getErrorMessage(), cause);
		this.errorType = errorType;
	}
	
	
    public ErrorType getErrorType() {
        return errorType;
    }

}
