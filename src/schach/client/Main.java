package schach.client;

import schach.api.Client;

public class Main {
	FrameLogin fl;
	Client client;
	public static Main instance;
	
	public static void main(String[] args) {
		instance = new Main();
		instance.fl = new FrameLogin(instance);

		
	}
	
	public void processMessage(String pMessage) {
		String[] message = pMessage.split("#");
		if(message[0]=="i"){
			
		}
	}
	
	public void connect(String ip,String port,String session) {
		client=new Client(ip, Integer.parseInt(port)) {
			
			@Override
			public void processMessage(String pMessage) {
				this.processMessage(pMessage);
				
			}
		};
		client.send("c#"+session);
		instance.fl.dispose();
		FrameWait fw = new FrameWait();
		fw.setVisible(true);
		
	}
	
}
