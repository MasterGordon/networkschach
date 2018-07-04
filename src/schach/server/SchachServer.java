package schach.server;

import java.util.HashMap;

import schach.api.Server;

public class SchachServer extends Server {

	HashMap<String, SchachClient> connectedClients = new HashMap<String, SchachClient>();
	HashMap<Integer, Brett> sessions = new HashMap<Integer, Brett>();

	public SchachServer(int pPort) {
		super(pPort);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		// TODO Auto-generated method stub
		SchachClient client = new SchachClient(pClientIP, pClientPort, this);
		connectedClients.put(pClientIP+":"+pClientPort, client);
		log(pClientIP+":"+pClientPort + " has connected!");
	}

	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		SchachClient client = connectedClients.get(pClientIP+":"+pClientPort);
		
		//Client Connection
		if(pMessage.startsWith("c")) {
			int session = Integer.parseInt(pMessage.split("#")[1]);
			if(sessions.containsKey(sessions)) {
				//F‹RGE CLIENT ZU SESSION + STARTE SESSION
				Brett brett = sessions.get(session);
				brett.spielerSchwarz = client;
				brett.spielerSchwarz.send("i#1#"+brett.toString());
				brett.spielerWeiﬂ.send("i#1#"+brett.toString());
			}else {
				//ERSTELLE NEUE SESSION
				Brett brett = new Brett();
				brett.spielerWeiﬂ = client;
				sessions.put(session, brett);
			}
		}
		
		if (pMessage.startsWith("m")) {
			String[] vonZu = pMessage.split("#");

		}
	}

	@Override
	public void processClosingConnection(String pClientIP, int pClientPort) {
		// TODO Auto-generated method stub

	}

	private void log(String s) {
		System.out.println(s);
	}
}
