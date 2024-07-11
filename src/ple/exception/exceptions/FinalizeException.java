package ple.exception.exceptions;

import ple.exception.type.FinalizeErrorType;
import ple.exception.type.InitialErrorType;

public class FinalizeException extends PleException{

	private static final long serialVersionUID = 8701619814869045856L;
	
	public FinalizeException(Throwable cause, InitialErrorType initialErrorType) {
		super(cause, initialErrorType);
	}
	
    @Override
    public FinalizeErrorType getErrorType() {
        return (FinalizeErrorType) super.getErrorType();
    }

}
