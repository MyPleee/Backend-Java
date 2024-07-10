package ple.type;

public enum InitialErrorType implements ErrorType{
	InitError("InitError", 1000, "init시 해결 할 수 없는 에러");
	
	private String errorName;
	private int errorCode;
	private String errorMessage;
	
	private InitialErrorType(String errorName, int errorCode, String errorMessage) {
		this.errorName = errorName;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
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
	
	
}
