package ple.exception.type;

import javax.servlet.http.HttpServletResponse;

public enum FinalizeErrorType implements PleErrorType{
	ConnectionCloseError(
			"ConnectionCloseError", 
			2000, 
			"서버 종료 시 dbpool에 있는 커넥션 해제 중 예외 발생", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			);
	
	
	private String errorName;
	private int errorCode;
	private String errorMessage;
	private int errorStatus;
	
	private FinalizeErrorType(String errorName, int errorCode, String errorMessage, int errorStatus) {
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
