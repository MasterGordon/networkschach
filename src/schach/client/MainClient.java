package schach.client;

import java.awt.Color;

import schach.api.Client;

public class MainClient {
	FrameLogin fl;
	FrameWait fw;
	FrameSpielbrett fg;
	Client client;
	boolean weiß;
	public static MainClient instance;

	public MainClient() {
		fl = new FrameLogin(this);
		fl.setVisible(true);
	}
	
	public static void main(String[] args) {
		instance = new MainClient();
	}

	public void processMessage(String pMessage) {
		System.out.println(pMessage);
		String[] message = pMessage.split("#");
		// SPIELBRETT INIT
		if (message[0].equals("i")) {
			if (message[3].equals("0"))
				weiß = false;
			else
				weiß = true;
			//fw.dispose();
			fg = new FrameSpielbrett(this);
			fg.setVisible(true);
			fg.update(pMessage);
			// SPIELBRETT UPDATE
		} else if (message[0].equals("b")) {
			fg.update(pMessage);
			// FEHLER NACHRICHT
		} else if (message[0].equals("e")) {
			fg.setBackground(Color.RED);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fg.setBackground(null);
			// ERGEBNISS NACHRICHT
		} else if (message[0].equals("r")) {
			if (message[1].equals("1")) {
				System.out.println("Du hast gewonnen!");
			} else {
				System.out.println("Du hast verloren!");
			}
		}
	}

	public void connect(String ip, String port, String session) {
		client = new Client(ip, Integer.parseInt(port)) {

			@Override
			public void processMessage(String pMessage) {
				instance.processMessage(pMessage);
			}
		};
			
		client.send("c#" + session);
		instance.fl.dispose();
		fw = new FrameWait();
		fw.setVisible(true);

	}

}
