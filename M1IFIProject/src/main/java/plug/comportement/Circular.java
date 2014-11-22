package plug.comportement;

import static java.lang.Math.PI;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import creatures.CustomCreature;

public class Circular implements IComportement {

	
	private CustomCreature creature;

	public Circular(CustomCreature creature){
		this.creature = creature;
	}
	public void behaviour(double x, double y) {
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
	
	public String getName() {
		return getClass().getName();
	}
}
