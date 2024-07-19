package ple.exceptions.exceptiontypes;

import javax.servlet.http.HttpServletResponse;

public enum UtilErrorType implements PleErrorType{
	UUIDBuildingError(
			"UUIDBuildingError", 
			4000, 
			"uuid 규격에 맞지 않는 uuid 생성됨", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			),
	;
	
	private String errorName;
	private int errorCode;
	private String errorMessage;
	private int errorStatus;
	
	private UtilErrorType(String errorName, int errorCode, String errorMessage, int errorStatus) {
		this.errorName = errorName;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorStatus = errorStatus;
	}

	@Override
	public String getErrorName() {
		return this.errorName;
	}

	@Override
	public int getErrorCode() {
		return this.errorCode;
	}

	@Override
	public String getErrorMessage() {
		return this.errorMessage;
	}

	@Override
	public int getErrorStatus() {
		return this.errorStatus;
	}
	
	
}
