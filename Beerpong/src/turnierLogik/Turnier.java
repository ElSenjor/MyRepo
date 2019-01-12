package turnierLogik;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Turnier {
	private Spielplan spielplan;
	private int anzahlTische;
	private Spiel[] aktuelleSpiele;
	private Spiel[] naechsteSpiele;

	public Turnier(int anzahlTeams, int anzahlTische) {
		try {
			spielplan = new Spielplan(anzahlTeams);
		} catch (TeamAnzahlException e) {
			e.printStackTrace();
			System.exit(0); // TODO handle Exception
		}
		this.anzahlTische = anzahlTische;
		aktuelleSpiele = new Spiel[anzahlTische];
		naechsteSpiele = new Spiel[anzahlTische];
		for (int i = 0; i < anzahlTische; i++) {
			aktuelleSpiele[i] = spielplan.getNaechstesSpiel();
		}
		for (int i = 0; i < anzahlTische; i++) {
			naechsteSpiele[i] = spielplan.getNaechstesSpiel();
		}
	}

	public void setErgebnis(int nrAkt, Ergebnis erg) throws Exception {
		// TODO Fehlerbehandlung falls Mannschaft schon spielt ggf. User fragen wer als
		// n�chstes spielen soll
		// TODO ggf. generell User fragen ob die n�chste Begegung passend ist und User
		// selber ausw�hlen lassen
		Spiel spiel = aktuelleSpiele[nrAkt];
		if (spiel != null) {
			spiel.setErgebnis(erg);
			spiel.getTeamA().addSpiel(spiel);
			spiel.getTeamB().addSpiel(spiel);

			// aktuelleSpiele und naechsteSpiele updaten
			aktuelleSpiele[nrAkt] = naechsteSpiele[0];
			for (int i = 0; i < naechsteSpiele.length - 1; i++) {
				naechsteSpiele[i] = naechsteSpiele[i + 1];
			}
			naechsteSpiele[naechsteSpiele.length - 1] = spielplan.getNaechstesSpiel();
		} else {
			throw new Exception("Ein Ergebnis sollte in aktuelle Spiele gesetzt werden.\nAn der Stelle " + nrAkt
					+ " von aktuelleSpiele l�uft gerade kein Spiel");
		}
	}

	public ListeVonSpielen getAktuelleSpiele() {
		return new ListeVonSpielen(this.aktuelleSpiele);
	}
	
	public ListeVonSpielen getNaechsteSpiele() {
		return new ListeVonSpielen(this.naechsteSpiele);
	}

	public Team[] getBlitztabelle() {
		List<Team> teams = Arrays.asList(spielplan.getTeams());
		Collections.sort(teams);
		Team[] ret = new Team[teams.size()];
		ret = teams.toArray(ret);
		return ret;
	}

	public String getSpielplanString() {
		return spielplan.getSpielPlanString();
	}

	public void printAktuelleSpiele() {
		System.out.println("Diese Spiele laufen gerade:");
		for (int i = 0; i < aktuelleSpiele.length; i++) {
			if (aktuelleSpiele[i] != null) {
				aktuelleSpiele[i].print();
			} else {
				System.out.println("<leer>");

			}
		}
		System.out.println("");
	}

	public void printNaechsteSpiele() {
		System.out.println("Diese Spiele kommen als n�chstes:");
		for (int i = 0; i < naechsteSpiele.length; i++) {
			if (naechsteSpiele[i] != null) {
				naechsteSpiele[i].print();
			} else {
				System.out.println("<leer>");
			}
		}
		System.out.println("");
	}

	public void printTeams() {
		spielplan.printTeams();
	}

	public void printSpielplan() {
		spielplan.printSpielplan();
	}
	
	public String getStringFromBlitz(Team[] blitz) {
		String ret = "<html>";
		for(int i=0; i< blitz.length; i++) {
			ret = ret + blitz[i].getString()+"<br>";
		}
		ret=ret+"</html>";
		return ret;
	}

	public static void main(String[] args) {
		System.out.println("Beerpong Turnier gestartet");
		Turnier t;
		t = new Turnier(4, 2);
		t.printTeams();
		System.out.println(t.getSpielplanString());
//		t.printSpielplan();
//		t.printAktuelleSpiele();
//		t.printNaechsteSpiele();

	}
}
