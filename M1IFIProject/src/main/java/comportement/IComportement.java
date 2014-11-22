package comportement;

import creatures.AbstractCreature;
import plug.IPlugin;

public interface IComportement extends IPlugin{
	public void behaviour(AbstractCreature creature, double x, double y);
}
