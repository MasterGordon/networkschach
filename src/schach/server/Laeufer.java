package schach.server;


public class Laeufer extends Figur {

	public Laeufer(boolean weiß, int x) {
		super();
		farbe = weiß;
		posx = x;
		if (farbe == true) {
			posy = 0;
		} else {
			posy = 7;
		}
	}

	@Override
	public String toString() {
		return farbe?"R":"r";
	}
	
	public boolean bewegungErlaubt(int x, int y) {
		if ((x - posx) / (y - posy) == 1) {
			if (posx > x) {
				for (int i = 0; i < posx - x - 1; i++) {
					if (brett.figuren[posx - i][posy - i] != null) {
						return false;
					}
				}
				return true;
			}
			for (int i = 0; i < x - posx - 1; i++) {
				if (brett.figuren[posx + i][posy + i] != null) {
					return false;
				}
			}
			return true;
		}
		if ((x - posx) / (y - posy) == -1) {
			if (posx > x) {
				for (int i = 0; i < posx - x - 1; i++) {
					if (brett.figuren[posx - i][posy + i] != null) {
						return false;
					}
				}
				return true;
			}
			for (int i = 0; i < x - posx - 1; i++) {
				if (brett.figuren[posx + i][posy - i] != null) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	public boolean schach() {
		for (int j = 0; j < 4; j++) {
			int x = posx;
			int y = posy;
			while (brett.figuren[x][y] != null && x < 8 && x >= 0 && y >= 0 && y < 8) {
				switch (j) {
				case 0:
					x++;
					y++;
					break;
				case 1:
					x--;
					y++;
					break;
				case 2:
					y++;
					x--;
					break;
				case 3:
					y--;
					x--;
					break;
				}
			}
			if (brett.figuren[x][y] instanceof Koenig && farbe != brett.figuren[x][y].farbe) {
				return true;
			}
		}
		return false;
	}
}
