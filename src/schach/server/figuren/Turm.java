package schach.server.figuren;


public class Turm extends Figur {

	public Turm(boolean weiß, int x) {
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
		return farbe?"T":"t";
	}
	
	public boolean bewegungErlaubt(int x, int y) {
		if (x - posx != 0 && y - posy == 0) {
			if (posx > x) {
				for (int i = 0; i < posx - x - 1; i++) {
					if (brett.figuren[posx - i][y] != null) {
						return false;
					}
				}
				return true;
			}
			for (int i = 0; i < x - posx - 1; i++) {
				if (brett.figuren[posx + i][y] != null) {
					return false;
				}
			}
			return true;
		}
		if (x - posx == 0 && y - posy != 0) {
			if (posy > y) {
				for (int i = 0; i < posy - y - 1; i++) {
					if (brett.figuren[x][posy - i] != null) {
						return false;
					}
				}
				return true;
			}
			for (int i = 0; i < y - posy - 1; i++) {
				if (brett.figuren[x][posy + i] != null) {
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
					break;
				case 1:
					x--;
					break;
				case 2:
					y++;
					break;
				case 3:
					y--;
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
