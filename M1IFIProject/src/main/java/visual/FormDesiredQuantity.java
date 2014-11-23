package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormDesiredQuantity extends JPanel {
	private JTextField[] fields;

	// Create a form with the specified labels, tooltips, and sizes.
	public FormDesiredQuantity(String[] labels, char[] mnemonics, int[] widths,
			String[] tips) {
		super(new BorderLayout());
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
		add(labelPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
		fields = new JTextField[labels.length];

		for (int i = 0; i < labels.length; i += 1) {
			fields[i] = new JTextField();
			if (i < tips.length)
				fields[i].setToolTipText(tips[i]);
			if (i < widths.length)
				fields[i].setColumns(widths[i]);

			JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
			lab.setLabelFor(fields[i]);
			if (i < mnemonics.length)
				lab.setDisplayedMnemonic(mnemonics[i]);

			labelPanel.add(lab);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p.add(fields[i]);
			fieldPanel.add(p);
		}
	}

	public String getText(int i) {
		if(fields[i].getText().isEmpty()){
			return "1";
		}
		return (fields[i].getText());
	}
}