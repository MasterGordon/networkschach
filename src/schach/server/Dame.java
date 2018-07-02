package schach.server;


public class Dame extends Figur {

	public Dame(boolean weiß) {
		super();
		farbe = weiß;
		posx = 3;
		if (farbe == true) {
			posy = 0;
		} else {
			posy = 7;
		}
	}

	public boolean bewegungErlaubt(int x, int y) {
		// Laeuferbewegung
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
		// Turmbewegung
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
		// Turm
		for (int j = 0; j < 4; j++) {
			int x = posx;
			int y = posy;
			while (brett.figuren[x][y] != null && x < 8 && x >= 0 && y < 8 && y >= 0) {
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
		// Laeufer
		for (int j = 0; j < 4; j++) {
			int x = posx;
			int y = posy;
			while (brett.figuren[x][y] != null && x >= 0 && x < 8 && y < 8 && y >= 0) {
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
