package deplacements;

import creatures.AbstractCreature;
import creatures.BouncingCreature;
import creatures.CustomCreature;
import plug.IPlugin;
import plug.comportement.IComportement;

public interface IDeplacement extends IPlugin{
	public void move();
	
	public void act(AbstractCreature creature, IComportement comportement);
}
