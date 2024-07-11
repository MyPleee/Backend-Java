package ple.exception.type;

import javax.servlet.http.HttpServletResponse;

public enum InitialErrorType implements PleErrorType{
	ClassNotFoundError(
			"ClassNotFoundError", 
			1000, 
			"dbpool 생성 중 드라이버 가져올 수 없음 에러", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			),
	SqlError(
			"SqlError", 
			1001, 
			"dbpool 초기화를 위해 커넥션 생성 중 sql 에러", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			);
	
	
	private String errorName;
	private int errorCode;
	private String errorMessage;
	private int errorStatus;
	
	private InitialErrorType(String errorName, int errorCode, String errorMessage, int errorStatus) {
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
