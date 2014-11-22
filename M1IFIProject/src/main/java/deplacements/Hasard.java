package deplacements;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import comportements.IComportement;
import creatures.AbstractCreature;
import creatures.BouncingCreature;
import creatures.CustomCreature;

public class Hasard implements IDeplacement{
	
	private AbstractCreature creature;
	private IComportement comportement;

	private static final double MIN_SPEED = 3;
	private static final double MAX_SPEED = 10;

	/**
	 * Number of cycles after which we apply some random noise.
	 */
	private static final int NUMBER_OF_CYCLES_PER_CHANGE = 30;
	private int currCycle;
	
	public Hasard(){
		currCycle = 0;
	}
	
	public String getName() {
		return getClass().getName();
	}
	

	public void act(AbstractCreature creature, IComportement comportement) {
		setCreature(creature);
		setComportement(comportement);
		applyNoise();
		move();
		
	}
	

	public void move() {
		Dimension s = creature.getEnvironment().getSize();
		
		double newX = creature.getPosition().getX() + creature.getSpeed() * cos(creature.getDirection());
		// the reason there is a minus instead of a plus is that in our plane
		// Y coordinates rises downwards
		double newY = creature.getPosition().getY() - creature.getSpeed() * sin(creature.getDirection());

		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;
		if((newX < -hw)||(newX > hw)||(newY < -hh)||(newY > hh)){
			comportement.behaviour(creature, newX, newY);
		}else{
			creature.setPosition(new Point2D.Double(newX, newY));
		}
	}
	
	public void applyNoise() {
		currCycle++;
		currCycle %= NUMBER_OF_CYCLES_PER_CHANGE;

		// every NUMBER_OF_CYCLES_PER_CHANGE we do the change
		if (currCycle == 0) {
			creature.setSpeed(creature.getSpeed() + ((random() * 2) - 1));

			// maintain the speed within some boundaries
			if (creature.getSpeed() < MIN_SPEED) {
				creature.setSpeed(MIN_SPEED);
			} else if (creature.getSpeed() > MAX_SPEED) {
				creature.setSpeed(MAX_SPEED);
			}

			creature.setDirection(creature.getDirection()
					+ ((random() * PI / 2) - (PI / 4)));
		}
	}
	
	public void setCreature(AbstractCreature creature){
		this.creature = creature;
	}
	
	public void setComportement(IComportement comportement){
		this.comportement = comportement;
	}
	
	
}
