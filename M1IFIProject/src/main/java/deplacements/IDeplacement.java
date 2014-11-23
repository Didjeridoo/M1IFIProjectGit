package deplacements;

import plug.IPlugin;

import comportements.IComportement;

import creatures.AbstractCreature;

public interface IDeplacement extends IPlugin{
	public void move();
	
	public void act(AbstractCreature creature, IComportement comportement);
}
