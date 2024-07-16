package ple.exceptions.customexceptions;

import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptiontypes.PleErrorType;
import ple.exceptions.exceptiontypes.UserErrorType;

public class UserException extends PleException {
	
	private static final long serialVersionUID = -3061018577475413421L;

	public UserException(UserErrorType userErrorType) {
		super(userErrorType);
	}

}
