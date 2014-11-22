package deplacements;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import creatures.AbstractCreature;
import creatures.CustomCreature;

public class Stupid implements IDeplacement {

	private CustomCreature creature;

	public Stupid(CustomCreature creature){
		this.creature = creature;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}

	public void act() {
		move();
	}

	public void move() {
		Dimension s = creature.getEnvironment().getSize();
		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;
		double newX = creature.getPosition().getX() + creature.getSpeed() * cos(creature.getDirection());
		double newY = creature.getPosition().getY() + creature.getSpeed() * sin(creature.getDirection());
		
		if((newX < -hw)||(newX > hw)||(newY < -hh)||(newY > hh)){
			creature.getMonde().behaviour(newX, newY);
		}else{
			creature.setPosition(new Point2D.Double(newX, newY));
		}
	}

}
