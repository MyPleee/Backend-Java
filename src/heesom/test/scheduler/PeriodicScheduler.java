package heesom.test.scheduler;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PeriodicScheduler implements Runnable, Scheduler, SchedulerLifeCycle{
	private String name;
	
	private long period;
	private AbstractWorker worker;
	
	private boolean stopped; 
	private boolean shutdowned;
	private SchedulerStatus  schedulerStatus;
	private Date lastExecuteTime;
	
	private List listeners = new CopyOnWriteArrayList();
	
	public PeriodicScheduler(long period, String name, AbstractWorker worker) {
		this.period = period;
		this.name = name;
		this.worker = worker;
		this.listeners.add(worker.getListener());
	}
	
	// ========================================
	
	private boolean isShutdowned() {
		return this.shutdowned;
	}
	
	private boolean isStopped() {
		return this.stopped;
	}
	
	private void setStatus(SchedulerStatus schedulerStatus) {
		this.schedulerStatus = schedulerStatus;
	}
	
	private void setLastExecuteTime(Date lastExecuteTime) {
		this.lastExecuteTime = lastExecuteTime;
	}
	
	// ========================================
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override 
	public AbstractWorker getWorker() {
		return this.worker;
	}
	
	@Override
	public SchedulerStatus getSchedulerStatus() {
		return this.schedulerStatus;
	}
	
	// ========================================
	
	@Override
	public void run() {
		try {
			
			this.print("Begin " + this.name);
			this.fireSchedulerListenerEvent(0); 
			this.setStatus(SchedulerStatus.IDLE);
			
	
			while(true) {
	
				do {
		
					if (this.isShutdowned()) {
						return;
					}
					
					if (this.isStopped()) { 
						this.setStatus(SchedulerStatus.STOPPED);
					} else if (!this.isStopped()) {  
						break;
					}

				} while(!this.pause(0L));
				
				this.setStatus(SchedulerStatus.IDLE);
				
				if (!this.isStopped() && this.engineRun()) {
					this.setStatus(SchedulerStatus.RUNNING);
					//this.setLastExecuteTime(TimeUtil.getCurrentDate());
					
					try {
						this.worker.doStart();
					} catch (Exception e) {
						this.print("Exception occurs while running Task");
					} finally {
						this.setStatus(SchedulerStatus.IDLE);
					}
				
				}
			}
		} catch(Exception e){
			this.print("Critical error occurs while running Task");
			this.shutdown();
			
		} finally {
			this.setStatus(SchedulerStatus.SHUTDOWNED);
			this.fireSchedulerListenerEvent(1);
			this.print("Shutdowned " + this.name);
		}
	}
	
	@Override
	public synchronized void start() {
		if (this.isShutdowned()) {
			this.print("this " + this.name + " is shutdowned, cannot start, have to start over");
		} else if (!this.isStopped()) {
			this.print("this " + this.name + " is running");
		} else {
			this.print("Start " + this.name);
			this.stopped = false;
			this.notify();
		}
	}
	
	@Override
	public synchronized void shutdown() {
		if (this.isShutdowned()) {
			this.print("this " + this.name + " is already shutdowned");
		} else {
			this.stopped = true;
			this.shutdowned = true;
			this.notify(); 
			this.worker.cancel();
		}
	}
	
	@Override
	public synchronized void stop() {
		if (this.isStopped()) {
			this.print("this " + this.name + " is already stopped");
		} else {
			this.stopped = true;
			this.worker.cancel();
			this.notify(); 
		}
	}
	
	public boolean engineRun() {
		long wait = this.period;
		
		return this.pause(wait);
		
	}
	
	protected final synchronized boolean pause(long period) {
		
		try {
			if (period <= 1L) {
				this.wait();
			} else {
				this.wait(period);
			}
			
			return true;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return false;
		}
	}
	
	
	
	private void fireSchedulerListenerEvent(int i) {
		
		for (int j = 0; j < this.listeners.size(); j++) {
			AbstractListener listener = (AbstractListener) this.listeners.get(j);
				try {
					switch(i) {
						case 0:
							listener.onStart();
							break;
							
						case 1:
							listener.onEnd();
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					this.print("Error occurs while running " + listener.getName());
				}
					
		}
		
	}
	
	@Override
	public void addSchedulerListener(AbstractListener listener) {
		this.listeners.add(listener);
	}
	
	@Override 
	public void removeSchedulerListener(AbstractListener listener) {
		this.listeners.remove(listener);
	}
	
	private static void print(String str) {
		System.out.println("[" + Thread.currentThread().getName() + " - " + Thread.currentThread().getId() + "] " + str);
	}

}
