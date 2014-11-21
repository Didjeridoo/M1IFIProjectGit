package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import plug.test.TestPlugin;

@SuppressWarnings("serial")
public class TestResultsDisplay extends JFrame {

	private JTextArea textArea;
	private Dimension screenSize;
	private Point middle;
	private Point newLocation;
	private TestPlugin testPlugin;

	public TestResultsDisplay() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 600));
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.middle = new Point(screenSize.width / 2, screenSize.height / 2);
		this.newLocation = new Point(middle.x - (getWidth() / 2), 0);
		this.setLocation(newLocation);
		this.textArea = new JTextArea();
		//this.textArea.append("Testons c'est good\n");
		testPlugin = TestPlugin.getInstance();
		String res = testPlugin.getResultTest();
		System.out.println("res : " + res);
		this.textArea.append(res);
		this.add(textArea, BorderLayout.CENTER);
		this.pack();
	}
	
	/*public void setCreature(AbstractCreature creature) {
		if (creature != null) {
			textArea.append(creature.toString());
		} else {
			textArea.append(null);
		}
	}*/
}
