package heesom.test.scheduler;

public abstract class AbstractWorker {
	
	public abstract void doStart();
	
	public abstract void cancel();
	
	// =============================
	
	private AbstractListener listner;
	
	public AbstractListener getListener() {
		return listner;
	}
	
	public void setListener(AbstractListener listener) {
		this.listner = listener;
	}
	
	// ==============================
	
	private String name;
	
	public String getName() {
		return this.name;
	}
}
