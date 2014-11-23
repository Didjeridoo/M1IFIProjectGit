package creatures;

import java.awt.Color;
import java.awt.geom.Point2D;

import comportements.IComportement;

import deplacements.IDeplacement;

public class CustomCreature extends AbstractCreature{

	/**
	 * Current step number from the last noise application.
	 */
	protected int currCycle;
	
	public CustomCreature(IEnvironment environment, IComportement behaviour, IDeplacement move, Point2D position, double speed,
			double direction, Color color) {
		super(environment, behaviour, move, position);

		this.speed = speed;
		this.direction = direction;
		this.color = color;
		
		currCycle = 0;
	}
	
	public void act() {
		move.act(this, comport);
	}
	
}
