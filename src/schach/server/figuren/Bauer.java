package schach.server.figuren;

import schach.server.Brett;

public class Bauer extends Figur {

	public Bauer(boolean weiß, int x,Brett brett) {
		super();
		this.brett = brett;
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
		return farbe ? "P" : "p";
	}

//	public boolean bewegungErlaubt(int x, int y) {
//		int dx = posx-x;
//		int dy = posy-y;
//		Figur target = brett.figuren[x][y];
//		if (farbe == true) {
//			if(dy==1&&dx==0&&target==null)
//				return true;
//			if(posy==1&&dy==2&&dx==0&&target==null)
//				return true;
//			if(dy==1&&dx==1&&target!=null)
//				return true;
//		}else {
//			
//		}
//		return false;
//	}
	
	public boolean bewegungErlaubt(int x, int y) {
		System.out.println(x + " " + y);
		if (farbe == true) {
			if (y == posy + 1 && x == posx && brett.figuren[x][y] == null
					|| y == posy + 2 && x == 0 && brett.figuren[x][y] == null
					|| y == posy + 1 && x == posx + 1 && brett.figuren[x][y] != null
					|| y == posy + 1 && x == posx - 1 && brett.figuren[x][y] != null) {
				return true;
			}
		} else {
			if (y == posy - 1 && x == posx && brett.figuren[posx][y] == null
					|| posy == 6 && y == posy - 2 && x == 0 && brett.figuren[posx][y] == null
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
