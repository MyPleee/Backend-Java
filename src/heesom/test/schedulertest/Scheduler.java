package heesom.test.schedulertest;

public interface Scheduler {
	
	void addSchedulerListener(AbstractListener listener);

	void removeSchedulerListener(AbstractListener listener);
	
	String getName();
	
	SchedulerStatus getSchedulerStatus();
	
	AbstractWorker getWorker();
}
