package schach.server;

public class SchachClient {
	String ip;
	int port;
	int session;
	SchachServer server;

	public SchachClient(String ip, int port,SchachServer server) {
		this.ip = ip;
		this.port = port;
		this.server = server;
	}
	
	public void send(String message) {
		server.send(ip, port, message);
	}
	
	public void close() {
		if(server.connectedClients.containsKey(ip+":"+port)) {
			server.connectedClients.remove(ip+":"+port);
			server.closeConnection(ip, port);
		}
	}
	
	public boolean isConnected() {
		return server.connectedClients.containsKey(ip+":"+port);
	}
	
	@Override
	public String toString() {
		return ip+":"+port;
	}
}
