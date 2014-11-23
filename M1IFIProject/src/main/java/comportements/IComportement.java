package comportements;

import plug.IPlugin;
import creatures.AbstractCreature;

public interface IComportement extends IPlugin{
	public void behaviour(AbstractCreature creature, double x, double y);
}
