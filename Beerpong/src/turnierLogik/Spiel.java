package turnierLogik;

public class Spiel {
	private Team a;
	private Team b;
	private Ergebnis erg;

	public Spiel(Team a, Team b) {
		this.a = a;
		this.b = b;
	}

	public void setErgebnis(Ergebnis erg) {
		this.erg = erg;
	}

	public Ergebnis getErgebnis() {
		if (erg != null) {
			return erg;
		} else {
			return null;
		}
	}
	
	public String getString() {
		if (gespielt()) {
			return a.getName() +" "+this.erg.getScoreA()+ ":" +this.erg.getScoreB()+" "+ b.getName();
		} else {
			return a.getName() + " - " + b.getName();
		}
	}
	
	public String getStringTeamA() {
		return a.getName();
	}
	
	public String getStringTeamB() {
		return b.getName();
	}
	
	public Team getTeamA() {
		return a;
	}
	
	public Team getTeamB() {
		return b;
	}

	public boolean gespielt() {
		if (erg == null) {
			return false;
		} else {
			return true;
		}
	}

	public void print() {
		System.out.println(getString());
	}
}
