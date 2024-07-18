package heesom.test.schedulertest;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SchedulerManager {
	private static final SchedulerManager instance = new SchedulerManager();
	private Map schedulerPool = new ConcurrentHashMap();
	
	public static SchedulerManager getInstance() {
		return instance;
	}
	
	public void add(Scheduler scheduler) {
		String schedulerName = scheduler.getName();
		
		if (schedulerName == null) {
			this.print("Scheduler name is null");
		} else if (scheduler.getWorker() == null) {
			this.print("Scheduler worker is null");
		} else {
			this.schedulerPool.put(scheduler.getName(), scheduler);
			
			SecurityManager s = System.getSecurityManager();
			
			ThreadGroup group;
			if(s != null) {
				group = s.getThreadGroup();
			} else {
				group = Thread.currentThread().getThreadGroup();
			}
			
			this.print("Current Thread Group - " + group.getName());
			
			new Thread(group, (Runnable) scheduler, scheduler.getName(), 0L).start();
		}
	}
	
	public void start(String schedulerName) {
		SchedulerLifeCycle value = (SchedulerLifeCycle) this.schedulerPool.get(schedulerName);
		
		if (value == null) {
			this.print(schedulerName + " is not found");
		} else {
			value.start();
		}
	}
	
	public void stop(String schedulerName) {
		SchedulerLifeCycle value = (SchedulerLifeCycle) this.schedulerPool.get(schedulerName);
		
		if (value == null) {
			this.print(schedulerName + " is not found");
		} else {
			value.stop();
		}
	}
	
	public void shutdown(String schedulerName) {
		SchedulerLifeCycle value = (SchedulerLifeCycle) this.schedulerPool.get(schedulerName);
		
		if (value == null) {
			this.print(schedulerName + " is not found");
		} else {
			value.shutdown();
		}
	}
	
	public synchronized void shutdownAllScheduler() {
		
		while (!this.isAllShutdowned()) {
			Iterator iterator = this.schedulerPool.keySet().iterator();
			
			while (iterator.hasNext()) {
				String schedulerName = (String) iterator.next();
				this.shutdown(schedulerName);
			}
		}
		
	}
	
	public synchronized boolean isAllShutdowned() {
		Iterator iterator = this.schedulerPool.values().iterator();
		
		Scheduler scheduler;
		
		while (iterator.hasNext()){	
			scheduler = (Scheduler) iterator.next();
			if (scheduler != null && scheduler.getSchedulerStatus() != SchedulerStatus.SHUTDOWNED) {
				return false;
			}
		}
		
		return true;
	}
	
	public void print(String str) {
		System.out.println(str);
	}
}
