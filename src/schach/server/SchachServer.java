package schach.server;

import java.util.ArrayList;

import schach.api.List;
import schach.api.Server;

public class SchachServer extends Server {
	
	public SchachServer(int pPort) {
		super(pPort);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		if(pClientIP.startsWith("m")) {
			String[] vonZu = pClientIP.split("#");
			
		}

	}

	@Override
	public void processClosingConnection(String pClientIP, int pClientPort) {
		// TODO Auto-generated method stub

	}

}
