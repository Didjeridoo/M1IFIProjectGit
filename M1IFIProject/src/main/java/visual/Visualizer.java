package visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Visualizer extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// save transformation
		AffineTransform pT = g2.getTransform();
		g2.transform(getTransform());
//		g2.translate(getWidth() / 2, getHeight() / 2);

		paint(g2);

		// restore transformation
		g2.setTransform(pT);
	}

	protected void paint(Graphics2D g2) {
		// draw all creatures
		for (IDrawable d : getDrawables()) {
			// save transformation for each drawable
			AffineTransform cT = g2.getTransform();

			d.paint(g2);

			// restore transformation
			g2.setTransform(cT);
		}
	}

	protected AffineTransform getTransform() {
		return AffineTransform.getTranslateInstance(getWidth() / 2.0, getHeight() / 2.0);
	}
	
	protected abstract Iterable<? extends IDrawable> getDrawables();

}
