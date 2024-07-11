package ple.exception;

import ple.type.ErrorType;
import ple.type.InitialErrorType;

public class InitialException extends PleException{

	private static final long serialVersionUID = 8701619814869045856L;

	@Deprecated
	public InitialException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Deprecated
	public InitialException(Throwable cause) {
		super(cause);
	}
	
	@Deprecated
	public InitialException(InitialErrorType initialErrorType, String msg) {
		super(initialErrorType, msg);
	}
	
	public InitialException(Throwable cause, InitialErrorType initialErrorType) {
		super(cause, initialErrorType);
	}
	
    @Override
    public InitialErrorType getErrorType() {
        return (InitialErrorType) super.getErrorType();
    }

}
