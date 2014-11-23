package comportements;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import creatures.AbstractCreature;

public class Toric implements IComportement{

	private Toric(){};

	private static Toric instance = new Toric();
	
	public void behaviour(AbstractCreature creature, double x, double y) {
		Dimension s = creature.getEnvironment().getSize();
		
		if (x > s.getWidth() / 2) {
			x = -s.getWidth() / 2;
		} else if (x < -s.getWidth() / 2) {
			x = s.getWidth() / 2;
		}

		if (y > s.getHeight() / 2) {
			y = -s.getHeight() / 2;
		} else if (y < -s.getHeight() / 2) {
			y = s.getHeight() / 2;
		}
		creature.setPosition(new Point2D.Double(x, y));
	}

	public static Toric getInstance(){return instance;}
	
	public String getName() {
		return getClass().getName();
	}
}
