package deplacement;

import java.awt.geom.Point2D;

import plug.IPlugin;
import creatures.BouncingCreature;
import creatures.IEnvironment;

public interface IDeplacement extends IPlugin{

	public double hasardX(BouncingCreature creature);
	
	public double hasardY(BouncingCreature creature);
}
