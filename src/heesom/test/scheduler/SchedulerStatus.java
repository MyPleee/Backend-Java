package heesom.test.scheduler;

public enum SchedulerStatus {
	SHUTDOWNED(0, "SHUTDOWNED"),
	STOPPED(1, "STOPPED"),
	RUNNING(2, "RUNNING"),
	IDLE(3, "IDLE");
	
	private int code;
	private String text;
	
	private SchedulerStatus(int code, String text) {
		this.code = code;
		this.text = text;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String toString() {
		return this.text;
	}

}
