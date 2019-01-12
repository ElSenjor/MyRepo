package turnierLogik;

class Team implements Comparable<Team> {
	private final String name;
	private int siege = 0;
	private int niederlagen = 0;
	private int pos = 0;
	private int neg = 0;

	Team(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}
	
	String getString() {
		return name+" "+Integer.toString(siege)+"-"+Integer.toString(niederlagen)+", "+Integer.toString(pos)+"-"+Integer.toString(neg);
	}

	void addSpiel(Spiel spiel) throws IllegalArgumentException {
		Ergebnis erg = spiel.getErgebnis();
		if (spiel.getStringTeamA() == name) {
			pos += erg.getScoreA();
			neg += erg.getScoreB();
			if (erg.getScoreA() > erg.getScoreB()) {
				siege++;
			} else if (erg.getScoreA() < erg.getScoreB()) {
				niederlagen++;
			} else {
				throw new IllegalArgumentException("Es sollte kein Unentschieden möglich");
			}
		} else if (spiel.getStringTeamB() == name) {
			pos += erg.getScoreB();
			neg += erg.getScoreA();
			if (erg.getScoreA() < erg.getScoreB()) {
				siege++;
			} else if (erg.getScoreA() > erg.getScoreB()) {
				niederlagen++;
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new IllegalArgumentException(
					"Das Team" + name + "ist an dem Spiel " + spiel.getString() + " nicht beteiligt");
		}
	}

	public int compareTo(Team x) {
		if (siege < x.siege) {
			return 1;
		} else if (siege > x.siege) {
			return -1;
		} else {
			if (niederlagen > x.niederlagen) {
				return 1;
			} else if (niederlagen < x.niederlagen) {
				return -1;
			} else {
				if (pos - neg < x.pos - x.neg) {
					return 1;
				} else if (pos - neg > x.pos - x.neg) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
}
