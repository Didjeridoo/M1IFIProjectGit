package creatures;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Color;
import java.awt.geom.Point2D;

import comportement.IComportement;

import deplacements.IDeplacement;


/**
 * Sample creature
 */
public class StupidCreature extends AbstractCreature {

	public StupidCreature(IEnvironment environment, IComportement comportement, IDeplacement deplacement, Point2D position,
			double direction, double speed, Color color) {
		super(environment,comportement, deplacement, position);
		
		this.direction = direction;
		this.speed = speed;
		this.color = color;
	}

	public void act() {
		move.act(this, comport);
	}
}
