package turnierLogik;

class Ergebnis {
	private final int a;
	private final int b;
	
	Ergebnis(int a, int b) {//TODO ggf. IllegalArgumentException werfen
		this.a = a;
		this.b = b;
		
	}
	int getScoreA() {
		return a;
	}
	int getScoreB() {
		return b;
	}
}
