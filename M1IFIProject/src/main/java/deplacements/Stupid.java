package deplacements;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import creatures.AbstractCreature;

public class Stupid implements IDeplacement {

	private AbstractCreature creature;

	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}

	public void move(double incX, double incY) {
		creature.setPosition(creature.getPosition().getX() + incX, creature
				.getPosition().getY() + incY);
	}

	public void act() {
		double incX = creature.getSpeed() * cos(creature.getDirection());
		double incY = creature.getSpeed() * sin(creature.getDirection());

		move(incX, incY);
	}

}
