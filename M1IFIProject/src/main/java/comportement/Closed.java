package comportement;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Dimension;

import creatures.AbstractCreature;

public class Closed implements IComportement{

	private AbstractCreature creature;
	private static Closed instance = new Closed();
	
	private Closed(){}
	
	public void behaviour() {
		
		Dimension s = creature.getEnvironment().getSize();
		double newX = creature.getPosition().getX() + creature.getSpeed()* cos(creature.getDirection());
		// the reason there is a minus instead of a plus is that in our plane
		// Y coordinates rises downwards
		double newY = creature.getPosition().getY() - creature.getSpeed()* sin(creature.getDirection());

		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;
		// newX and newY were just put on the border of the env. It's not a bug
		// as long as the tests passed. Now, the mirroring position is computed.
		if (newX < -hw) {
			newX = - 2*hw - newX;
			// ERROR #2 direction is badly managed 
			setDirectionBounceX();	
		} else if (newX > hw) {
			newX = 2*hw - newX;
			// ERROR #2 direction is badly managed 
			setDirectionBounceX();
		} // else // ERROR #1 (NO ELSE, we need to check X and Y independently)
		
		if (newY < -hh) {
			newY = - 2*hh - newY;
			// ERROR #2 direction is badly managed 
			setDirectionBounceY();
		} else if (newY > hh) {
			// ERROR #3 (cut and paste led to "hw" instead of "hh")
			newY = 2*hh - newY;
			// ERROR #2 direction is badly managed 
			setDirectionBounceY();
		}
		creature.setPosition(newX, newY);
	}

	public static Closed getInstance(){return instance;}
	
	public void setCreature(AbstractCreature creature){this.creature = creature;}
	
	public String getName() {
		return getClass().getName();
	}

	private void setDirectionBounceX() {
		if (creature.getDirection()>= PI)
			creature.setDirection(3*PI - creature.getDirection());
		else
			creature.setDirection(PI - creature.getDirection());
	}

	private void setDirectionBounceY() {
		creature.setDirection(PI * 2 - creature.getDirection());
	}
}
