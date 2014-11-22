package deplacements;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import creatures.AbstractCreature;
import creatures.BouncingCreature;
import creatures.CustomCreature;

public class Hasard implements IDeplacement{
	
	private CustomCreature creature;

	public Hasard(CustomCreature creature){
		this.creature = creature;
	}
	
	public String getName() {
		return getClass().getName();
	}
	

	public void act() {
		creature.applyNoise();
		move();
		
	}
	

	public void move() {
		Dimension s = creature.getEnvironment().getSize();
		
		double newX = creature.getPosition().getX() + creature.getSpeed() * cos(creature.getDirection());
		// the reason there is a minus instead of a plus is that in our plane
		// Y coordinates rises downwards
		double newY = creature.getPosition().getY() - creature.getSpeed() * sin(creature.getDirection());

		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;
		if((newX < -hw)||(newX > hw)||(newY < -hh)||(newY > hh)){
			creature.getMonde().behaviour(newX, newY);
		}else{
			creature.setPosition(new Point2D.Double(newX, newY));
		}
	}
	
}
