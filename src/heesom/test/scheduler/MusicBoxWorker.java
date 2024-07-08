package heesom.test.scheduler;

public class MusicBoxWorker extends AbstractWorker{
	private String name = "MusicBoxWorker";
	
	private AbstractListener musicBoxListener = new AbstractListener() {
		
		private String name = "Default MusicBox Listener";
		
		@Override 
		public void onStart() {
			System.out.println(this.name + " onStarted!");
		}
		
		@Override
		public void onEnd() {
			System.out.println(this.name + " onEnded!");
		}
	};
	
	public void doStart() {
		System.out.println(name + " Play~~~~~~");
		
	}
	
	public void cancel() {
		System.out.println(name + " canceled!");
		
	}
	
	public AbstractListener getListener() {
		return this.musicBoxListener;
	}
	
	public void setListener(AbstractListener listener) {
		this.musicBoxListener = listener;
	}
	
	public String getName() {
		return this.name;
	}


	
}
