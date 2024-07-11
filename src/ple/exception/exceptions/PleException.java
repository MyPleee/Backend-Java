package ple.exception.exceptions;

import ple.exception.type.PleErrorType;

public class PleException extends Exception{

	private static final long serialVersionUID = -7123725858716889012L;

	protected PleErrorType errorType;
	
	@Deprecated
	public PleException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Deprecated
	public PleException(Throwable cause) {
		super(cause);
	}
	
	@Deprecated
	public PleException(PleErrorType errorType, String msg) {
		super(msg);
		this.errorType = errorType;
	}
	
	public PleException(Throwable cause, PleErrorType errorType) {
		super(errorType.getErrorMessage(), cause);
		this.errorType = errorType;
	}
	
	
    public PleErrorType getErrorType() {
        return errorType;
    }

}
