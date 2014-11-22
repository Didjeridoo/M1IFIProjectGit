package comportement;

import creatures.AbstractCreature;
import plug.IPlugin;

public interface IComportement extends IPlugin{
	public void behaviour(double x, double y);
}
