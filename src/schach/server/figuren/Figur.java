package schach.server.figuren;

import schach.server.Brett;

public abstract class Figur {
	Brett brett;
	boolean farbe;
	int posx, posy;

	public void bewege(int x, int y) {
		brett.figuren[posx][posy] = null;
		brett.figuren[x][y] = this;
		posx = x;
		posy = y;
	}

	public abstract boolean bewegungErlaubt(int x, int y);
	
	public abstract boolean schach();
}
