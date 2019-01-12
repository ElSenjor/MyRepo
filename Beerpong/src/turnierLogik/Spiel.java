package turnierLogik;

class Spiel {
	private Team a;
	private Team b;
	private Ergebnis erg;

	Spiel(Team a, Team b) {
		this.a = a;
		this.b = b;
	}

	void setErgebnis(Ergebnis erg) {
		this.erg = erg;
	}

	Ergebnis getErgebnis() {
		if (erg != null) {
			return erg;
		} else {
			return null;
		}
	}
	
	String getString() {
		if (gespielt()) {
			return a.getName() +" "+this.erg.getScoreA()+ ":" +this.erg.getScoreB()+" "+ b.getName();
		} else {
			return a.getName() + " - " + b.getName();
		}
	}
	
	String getStringTeamA() {
		return a.getName();
	}
	
	String getStringTeamB() {
		return b.getName();
	}
	
	Team getTeamA() {
		return a;
	}
	
	Team getTeamB() {
		return b;
	}

	boolean gespielt() {
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
