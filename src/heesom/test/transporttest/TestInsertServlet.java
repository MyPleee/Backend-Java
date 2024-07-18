package heesom.test.transporttest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ple.util.StopWatch;

import java.io.IOException;

public class TestInsertServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7748812202674651050L;
	
	StopWatch st = StopWatch.getInstance();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		this.st.startStopWatch();
		
		TestDao dao = new TestDao();
		
		dao.insertData(4, "test", "test");
		
		this.st.print();
		this.st.resetStopWatch();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		TestDao dao = new TestDao();
		
		dao.insertData(4, "test", "test");
		
	}
	
}