package deplacements;

import comportement.IComportement;

import creatures.AbstractCreature;
import plug.IPlugin;

public interface IDeplacement extends IPlugin{
	public void move();
	
	public void act(AbstractCreature creature, IComportement comportement);
}
