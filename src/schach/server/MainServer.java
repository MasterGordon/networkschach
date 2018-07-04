package schach.server;

public class MainServer {

	public static void main(String[] args) {
		System.out.println("Creating Server!");
		if (args.length == 0)
			new SchachServer(1010);
		else
			new SchachServer(Integer.parseInt(args[0]));
	}

}
