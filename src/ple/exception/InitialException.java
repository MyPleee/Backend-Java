package ple.exception;

import ple.type.ErrorType;
import ple.type.InitialErrorType;

public class InitialException extends PleException{

	private static final long serialVersionUID = 8701619814869045856L;

	public InitialException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public InitialException(Throwable cause) {
		super(cause);
	}
	
	public InitialException(InitialErrorType initialErrorType, String msg) {
		super(initialErrorType, msg);
	}
	
	public InitialException(InitialErrorType initialErrorType, Throwable cause) {
		super(initialErrorType, cause);
	}
	
    @Override
    public InitialErrorType getErrorType() {
        return (InitialErrorType) super.getErrorType();
    }

}
