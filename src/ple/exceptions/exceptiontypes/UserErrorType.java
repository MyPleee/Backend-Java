package ple.exceptions.exceptiontypes;

import javax.servlet.http.HttpServletResponse;

public enum UserErrorType implements PleErrorType{
	NotSupportedAlgorithmError(
			"NotSupportedAlgorithmError", 
			3000, 
			"비밀번호 해싱 중 sha-256이 아닌 지원되지 않는 알고리즘 적용", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			),
	AdminLoginError(
			"AdminLoginError", 
			3001, 
			"admin 로그인 중 에러", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			),
	UserLoginError(
			"UserLoginError", 
			3002, 
			"user 로그인 중 에러", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			),
	UserSignUpError(
			"UserSignUpError", 
			3003, 
			"user 계정 생성 중 에러", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			),
	JSONParsingError(
			"JSONParsingError", 
			3004, 
			"json을 dto로 변환 중 에러", 
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			),
	;
	
	private String errorName;
	private int errorCode;
	private String errorMessage;
	private int errorStatus;
	
	private UserErrorType(String errorName, int errorCode, String errorMessage, int errorStatus) {
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
