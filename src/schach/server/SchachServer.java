package schach.server;

import java.util.HashMap;

import schach.api.Server;

public class SchachServer extends Server {

	HashMap<String, SchachClient> connectedClients = new HashMap<String, SchachClient>();
	HashMap<Integer, Brett> sessions = new HashMap<Integer, Brett>();

	public SchachServer(int pPort) {
		super(pPort);
		log("Server Created!");
	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		// TODO Auto-generated method stub
		SchachClient client = new SchachClient(pClientIP, pClientPort, this);
		connectedClients.put(pClientIP + ":" + pClientPort, client);
		log(pClientIP + ":" + pClientPort + " has connected!");
	}

	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		SchachClient client = connectedClients.get(pClientIP + ":" + pClientPort);

		// Client Connection
		if (pMessage.startsWith("c")) {
			int session = Integer.parseInt(pMessage.split("#")[1]);
			if (sessions.containsKey(session)) {
				if (sessions.get(session).spielerSchwarz == null) {
					// FÜRGE CLIENT ZU SESSION + STARTE SESSION
					log("Joining Session [" + session + "] " + client);
					client.session = session;
					Brett brett = sessions.get(session);
					brett.spielerSchwarz = client;
					brett.spielerSchwarz.send("i#1#" + brett.toString() + "#0");
					brett.spielerWeiß.send("i#1#" + brett.toString() + "#1");
				} else {
					client.send("e#2");
				}
			} else {
				// ERSTELLE NEUE SESSION
				log("Creating Session [" + session + "] for " + client);
				Brett brett = new Brett(this, session);
				client.session = session;
				brett.spielerWeiß = client;
				sessions.put(session, brett);
			}
		}

		if (pMessage.startsWith("m")) {
			Brett brett = sessions.get(client.session);
			if (client.equals(brett.getCurrentSpieler())) {
				brett.move(pMessage.split("#")[1], pMessage.split("#")[2]);
			}
		}
	}

	@Override
	public void processClosingConnection(String pClientIP, int pClientPort) {
		SchachClient client = connectedClients.get(pClientIP + ":" + pClientPort);
		Brett brett = sessions.get(client.session);
		try {
			if (brett.spielerSchwarz.equals(client)) {
				brett.spielerSchwarz.send("r#0");
				brett.spielerWeiß.send("r#1");
				closeGame(client.session);
			}else {
				brett.spielerSchwarz.send("r#1");
				brett.spielerWeiß.send("r#0");
				closeGame(client.session);
			}
		} catch (Exception e) {
			client.close();
		}
	}

	private void log(String s) {
		System.out.println(s);
	}

	public void closeGame(int session) {
		Brett brett = sessions.get(session);
		brett.spielerSchwarz.close();
		brett.spielerWeiß.close();
		sessions.remove(session);
	}
}
