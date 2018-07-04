package schach.server;

public class Brett {
	Figur[][] figuren;
	SchachClient spielerSchwarz;
	SchachClient spielerWeiﬂ;

	public Brett() {
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
