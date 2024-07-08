package heesom.test.dbpool;

import ple.util.StopWatch;

public class ConnectionTestMain {

	public static void main(String[] args) {
		DbPoolManager dbPoolManager = DbPoolManager.getInstance();
		dbPoolManager.initializePool();
		
		StopWatch stopWatch = StopWatch.getInstance();
		stopWatch.startStopWatch();
		
		// db 커넥션 필요할 시 맺을 때 elapse time:2619
		TestDaoWithDb testDaoWithDb = new TestDaoWithDb();
		for (int i = 0; i < 1000; i++) {
			testDaoWithDb.selectData(1);
		}
		
		stopWatch.stopStopWatch();
		stopWatch.print();
		
		stopWatch.resetStopWatch();
		
		
		stopWatch.startStopWatch();
		// pool 사용할 경우 elapse time:169
		TestDaoWithDbPool testDaoWithDbPool = new TestDaoWithDbPool();
		for (int i = 0; i < 1000; i++) {
			testDaoWithDbPool.selectData(1);
		}
		stopWatch.stopStopWatch();
		
		stopWatch.print();

	}

}
