package schach.server;

public class Brett {
	boolean werIstDran = true;//true = weiß
	Figur[][] figuren;
	SchachClient spielerSchwarz;
	SchachClient spielerWeiß;
	SchachServer server;
	int session;

	public Brett(SchachServer server, int session) {
		this.session = session;
		this.server = server;
		figuren = new Figur[8][8];
		// for(int i=0;i<8;i++) {
		// figuren[i] = new Figur[8];
		// }
		figuren[0][0] = new Turm(true, 0);
		figuren[0][1] = new Springer(true, 1);
		figuren[0][2] = new Laeufer(true, 2);
		figuren[0][3] = new Dame(true);
		figuren[0][4] = new Koenig(true);
		figuren[0][5] = new Laeufer(true, 5);
		figuren[0][6] = new Springer(true, 6);
		figuren[0][7] = new Turm(true, 7);
		figuren[7][0] = new Turm(false, 0);
		figuren[7][1] = new Springer(false, 1);
		figuren[7][2] = new Laeufer(false, 2);
		figuren[7][3] = new Dame(false);
		figuren[7][4] = new Koenig(false);
		figuren[7][5] = new Laeufer(false, 5);
		figuren[7][6] = new Springer(false, 6);
		figuren[7][7] = new Turm(false, 7);
		for (int i = 0; i < 8; i++) {
			figuren[6][i] = new Bauer(false, i);
		}
		for (int i = 0; i < 8; i++) {
			figuren[1][i] = new Bauer(true, i);
		}
	}

	public void sendUpdate() {
		String packet = "b#"+(werIstDran?"1":"0")+"#"+toString();
		spielerSchwarz.send(packet);
		spielerWeiß.send(packet);
	}
	
	public void move(String from,String to) {
		int fromX = Integer.parseInt(from.split(",")[0]);
		int fromY = Integer.parseInt(from.split(",")[1]);
		int toX = Integer.parseInt(to.split(",")[0]);
		int toY = Integer.parseInt(to.split(",")[1]);
		if(figuren[fromX][fromY].bewegungErlaubt(toX, toY)) {
			if(figuren[toX][toY] instanceof Koenig) {
				//SPIELER HAT GEWONNEN
				if(werIstDran) {
					spielerSchwarz.send("r#0");
					spielerWeiß.send("r#1");
				}else {
					spielerSchwarz.send("r#1");
					spielerWeiß.send("r#0");
				}
			}
			figuren[toX][toY] = figuren[fromX][fromY];
			figuren[fromX][fromY] = null;
			sendUpdate();
		}
	}
	
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (figuren[i][j] == null)
					string += "0";
				else
					string += figuren[i][j].toString();
			}
		return string;
	}

}
