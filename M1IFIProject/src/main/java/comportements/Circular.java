package comportements;

import static java.lang.Math.PI;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import creatures.AbstractCreature;

public class Circular implements IComportement {

	
	private AbstractCreature creature;
	
	private Circular(){};
	
	private static Circular instance = new Circular();

	public void behaviour(AbstractCreature creature, double x, double y) {
		setCreature(creature);
		Dimension s = creature.getEnvironment().getSize();
		double hh = s.getHeight() / 2;
		
		if (x > s.getWidth() / 2) {
			x = -s.getWidth() / 2;
		} else if (x < -s.getWidth() / 2) {
			x = s.getWidth() / 2;
		}
		
		if (y < -hh) {
			y = - 2*hh - y;
			setDirectionBounceY();
		} else if (y > hh) {
			y = 2*hh - y;
			setDirectionBounceY();
		}
		
		creature.setPosition(new Point2D.Double(x, y));
	}

	private void setDirectionBounceY() {
		creature.setDirection(PI * 2 - creature.getDirection());
	}
	
	public static Circular getInstance(){return instance;}
	
	public void setCreature(AbstractCreature creature){this.creature = creature;}
	
	public String getName() {
		return getClass().getName();
	}
}