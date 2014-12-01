package creatures;

import java.awt.Color;
import java.awt.geom.Point2D;

import comportements.Closed;
import comportements.IComportement;
import deplacements.Hasard;
import deplacements.IDeplacement;


public class BouncingCreature extends AbstractCreature{
	
	public BouncingCreature(IEnvironment environment,IComportement behaviour, IDeplacement move, Point2D position, double speed,
			double direction, Color color) {
		super(environment, Closed.getInstance(), new Hasard(), position);

		this.speed = speed;
		this.direction = direction;
		this.color = color;
		
	}

	public void act() {
		move.act(this, comport);
	}
	

}
