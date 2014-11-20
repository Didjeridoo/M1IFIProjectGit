package creatures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatureBuilder {

	/**
	 * Maximum speed in pixels / cycle
	 */
	public static final float MAX_SPEED = 10f;
	
	public List<ICreature> createCreatures(IEnvironment env, int number) {
		List<ICreature> list = new ArrayList<ICreature>();
		Random rand = new Random();

		Dimension s = env.getSize();
		// view the color space as a cube and then iterate over it using a small
		// steps
		float creaturesCountCubeRoot = (float) Math.pow(number, 1.0 / 3.0);
		float colorPhase = (float) (1.0 / creaturesCountCubeRoot);
		float r = 0.0f;
		float g = 0.0f;
		float b = 0.0f;

		for (int i = 0; i < number; i++) {
			// X coordinate
			double x = (rand.nextDouble() * s.getWidth()) - s.getWidth() / 2;

			// Y coordinate
			double y = (rand.nextDouble() * s.getHeight()) - s.getHeight() / 2;

			// direction
			double direction = (rand.nextDouble() * 2 * Math.PI);

			// speed
			int speed = (int) (rand.nextDouble() * MAX_SPEED);
			
			// color
			r += colorPhase;
			if (r > 1.0) {
				r -= 1.0f;
				g += colorPhase;
				if (g > 1.0) {
					g -= 1.0f;
					b += colorPhase;
					if (b > 1.0)
						b -= 1.0f;
				}
			}
			list.add(new BouncingCreature(env, new Point2D.Double(x,y), direction, speed, new Color(
					r, g, b)));
		}
		
		return list;
	}
	
}
