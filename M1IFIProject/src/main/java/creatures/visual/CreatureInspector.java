package creatures.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import creatures.ICreature;

@SuppressWarnings("serial")
public class CreatureInspector extends JFrame {

	private JTextArea textArea;

	public CreatureInspector() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 600));

		textArea = new JTextArea();
		add(textArea, BorderLayout.CENTER);
		pack();
	}

	public void setCreature(ICreature creature) {
		if (creature != null) {
			textArea.setText(creature.toString());
		} else {
			textArea.setText(null);
		}
	}

}
