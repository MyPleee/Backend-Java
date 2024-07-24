package heesom.test.dbpooltest;

import ple.exceptions.customexceptions.InitialException;
import ple.util.StopWatch;

public class ConnectionTestMain {

	public static void main(String[] args) throws InitialException {
		DbPoolManager dbPoolManager = DbPoolManager.getInstance();
		dbPoolManager.initializePool();
		
		StopWatch stopWatch = StopWatch.getInstance();
		
		/*
		 * 1. db 커넥션 필요할 시 맺을 때 
		 * ==================================
		 * start time - 2024/07/17 13:38:36.319
		 * elapse time - 3400
		 * stop time - 2024/07/17 13:38:39.719
		 * ==================================
		 */
		stopWatch.startStopWatch();
		TestDaoWithDb testDaoWithDb = new TestDaoWithDb();
		for (int i = 0; i < 1000; i++) {
			testDaoWithDb.selectData(1);
		}
		
		stopWatch.print();
		
		
		/*
		 * 2. pool 사용할 경우
		 * ==================================
		 * start time - 2024/07/17 13:38:39.725
		 * elapse time - 194
		 * stop time - 2024/07/17 13:38:39.919
		 * ==================================

		 */
		stopWatch.startStopWatch();
		TestDaoWithDbPool testDaoWithDbPool = new TestDaoWithDbPool();
		for (int i = 0; i < 1000; i++) {
			testDaoWithDbPool.selectData(1);
		}
		stopWatch.stopStopWatch();
		
		stopWatch.print();

	}

}
