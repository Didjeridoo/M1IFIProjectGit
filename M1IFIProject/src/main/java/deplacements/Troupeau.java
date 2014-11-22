package deplacements;

import static commons.Utils.filter;
import static java.lang.Math.abs;
import commons.Utils.Predicate;
import creatures.AbstractCreature;
import creatures.BouncingCreature;
import creatures.Creature;
import creatures.ICreature;
import creatures.SmartCreature;
import creatures.SmartCreature.CreaturesAroundCreature;

public class Troupeau implements IDeplacement{
	AbstractCreature abs;
	/** Minimal distance between this creature and the ones around. */
	private final static double MIN_DIST = 10d;

	/** Minimal speed in pixels per loop. */
	private final static double MIN_SPEED = 3d;
	
	
	public void act(AbstractCreature creature) {
		// speed - will be used to compute the average speed of the nearby
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
			// we move always the maximum
			double incX = creature.getSpeed() * Math.cos(avgDir);
			double incY = - creature.getSpeed() * Math.sin(avgDir);

			// we should not moved closer than a dist - MIN_DIST
			move(incX, incY);
		}
	}

	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}

	public void move(double incX, double incY) {
		// TODO Auto-generated method stub
		
	}
	public Iterable<ICreature> creaturesAround(
			AbstractCreature abstractCreature) {
		return filter(abs.getEnvironment().getCreatures(), new CreaturesAroundCreature(abs));
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

	public double hasardX(AbstractCreature creature) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double hasardY(AbstractCreature creature) {
		// TODO Auto-generated method stub
		return 0;
	}

}
