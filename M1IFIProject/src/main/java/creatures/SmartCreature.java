package creatures;

import static commons.Utils.filter;
import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.geom.Point2D;

import commons.Utils.Predicate;


/**
 * Smart creature that implements following behavior:
 * 
 * Looking at the nearby creatures that are within FOV and a certain distance
 * defined in the environment, it
 * <ul>
 * <li>tries to align its speed with the speed of the creatures around.
 * <li>goes in the same direction as the creatures around.
 * <li>maintains some minimal distance from the creatures around.
 * </ul>
 * 
 * Additionally to that, it tries to maintain some minimum speed so the
 * creatures always moves.
 * 
 */
public class SmartCreature extends AbstractCreature {
	
	public static class CreaturesAroundCreature implements Predicate<ICreature> {
		private final SmartCreature observer;

		public CreaturesAroundCreature(SmartCreature observer) {
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


	/** Minimal distance between this creature and the ones around. */
	private final static double MIN_DIST = 10d;

	/** Minimal speed in pixels per loop. */
	private final static double MIN_SPEED = 3d;

	public SmartCreature(IEnvironment environment, Point2D position, double direction, double speed,
			Color color) {
		super(environment, position);
		this.direction = direction;
		this.speed = speed;
		this.color = color;
	}

	public void act() {
		// speed - will be used to compute the average speed of the nearby
		// creatures including this instance
		double avgSpeed = speed;
		// direction - will be used to compute the average direction of the
		// nearby creatures including this instance
		double avgDir = direction;
		// distance - used to find the closest nearby creature
		double minDist = Double.MAX_VALUE;

		// iterate over all nearby creatures
		Iterable<ICreature> creatures = creaturesAround(this);
		int count = 0;
		for (ICreature c : creatures) {
			avgSpeed += c.getSpeed();
			avgDir += c.getDirection();
			minDist = Math.min(minDist, c.distanceFromAPoint(getPosition()));
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
		this.direction = avgDir;
		this.speed = avgSpeed;
		
		// if we are not too close move closer
		if (minDist > MIN_DIST) {
			// we move always the maximum
			double incX = speed * Math.cos(avgDir);
			double incY = - speed * Math.sin(avgDir);

			// we should not moved closer than a dist - MIN_DIST
			move(incX, incY);
		}
	}

	public Iterable<ICreature> creaturesAround(
			SmartCreature smartCreature) {
		return filter(environment.getCreatures(), new CreaturesAroundCreature(this));
	}
	
}
