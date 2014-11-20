package visual;

import java.awt.Color;
import java.awt.Graphics2D;

public interface IDrawable {

	public Color getColor();

	public int getSize();

	/**
	 * Draws creature to a given canvas.
	 * 
	 * @param g
	 *            canvas where to draw the creature.
	 */
	public void paint(Graphics2D g2);

}