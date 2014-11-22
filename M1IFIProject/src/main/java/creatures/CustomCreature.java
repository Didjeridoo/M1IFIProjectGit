package creatures;

import static java.lang.Math.PI;
import static java.lang.Math.random;

import java.awt.Color;
import java.awt.geom.Point2D;

import comportement.IComportement;

import deplacements.IDeplacement;

public class CustomCreature extends AbstractCreature{

	private static final double MIN_SPEED = 3;
	private static final double MAX_SPEED = 10;

	/**
	 * Number of cycles after which we apply some random noise.
	 */
	private static final int NUMBER_OF_CYCLES_PER_CHANGE = 30;

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

	public void applyNoise() {
		currCycle++;
		currCycle %= NUMBER_OF_CYCLES_PER_CHANGE;

		// every NUMBER_OF_CYCLES_PER_CHANGE we do the change
		if (currCycle == 0) {
			this.speed += ((random() * 2) - 1);

			// maintain the speed within some boundaries
			if (this.speed < MIN_SPEED) {
				this.speed = MIN_SPEED;
			} else if (this.speed > MAX_SPEED) {
				this.speed = MAX_SPEED;
			}

			setDirection(this.direction
					+ ((random() * PI / 2) - (PI / 4)));
		}
	}
	
	public void act() {
		move.act(this, comport);
	}
	
}
