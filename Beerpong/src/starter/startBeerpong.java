package starter;

import turnierGUI.Uebersicht;
import turnierLogik.Turnier;

public class startBeerpong {
	public static void main(String[] args) {
		Turnier t = new Turnier(4, 2);
		@SuppressWarnings("unused")
		Uebersicht u = new Uebersicht(t);
	}
}
