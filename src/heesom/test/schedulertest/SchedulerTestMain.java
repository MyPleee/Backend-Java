package heesom.test.schedulertest;

public class SchedulerTestMain {

	public static void main(String[] args) {
		SchedulerTestMain schedulerTest = new SchedulerTestMain();
		
		schedulerTest.testPeriodicScheduler();
	}
	
	private void testPeriodicScheduler() {
		
		MusicBoxWorker musicBoxWorker = new MusicBoxWorker(); 
		String periodicName= "MusicBoxScheduler";
		PeriodicScheduler mb = new PeriodicScheduler(1000L, periodicName, musicBoxWorker);
		
		SchedulerManager sm = SchedulerManager.getInstance();
		
		System.out.println(periodicName + " add");
		sm.add(mb);
		
		try {
			Thread.sleep(5000L);
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(periodicName + " stop");
		sm.stop(periodicName);
		
		try {
			Thread.sleep(5000L);
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println();
		
		System.out.println(periodicName + "start");
		sm.start(periodicName);
		
		try {
			Thread.sleep(5000L);
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(periodicName + " end");
		sm.shutdown(periodicName);
		
		try {
			Thread.sleep(5000L);
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(periodicName + " restart");
		sm.start(periodicName);
		
	}
	
	private static void testCronScheduler() {
		MusicBoxWorker musicBoxWorker = new MusicBoxWorker();
		String cronSchedulerName= "MusicBoxScheduler";
		//CronScheduler mb = new CronScheduler(1000L, cronSchedulerName, musicBoxWorker);
		
		SchedulerManager sm = SchedulerManager.getInstance();
		
		System.out.println(cronSchedulerName);
		//sm.add(mb);
	}
	
	private static void print(String str) {
		System.out.println(str);
	}

}
