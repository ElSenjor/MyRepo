package turnierGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import turnierLogik.*;

public class Uebersicht extends MeinFenster implements ActionListener {
	TextField txtErg[][];
	Button btnErg[];
	Label lblAkt[][];
	Label lblNae[][];
	Panel aktuelleSpiele;
	Panel naechsteSpiele;
	Turnier t;
	JLabel jlblSpielplan;
	JLabel jlblBlitztabelle;

	public Uebersicht(Turnier t) {
		super("Beerpong");
		this.t = t;

		ListeVonSpielen arrAkt = t.getAktuelleSpiele();
		ListeVonSpielen arrNae = t.getNaechsteSpiele();

		// TextField erstellen
		txtErg = new TextField[arrAkt.length][2];
		for (int i = 0; i < arrAkt.length; i++) {
			for (int k = 0; k < 2; k++) {
				txtErg[i][k] = new TextField();
			}
		}

		// Buttons erstellen
		btnErg = new Button[arrAkt.length];
		for (int i = 0; i < arrAkt.length; i++) {
			btnErg[i] = new Button("Set");
			btnErg[i].setVisible(true);
			btnErg[i].addActionListener(this);
		}

		// LblAkt erstellen
		lblAkt = new Label[arrAkt.length][2];
		for (int i = 0; i < arrAkt.length; i++) {
			lblAkt[i][0] = new Label();
			lblAkt[i][1] = new Label();
		}

		// LblNae erstellen
		lblNae = new Label[arrAkt.length][2];
		for (int i = 0; i < arrAkt.length; i++) {
			lblNae[i][0] = new Label();
			lblNae[i][1] = new Label();
		}

		// Panels erstellen
		aktuelleSpiele = new Panel(new GridBagLayout());
		naechsteSpiele = new Panel(new GridBagLayout());

		// Panel aktuelle Spiele füllen
		for (int i = 0; i < arrAkt.length; i++) {
			put(aktuelleSpiele, lblAkt[i][0], 0, i, 5);
			put(aktuelleSpiele, txtErg[i][0], 5, i, 1);
			put(aktuelleSpiele, new Label(":"), 6, i, 1);
			put(aktuelleSpiele, txtErg[i][1], 7, i, 1);
			put(aktuelleSpiele, lblAkt[i][1], 8, i, 5);
			put(aktuelleSpiele, btnErg[i], 13, i, 3);

		}

		// Panel nächste Spiele füllen
		for (int i = 0; i < arrNae.length; i++) {
			put(naechsteSpiele, lblNae[i][0], 0, i, 5);
			put(naechsteSpiele, new Label("-"), 5, i, 3);
			put(naechsteSpiele, lblNae[i][1], 8, i, 5);

		}
		jlblSpielplan = new JLabel();
		jlblBlitztabelle = new JLabel();
		put(this, new Label(""), 0, 0, 1);
		put(this, naechsteSpiele, 1, 1, 1);
		put(this, aktuelleSpiele, 1, 0, 1);
		put(this, new Label(""), 2, 0, 1);
		put(this, jlblSpielplan, 3, 0, 3);
		put(this, new Label("  "), 6, 0, 1);
		put(this, jlblBlitztabelle, 7, 0, 3);
		pack();
		setVisible(true);
		updateGUI();
	}

	public void updateGUI() {
		ListeVonSpielen arrAkt = t.getAktuelleSpiele();
		ListeVonSpielen arrNae = t.getNaechsteSpiele();

		for (int i = 0; i < arrAkt.length; i++) {
			lblAkt[i][0].setText(arrAkt.getStringTeamA(i));
			lblAkt[i][1].setText(arrAkt.getStringTeamB(i));
		}

		for (int i = 0; i < arrNae.length; i++) {
			lblNae[i][0].setText(arrNae.getStringTeamA(i));
			lblNae[i][1].setText(arrNae.getStringTeamB(i));
		}
		jlblSpielplan.setText(t.getSpielplanString());
		jlblBlitztabelle.setText(t.getBlitztabelleString());
		pack();
		revalidate();
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		// Button herausfinden
		int spielNr = -1;
		for (int i = 0; i < btnErg.length; i++) {
			if (btnErg[i] == e.getSource()) {
				spielNr = i;
			}
		}
		if (spielNr == -1) {
			System.out.println("Button not found");
			return;
		}

		try {
			int a = Integer.parseInt(txtErg[spielNr][0].getText());
			int b = Integer.parseInt(txtErg[spielNr][1].getText());

			if (a >= 0 && a <= 10 && b >= 0 && b <= 10) {
				if (a != b) {
					t.setErgebnis(spielNr, a, b);
					updateGUI();
				}
			}
			t.printBlitztabelle();

		} catch (Exception ex) {
			// TODO Fehlermeldungen für String Int Conversion behandeln
		}
		txtErg[spielNr][0].setText("");
		txtErg[spielNr][1].setText("");
	}


}
