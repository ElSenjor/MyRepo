package turnierLogik;

import java.util.Scanner;

public class Spielplan {
	private Team[] teams;
	private Spiel[] spielplan;
	private int naechstesSpiel = 0;

	public Spielplan(int anzahlTeams) throws TeamAnzahlException {
		int spielPerm[];
		Spiel[] spiele;

		// Setze Permutationen TODO
		if (anzahlTeams == 4) {
			int[] tmp = { 0, 5, 1, 4, 2, 3 };
			spielPerm = tmp;
		} else if (anzahlTeams == 5) {
			int[] tmp = { 0, 7, 3, 6, 2, 5, 1, 8, 4, 9 };
			spielPerm = tmp;
		} else {
			throw new TeamAnzahlException();
		}

		// Name der Teams setzen
		teams = new Team[anzahlTeams];
		Scanner sc = new Scanner(System.in);
		//TODO check for duplicates in team names
		boolean teamsChecked = false;
		while (!teamsChecked) {
			for (int i = 0; i < anzahlTeams; i++) {
				System.out.println("Name Team Nr. " + (i + 1) + ": ");
				teams[i] = new Team(sc.nextLine());
			}
			teamsChecked = true;
		}
		sc.close();

		// Anzahl Spiele errechnen
		int anzahlSpiele = 0;
		for (int i = 1; i < anzahlTeams; i++) {
			anzahlSpiele += i;
		}
		System.out.println(
				"Damit hat das Turnier " + anzahlTeams + " Teams. Also werden " + anzahlSpiele + " Spiele gespielt.\n");

		// Spiele erstellen
		spiele = new Spiel[anzahlSpiele];
		int spielPointer = 0;
		for (int i = 0; i < anzahlTeams; i++) {
			for (int k = i + 1; k < anzahlTeams; k++) {
				spiele[spielPointer] = new Spiel(teams[i], teams[k]);
				spiele[spielPointer].print();
				spielPointer++;
			}
		}

		// Spielplan erstellen
		spielplan = new Spiel[anzahlSpiele];
		for (int i = 0; i < anzahlSpiele; i++) {
			spielplan[i] = spiele[spielPerm[i]];
		}
	}

	public Spiel getNaechstesSpiel() {
		if (naechstesSpiel < spielplan.length) {
			Spiel ret = this.spielplan[naechstesSpiel];
			naechstesSpiel++;
			return ret;
		} else {
			return null;
		}
	}

	public Spiel getSpielNachNr(int nr) {
		if (nr >= 0 && nr < spielplan.length) {
			return spielplan[nr];
		} else {
			return null;
		}
	}

	public String getSpielPlanString() {
		String ret = "<html>";
		for (int i = 0; i < spielplan.length; i++) {
			ret = ret + spielplan[i].getString() + "<br>";
		}
		ret = ret + "</html>";
		return ret;
	}
	
	public Team[] getTeams() {
		return teams;
	}

	public void printTeams() {
		// gibt Namen der Teams aus
		System.out.println("Die Teams für heute:");
		for (int i = 0; i < teams.length; i++) {
			System.out.println("Team " + (i + 1) + ": " + teams[i].getName());
		}
		System.out.println("");
	}

	public void printSpielplan() {
		System.out.println("Der Spielplan für heute:");
		for (int i = 0; i < spielplan.length; i++) {
			spielplan[i].print();
		}
		System.out.println("");
	}
}
