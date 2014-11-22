package deplacements;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import creatures.AbstractCreature;
import creatures.BouncingCreature;

public class Hasard implements IDeplacement{
	
	
	public String getName() {
		return getClass().getName();
	}
	
	public double hasardX(AbstractCreature creature){
		
		double newX = creature.getPosition().getX() + creature.getSpeed() * cos(creature.getDirection());
		double hw = creature.getEnvironment().getSize().getWidth() / 2;
		if (newX < -hw) {
			newX = - 2*hw - newX;;	
		} else if (newX > hw) {
			newX = 2*hw - newX;
		}
		
		return newX;
	}
	
	public double hasardY(AbstractCreature creature){
		
		double newY = creature.getPosition().getY() - creature.getSpeed() * sin(creature.getDirection());
		
		double hh = creature.getEnvironment().getSize().getHeight() / 2;
		
		
		if (newY < -hh) {
			newY = - 2*hh - newY;
		} else if (newY > hh) {
			newY = 2*hh - newY;
		}
		return newY;
	}

	public void move(double incX, double incY) {
		// TODO Auto-generated method stub
		
	}

	public void act(AbstractCreature creature) {
		// TODO Auto-generated method stub
		
	}
}
