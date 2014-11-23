package creatures;

import static commons.Utils.filter;
import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.geom.Point2D;

import commons.Utils.Predicate;
import comportements.IComportement;
import deplacements.IDeplacement;


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
	


	/** Minimal distance between this creature and the ones around. */
	private final static double MIN_DIST = 10d;

	/** Minimal speed in pixels per loop. */
	private final static double MIN_SPEED = 3d;

	public SmartCreature(IEnvironment environment,IComportement comportement, IDeplacement move, Point2D position, double direction, double speed,
			Color color) {
		super(environment, comportement, move, position);
		this.direction = direction;
		this.speed = speed;
		this.color = color;
	}

	public void act() {
		move.act(this, comport);
	}

	
}
