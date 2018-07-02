package schach.server;


public class Bauer extends Figur {

	public Bauer(boolean weiﬂ, int x) {
		super();
		farbe = weiﬂ;
		posx = x;
		if (farbe == true) {
			posy = 1;
		} else {
			posy = 6;
		}
	}

	public boolean bewegungErlaubt(int x, int y) {
		if (farbe == true) {
			if (y == posy + 1 && x == 0 && brett.figuren[posx][y] == null
					|| posy == 2 && y == posy + 2 && x == 0 && brett.figuren[posx][y] == null
					|| y == posy + 1 && x == posx + 1 && brett.figuren[x][y] != null
					|| y == posy + 1 && x == posx - 1 && brett.figuren[x][y] != null) {
				return true;
			}
		} else {
			if (y == posy - 1 && x == 0 && brett.figuren[posx][y] == null
					|| posy == 7 && y == posy - 2 && x == 0 && brett.figuren[posx][y] == null
					|| y == posy - 1 && x == posx + 1 && brett.figuren[x][y] != null
					|| y == posy - 1 && x == posx - 1 && brett.figuren[x][y] != null) {
				return true;
			}
		}
		return false;
	}

	public boolean schach() {
		if (farbe == true) {
			if ((brett.figuren[posx + 1][posy + 1] instanceof Koenig
					&& farbe != brett.figuren[posx + 1][posy + 1].farbe)
					|| (brett.figuren[posx - 1][posy + 1] instanceof Koenig
							&& farbe != brett.figuren[posx - 1][posy + 1].farbe)) {
				return true;
			}
		}
		if ((brett.figuren[posx + 1][posy - 1] instanceof Koenig && farbe != brett.figuren[posx + 1][posy - 1].farbe)
				|| (brett.figuren[posx - 1][posy - 1] instanceof Koenig
						&& farbe != brett.figuren[posx - 1][posy - 1].farbe)) {
			return true;
		}
		return false;
	}

}
