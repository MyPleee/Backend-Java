package heesom.util;

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
	
	public void startStopWatch() {
		this.startTime = System.currentTimeMillis();
		this.isStopwatchRunning = true;
	}
	
	public void stopStopWatch() {
		this.stopTime = System.currentTimeMillis();
		this.isStopwatchRunning = false;
	}
	
	public void resetStopWatch() {
		this.startTime = -1L;
		this.stopTime = -1L;
		this.isStopwatchRunning = false;
	}
	
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
	
	public void print() {
		System.out.println("==================================");
		System.out.println("start time - " + this.getStartTime());
		System.out.println("elapse time - " + this.getElapsedTime());
		System.out.println("stop time - " + this.getStopTime());
		System.out.println("==================================");
	}
	
}
