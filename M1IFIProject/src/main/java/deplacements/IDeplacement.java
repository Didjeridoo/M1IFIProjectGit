package deplacements;

import comportements.IComportement;
import creatures.AbstractCreature;
import creatures.BouncingCreature;
import creatures.CustomCreature;
import plug.IPlugin;

public interface IDeplacement extends IPlugin{
	public void move();
	
	public void act(AbstractCreature creature, IComportement comportement);
}
