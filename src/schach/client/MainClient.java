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
			try {
				Thread.sleep(100);
				fw.dispose();
			} catch (Exception e) {
			}
			fg = new FrameSpielbrett(this);
			fg.setVisible(true);
			fg.update(pMessage);
			fg.setTitle("Schach - Ingame - "+(weiß?"weiß":"schwarz"));
			// SPIELBRETT UPDATE
		} else if (message[0].equals("b")) {
			fg.update(pMessage);
			// FEHLER NACHRICHT
		} else if (message[0].equals("e")) {
			if(fg!=null) {
				fg.setBackground(Color.RED);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fg.setBackground(null);
			}
			if(message[1].startsWith("2"))
				System.exit(-1);
			// ERGEBNISS NACHRICHT
		} else if (message[0].equals("r")) {
			if (message[1].equals("1")) {
				System.out.println("Du hast gewonnen!");
				if(weiß) {
					fg.lblWhite.setForeground(Color.GREEN);
					fg.lblBlack.setForeground(Color.RED);
				}else {
					fg.lblWhite.setForeground(Color.RED);
					fg.lblBlack.setForeground(Color.GREEN);
				}
			} else {
				System.out.println("Du hast verloren!");
				if(weiß) {
					fg.lblWhite.setForeground(Color.RED);
					fg.lblBlack.setForeground(Color.GREEN);
				}else {
					fg.lblWhite.setForeground(Color.GREEN);
					fg.lblBlack.setForeground(Color.RED);
				}
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
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.send("c#" + session);
		instance.fl.dispose();
		fw = new FrameWait();
		fw.setVisible(true);

	}

}
