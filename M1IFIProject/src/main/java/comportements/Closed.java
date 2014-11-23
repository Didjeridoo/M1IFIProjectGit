package comportements;

import static java.lang.Math.PI;

import java.awt.Dimension;

import creatures.AbstractCreature;

public class Closed implements IComportement{

	
	private Closed(){};
	
	private static Closed instance = new Closed();
	
	public void behaviour(AbstractCreature creature, double x, double y) {
		Dimension s = creature.getEnvironment().getSize();

		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;
		
		if (x < -hw) {
			x = - 2*hw - x; 
			setDirectionBounceX(creature);	
		} else if (x > hw) {
			x = 2*hw - x;
			setDirectionBounceX(creature);
		}
		
		if (y < -hh) {
			y = - 2*hh - y;
			setDirectionBounceY(creature);
		} else if (y > hh) {
			y = 2*hh - y;
			setDirectionBounceY(creature);
		}
		creature.setPosition(x, y);
	}

	public static Closed getInstance(){return instance;}
	
	public String getName() {
		return getClass().getName();
	}

	private void setDirectionBounceX(AbstractCreature creature) {
		if (creature.getDirection()>= PI)
			creature.setDirection(3*PI - creature.getDirection());
		else
			creature.setDirection(PI - creature.getDirection());
	}

	private void setDirectionBounceY(AbstractCreature creature) {
		creature.setDirection(PI * 2 - creature.getDirection());
	}
}
