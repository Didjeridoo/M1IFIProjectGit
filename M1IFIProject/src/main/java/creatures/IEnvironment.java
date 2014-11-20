package creatures;

import java.awt.Dimension;


public interface IEnvironment {

	public Dimension getSize();

	public Iterable<ICreature> getCreatures();

}
