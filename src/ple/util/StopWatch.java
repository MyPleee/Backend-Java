package ple.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StopWatch {
	
	private long startTime = -1L;
	private long stopTime = -1L;
	private boolean  isStopwatchRunning;
	
	private static StopWatch instance = new StopWatch();
	
	private StopWatch() {}
	
	public static StopWatch getInstance() {
		if(StopWatch.instance == null) {
			StopWatch.instance = new StopWatch();
		}
		return StopWatch.instance;
	}
	
	/**
	 * startTime 저장
	 */
	public void startStopWatch() {
		this.startTime = System.currentTimeMillis();
		this.isStopwatchRunning = true;
	}
	
	/**
	 * stopTime 저장
	 */
	public void stopStopWatch() {
		this.stopTime = System.currentTimeMillis();
		this.isStopwatchRunning = false;
	}
	
	/**
	 * startTime과 stopTime 초기화
	 */
	@Deprecated
	public void resetStopWatch() {
		this.startTime = -1L;
		this.stopTime = -1L;
		this.isStopwatchRunning = false;
	}
	
	// ============= 출력 관련 메서드 ============== 
	/**
	 * startTime, elapsedTime, stopTime 출력
	 * 시계가 돌고 있다면 현재 시간을 stopTime에 저장하고 출력
	 */
	public void print() {
		System.out.println("==================================");
		System.out.println("start time - " + this.getStartTime());
		System.out.println("elapse time - " + this.getElapsedTime());
		System.out.println("stop time - " + this.getStopTime());
		System.out.println("==================================");
	}
	
	/**
	 * 시계가 돌고 있다면 stopTime 저장하고 시작 기준으로 현재까지의 시간 반환
	 */
	private long getElapsedTime() {
		if (this.startTime == -1L) {
			return 0L;
		} 
		
		if (this.isStopwatchRunning) { 
			this.stopTime = System.currentTimeMillis();
		}
		
		return this.stopTime - this.startTime;
		
	}
	
	private String getStartTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(this.startTime);
		
		return formatter.format(c.getTime());
	}
	
	private String getStopTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(this.stopTime);
		
		return formatter.format(c.getTime());
	}
	

	
}
