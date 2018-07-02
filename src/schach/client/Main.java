package schach.client;

import java.awt.Color;

import schach.api.Client;

public class Main {
	FrameLogin fl;
	FrameWait fw;
	FrameSpielbrett fg;
	Client client;
	public static Main instance;
	
	public static void main(String[] args) {
		instance = new Main();
		instance.fl = new FrameLogin(instance);

		
	}
	
	public void processMessage(String pMessage) {
		String[] message = pMessage.split("#");
		//SPIELBRETT INIT
		if(message[0]=="i"){
			fw.dispose();
			fg = new FrameSpielbrett();
			fg.update(pMessage);
		//SPIELBRETT UPDATE	
		}else if(message[0]=="b"){
			fg.update(pMessage);
		//FEHLER NACHRICHT	
		}else if(message[0]=="e"){
			fg.setBackground(Color.RED);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fg.setBackground(null);
		//ERGEBNISS NACHRICHT	
		}else if(message[0]=="r"){
			if(message[1]=="1"){
				System.out.println("Du hast gewonnen!");
			}else{
				System.out.println("Du hast verloren!");
			}
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
		fw = new FrameWait();
		fw.setVisible(true);
		
	}
	
}
