package plug.comportement;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import creatures.AbstractCreature;

public class Toric implements IComportement{

	private static AbstractCreature creature;

	public Toric(AbstractCreature creature){
		this.creature = creature;
	}
	private static Toric instance = new Toric(creature);
	
	private Toric(){}
	
	public void behaviour(double x, double y) {
		
		Dimension s = creature.getEnvironment().getSize();
		
		if (x > s.getWidth() / 2) {
			x = -s.getWidth() / 2;
		} else if (x < -s.getWidth() / 2) {
			x = s.getWidth() / 2;
		}

		if (y > s.getHeight() / 2) {
			y = -s.getHeight() / 2;
		} else if (y < -s.getHeight() / 2) {
			y = s.getHeight() / 2;
		}

		creature.changePosition(new Point2D.Double(x, y));
	}

	public static Toric getInstance(){return instance;}
	
	public void setCreature(AbstractCreature creature){this.creature = creature;}
	
	public String getName() {
		return getClass().getName();
	}

	public void behaviour() {
		// TODO Auto-generated method stub
		
	}
}
