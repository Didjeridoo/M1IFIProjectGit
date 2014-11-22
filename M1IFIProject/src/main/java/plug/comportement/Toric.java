package plug.comportement;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import creatures.AbstractCreature;
import creatures.CustomCreature;

public class Toric implements IComportement{

	private static CustomCreature creature;

	private static Toric instance = new Toric();
	
	public void behaviour(CustomCreature creature, double x, double y) {
		this.creature = creature;
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
	
	public void setCreature(CustomCreature creature){this.creature = creature;}
	
	public String getName() {
		return getClass().getName();
	}
}
