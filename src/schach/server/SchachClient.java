package schach.server;

import java.util.UUID;

public class SchachClient {
	String ip;
	int port;
	UUID uuid;

	public SchachClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
		uuid = UUID.randomUUID();
	}
}
