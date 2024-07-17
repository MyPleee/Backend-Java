package heesom.test.streams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ple.util.StopWatch;

public class FileInMemoryProcessingTest {
	
	public static void main(String[] args) throws IOException {
		String fileName = "d:\\encodingtest.txt";
		StopWatch watch = StopWatch.getInstance();
		
		
		/*
		 * 1. 파일에서 바이트 하나씩 읽어오기 
		 * 
		 * ==================================
		 * start time - 2024/07/17 13:50:47.383
		 * elapse time - 70
		 * stop time - 2024/07/17 13:50:47.453
		 * ==================================
		 */
		watch.startStopWatch();
		
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader is1 = new InputStreamReader(fis, "utf-8");
		
		int read1;
		while((read1 = is1.read()) != -1) {
			char c = (char) read1;
			System.out.print(c);
		}
		
		watch.print();
		
		fis.close();
		
		/* 
		 * 2. 파일에서 버퍼가 가능한만큼 한 번에 읽어오기
		 * ==================================
		 * start time - 2024/07/17 13:50:47.461
		 * elapse time - 5
		 * stop time - 2024/07/17 13:50:47.466
		 * ==================================
		 */
		watch.startStopWatch();
		
		BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(fileName));
		InputStreamReader is2 = new InputStreamReader(bis2, "utf-8");
		
		int read2;
		while((read2 = is2.read()) != -1) {
			char c = (char) read2;
			System.out.print(c);
		}
		
		watch.print();
		
		bis2.close();
		
		/* 
		 * 3. 파일에서 버퍼가 가능한만큼 한 번에 읽어와서 변환도 버퍼가 가능한 만큼 한번에 하기
		 * ==================================
		 * start time - 2024/07/17 13:50:47.466
		 * elapse time - 1
		 * stop time - 2024/07/17 13:50:47.467
		 * ==================================
		 */
		watch.startStopWatch();
		
		BufferedInputStream bis3 = new BufferedInputStream(new FileInputStream(fileName));
		BufferedReader br3 = new BufferedReader(new InputStreamReader(bis3, "utf-8"));
		
		StringBuilder read3 = new StringBuilder();
		String line;
		while((line = br3.readLine()) != null) {
			read3.append(line).append("\n");
		}
		
		System.out.println(read3.toString());
		watch.print();
		
    }
}
