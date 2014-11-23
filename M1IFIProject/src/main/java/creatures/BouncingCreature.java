package creatures;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import comportements.IComportement;

import deplacements.IDeplacement;


public class BouncingCreature extends AbstractCreature{

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

	public BouncingCreature(IEnvironment environment,IComportement behaviour, IDeplacement move, Point2D position, double speed,
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

	/**
	 * Every number of cycles we apply some random noise over speed and
	 * direction
	 */
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

	/**
	 * The actual move
	 */
	public void move() {
		Dimension s = environment.getSize();
		
		double newX = position.getX() + speed * cos(direction);
		// the reason there is a minus instead of a plus is that in our plane
		// Y coordinates rises downwards
		double newY = position.getY() - speed * sin(direction);

		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;

		// newX and newY were just put on the border of the envt. It's not a bug
		// as long as the tests passed. Now, the mirroring position is computed.
		if (newX < -hw) {
			newX = - 2*hw - newX;
			// ERROR #2 direction is badly managed 
			setDirectionBounceX();	
		} else if (newX > hw) {
			newX = 2*hw - newX;
			// ERROR #2 direction is badly managed 
			setDirectionBounceX();
		} // else // ERROR #1 (NO ELSE, we need to check X and Y independently)
		
		if (newY < -hh) {
			newY = - 2*hh - newY;
			// ERROR #2 direction is badly managed 
			setDirectionBounceY();
		} else if (newY > hh) {
			// ERROR #3 (cut and paste led to "hw" instead of "hh")
			newY = 2*hh - newY;
			// ERROR #2 direction is badly managed 
			setDirectionBounceY();
		}
		
		setPosition(newX, newY);
	}
	
	public void setDirectionBounceX() {
		if (direction >= PI)
			setDirection(3*PI - direction);
		else
			setDirection(PI - direction);
	}

	public void setDirectionBounceY() {
		setDirection(PI * 2 - direction);
	}
}
