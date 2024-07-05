package heesom.scheduler;

public abstract class AbstractListener {
	
	public static final String name = "";
	
	public abstract void onStart();
	
	public abstract void onEnd();
	
	public String getName() {
		return this.name;
	}
}
