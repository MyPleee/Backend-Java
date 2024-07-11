package ple.exception.type;

public enum FinalizeErrorType implements PleErrorType{
	ConnectionCloseError("ConnectionCloseError", 2000, "서버 종료 시 dbpool에 있는 커넥션 해제 중 예외 발생"),;
	
	
	private String errorName;
	private int errorCode;
	private String errorMessage;
	
	private FinalizeErrorType(String errorName, int errorCode, String errorMessage) {
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
