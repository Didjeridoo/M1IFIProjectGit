package creatures;

import java.awt.Color;
import java.awt.geom.Point2D;

import comportements.IComportement;

import deplacements.IDeplacement;
import deplacements.Troupeau;


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
	
	public SmartCreature(IEnvironment environment,IComportement comportement, IDeplacement move, Point2D position,
			double speed , double direction ,Color color) {
		super(environment, comportement, new Troupeau(), color, position);
		this.direction = direction;
		this.speed = speed;
		this.color = color;
	}

	public void act() {
		move.act(this, comport);
	}

	
}
