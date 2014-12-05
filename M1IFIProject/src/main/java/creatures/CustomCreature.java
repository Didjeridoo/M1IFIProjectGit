package creatures;

import java.awt.Color;
import java.awt.geom.Point2D;

import comportements.IComportement;

import deplacements.IDeplacement;

public class CustomCreature extends AbstractCreature{
	
	public CustomCreature(IEnvironment environment, IComportement behaviour, IDeplacement move, Point2D position, double speed,
			double direction, Color color) {
		super(environment, behaviour, move, color, position);

		this.speed = speed;
		this.direction = direction;
		this.color = color;
	}
	
	public void act() {
		move.act(this, comport);
	}
	
	
}
