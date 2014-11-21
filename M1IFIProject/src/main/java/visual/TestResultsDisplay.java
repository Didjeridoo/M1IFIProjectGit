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
	private String text;

	public TestResultsDisplay() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 600));
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.middle = new Point(screenSize.width / 2, screenSize.height / 2);
		this.newLocation = new Point(middle.x - (getWidth() / 2), 0);
		this.setLocation(newLocation);
		this.textArea = new JTextArea();
		this.testPlugin = TestPlugin.getInstance();
		this.text = this.testPlugin.getResultTest();
		this.textArea.append(text);
		this.add(textArea, BorderLayout.CENTER);
		this.pack();
	}
}
