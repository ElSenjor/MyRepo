package turnierGUI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public abstract class MeinFenster extends Frame {
	private static final long serialVersionUID = 1601323251219439243L;

	public static void put(Container ctr, Component comp, int x, int y, int w) {
		GridBagLayout g = (GridBagLayout) ctr.getLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		ctr.add(comp);
		g.setConstraints(comp, c);
	}

	public MeinFenster(String titel) {
		setTitle(titel);
		setBounds(400, 400, 250, 300);
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}