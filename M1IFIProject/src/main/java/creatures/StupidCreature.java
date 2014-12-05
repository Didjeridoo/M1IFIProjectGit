package creatures;

import java.awt.Color;
import java.awt.geom.Point2D;

import comportements.IComportement;

import deplacements.IDeplacement;
import deplacements.Stupid;


/**
 * Sample creature
 */
public class StupidCreature extends AbstractCreature {

	public StupidCreature(IEnvironment environment, IComportement comportement, IDeplacement deplacement, Point2D position,
			double speed, double direction, Color color) {
		super(environment,comportement, new Stupid(), color, position);
		
		this.direction = direction;
		this.speed = speed;
		this.color = color;
	}

	public void act() {
		move.act(this, comport);
	}
}
