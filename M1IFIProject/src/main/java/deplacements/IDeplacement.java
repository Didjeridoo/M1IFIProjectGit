package deplacements;

import creatures.AbstractCreature;
import creatures.BouncingCreature;
import plug.IPlugin;

public interface IDeplacement extends IPlugin{
	public void move();
	
	public void act();
}
