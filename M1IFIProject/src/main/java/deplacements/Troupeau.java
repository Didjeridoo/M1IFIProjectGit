package deplacements;

import static commons.Utils.filter;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import commons.Utils.Predicate;

import comportements.IComportement;
import creatures.AbstractCreature;
import creatures.BouncingCreature;
import creatures.Creature;
import creatures.CustomCreature;
import creatures.ICreature;
import creatures.SmartCreature;
import creatures.SmartCreature.CreaturesAroundCreature;

public class Troupeau implements IDeplacement{
	
	private AbstractCreature creature;
	private IComportement comportement;
		
		
	/** Minimal distance between this creature and the ones around. */
	private final static double MIN_DIST = 10d;

	/** Minimal speed in pixels per loop. */
	private final static double MIN_SPEED = 3d;
	
	
	public void act(AbstractCreature creature, IComportement comportement) {
		setCreature(creature);
		setComportement(comportement);		// speed - will be used to compute the average speed of the nearby
		// creatures including this instance
		double avgSpeed = creature.getSpeed();
		// direction - will be used to compute the average direction of the
		// nearby creatures including this instance
		double avgDir = creature.getDirection();
		// distance - used to find the closest nearby creature
		double minDist = Double.MAX_VALUE;

		// iterate over all nearby creatures
		Iterable<ICreature> creatures = creaturesAround(creature);
		int count = 0;
		for (ICreature c : creatures) {
			avgSpeed += c.getSpeed();
			avgDir += c.getDirection();
			minDist = Math.min(minDist, c.distanceFromAPoint(creature.getPosition()));
			count++;
		}
		
		// average
		avgSpeed = avgSpeed / (count + 1);
		// min speed check
		if (avgSpeed < MIN_SPEED) {
			avgSpeed = MIN_SPEED;
		}
		// average
		avgDir = avgDir / (count + 1);

		// apply - change this creature state
		creature.changeDirection(avgDir);
		creature.setSpeed(avgSpeed);
		
		// if we are not too close move closer
		if (minDist > MIN_DIST) {
			move();
			// we move always the maximum
			Dimension s = creature.getEnvironment().getSize();
			double hw = s.getWidth() / 2;
			double hh = s.getHeight() / 2;
			double newX = creature.getPosition().getX() + (creature.getSpeed() * Math.cos(avgDir));
			double newY = creature.getPosition().getY() + (- creature.getSpeed() * Math.sin(avgDir));

			
			if((newX < -hw)||(newX > hw)||(newY < -hh)||(newY > hh)){
				comportement.behaviour(creature, newX, newY);
			}else{
				creature.setPosition(new Point2D.Double(newX, newY));
			}
		}
	}

	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}

	public void move() {
		
	}
	public Iterable<ICreature> creaturesAround(
			AbstractCreature AbstractCreature) {
		return filter(creature.getEnvironment().getCreatures(), new CreaturesAroundCreature(creature));
	}
	
	public static class CreaturesAroundCreature implements Predicate<ICreature> {
		private final AbstractCreature observer;

		public CreaturesAroundCreature(AbstractCreature observer) {
			this.observer = observer;
		}

		public boolean apply(ICreature input) {
			if (input == observer) {
				return false;
			}
			double dirAngle = input.directionFormAPoint(observer.getPosition(),
					observer.getDirection());

			return abs(dirAngle) < (observer.getFieldOfView() / 2)
					&& observer.distanceFromAPoint(input.getPosition()) <= observer
							.getLengthOfView();

		}
	}
	
	public void setCreature(AbstractCreature creature){
		this.creature = creature;
	}
	
	public void setComportement(IComportement comportement){
		this.comportement = comportement;
	}

}
