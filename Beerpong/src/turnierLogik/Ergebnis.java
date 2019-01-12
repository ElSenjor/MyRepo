package turnierLogik;

public class Ergebnis {
	private final int a;
	private final int b;
	
	public Ergebnis(int a, int b) {//TODO ggf. IllegalArgumentException werfen
		this.a = a;
		this.b = b;
		
	}
	public int getScoreA() {
		return a;
	}
	public int getScoreB() {
		return b;
	}
}
