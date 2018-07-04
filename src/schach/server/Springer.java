package schach.server;


public class Springer extends Figur {

	public Springer(boolean weiﬂ, int x) {
		super();
		farbe = weiﬂ;
		posx = x;
		if (farbe == true) {
			posy = 0;
		} else {
			posy = 7;
		}
	}

	@Override
	public String toString() {
		return farbe?"H":"h";
	}
	
	public boolean bewegungErlaubt(int x, int y) {
		if (((posx - x == 2 || posx - x == -2) && (posy - y == 1 || posy - y == -1))
				|| ((posx - x == 1 || posx - x == -1) && (posy - y == 2 || posy - y == -2))) {
			return true;
		}
		return false;
	}

	public boolean schach() {
		if (brett.figuren[posx + 2][posy + 1] instanceof Koenig && farbe != brett.figuren[posx + 2][posy + 1].farbe
				|| brett.figuren[posx + 2][posy - 1] instanceof Koenig
						&& farbe != brett.figuren[posx + 2][posy - 1].farbe
				|| brett.figuren[posx - 2][posy + 1] instanceof Koenig
						&& farbe != brett.figuren[posx - 2][posy + 1].farbe
				|| brett.figuren[posx - 2][posy - 1] instanceof Koenig
						&& farbe != brett.figuren[posx - 2][posy - 1].farbe
				|| brett.figuren[posx + 1][posy + 2] instanceof Koenig
						&& farbe != brett.figuren[posx + 1][posy + 2].farbe
				|| brett.figuren[posx - 1][posy + 2] instanceof Koenig
						&& farbe != brett.figuren[posx - 1][posy + 2].farbe
				|| brett.figuren[posx + 1][posy - 2] instanceof Koenig
						&& farbe != brett.figuren[posx + 1][posy - 2].farbe
				|| brett.figuren[posx - 1][posy - 2] instanceof Koenig
						&& farbe != brett.figuren[posx - 1][posy - 2].farbe) {
			return true;
		}
		return false;
	}

}
