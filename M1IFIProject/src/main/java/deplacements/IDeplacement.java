package deplacements;

import creatures.AbstractCreature;
import creatures.BouncingCreature;
import plug.IPlugin;

public interface IDeplacement extends IPlugin{
	public void move(double incX, double incY);
	
	public void act(AbstractCreature creature);
	
	public double hasardX(AbstractCreature creature);
	
	public double hasardY(AbstractCreature creature);
}
