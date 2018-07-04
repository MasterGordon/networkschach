package schach.server.figuren;


public class Bauer extends Figur {

	public Bauer(boolean weiß, int x) {
		super();
		farbe = weiß;
		posx = x;
		if (farbe == true) {
			posy = 1;
		} else {
			posy = 6;
		}
	}
	
	@Override
	public String toString() {
		return farbe?"P":"p";
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
