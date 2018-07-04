package schach.server;


public class Koenig extends Figur {

	public Koenig(boolean weiﬂ) {
		super();
		farbe = weiﬂ;
		posx = 4;
		if (farbe == true) {
			posy = 0;
		} else {
			posy = 7;
		}
	}

	@Override
	public String toString() {
		return farbe?"K":"k";
	}
	
	public boolean bewegungErlaubt(int x, int y) {

		if (y <= posy + 1 && y >= posy - 1 && x <= posx + 1 && x <= posx - 1 && koenigAbfrage(x, y) == false) {
			return true;
		}

		return false;
	}

	public boolean koenigAbfrage(int x, int y) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (brett.figuren[x + i][y + j] instanceof Koenig) {
					if(!(i==0&&j==0)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean schach() {
		return false;
	}

}
