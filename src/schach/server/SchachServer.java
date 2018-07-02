package schach.server;

import java.util.HashMap;
import java.util.UUID;

import schach.api.Server;

public class SchachServer extends Server {

	HashMap<UUID, SchachClient> connectedClients = new HashMap<UUID, SchachClient>();
	HashMap<String, Brett> sessions = new HashMap<String, Brett>();

	public SchachServer(int pPort) {
		super(pPort);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		// TODO Auto-generated method stub
		SchachClient client = new SchachClient(pClientIP, pClientPort);
		connectedClients.put(client.uuid, client);
		log(client.uuid + " has connected!");
	}

	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		//Client Connection
		if(pClientIP.startsWith("c")) {
		}
		
		if (pClientIP.startsWith("m")) {
			String[] vonZu = pClientIP.split("#");

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
