package ple.exception.type;

public enum InitialErrorType implements PleErrorType{
	ClassNotFoundError("ClassNotFoundError", 1000, "dbpool 생성 중 드라이버 가져올 수 없음 에러"),
	SqlError("SqlError", 1001, "dbpool 초기화를 위해 커넥션 생성 중 sql 에러")
	;
	
	
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
