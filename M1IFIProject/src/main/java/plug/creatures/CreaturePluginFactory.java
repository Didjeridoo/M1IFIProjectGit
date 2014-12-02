package plug.creatures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import commons.Config;
import commons.Generate;
import comportements.Circular;
import comportements.IComportement;
import plug.IPlugin;
import plug.PluginLoader;
import creatures.IColorStrategy;
import creatures.ICreature;
import creatures.IEnvironment;
import deplacements.IDeplacement;

public class CreaturePluginFactory {

	/**
	 * singleton for the abstract factory
	 */
	protected static CreaturePluginFactory _singleton;

	private double maxSpeed;

	protected PluginLoader pluginLoader;

	private final String pluginDir = "myplugins/repository";

	protected Map<String, Constructor<? extends ICreature>> constructorMap;

	/**
	 * logger facilities to trace plugin loading...
	 */
	private static Logger logger = Logger
			.getLogger("plug.CreaturePluginFactory");

	public static void init(double inMaxSpeed) {
		if (_singleton != null) {
			throw new RuntimeException("CreatureFactory already created by "
					+ _singleton.getClass().getName());
		} else {
			_singleton = new CreaturePluginFactory(inMaxSpeed);
		}
	}

	public static CreaturePluginFactory getInstance() {
		return _singleton;
	}

	private CreaturePluginFactory(double inMaxSpeed) {
		try {
			pluginLoader = new PluginLoader(pluginDir, ICreature.class);
		} catch (MalformedURLException ex) {
		}
		maxSpeed = inMaxSpeed;
		constructorMap = new HashMap<String, Constructor<? extends ICreature>>();
		load();
	}

	public void load() {
		pluginLoader.loadPlugins();
		buildConstructorMap();
	}

	public void reload() {
		pluginLoader.reloadPlugins();
		constructorMap.clear();
		buildConstructorMap();
	}

	@SuppressWarnings("unchecked")
	private void buildConstructorMap() {
		for (Class<? extends IPlugin> p : pluginLoader.getPluginClasses()) {
			Constructor<? extends ICreature> c = null;
			try {
				c = (Constructor<? extends ICreature>) p
						.getDeclaredConstructor(IEnvironment.class,IComportement.class,IDeplacement.class,
								Point2D.class, double.class, double.class,
								Color.class);
				c.setAccessible(true);
			} catch (SecurityException e) {
				logger.info("Cannot access (security) constructor for plugin"
						+ p.getName());
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				logger.info("No constructor in plugin " + p.getName()
						+ " with the correct signature");
				e.printStackTrace();
			}
			if (c != null)
				constructorMap.put(p.getName(), c);
		}
	}

	public Map<String, Constructor<? extends ICreature>> getConstructorMap() {
		return constructorMap;
	}

	private final Random rand = new Random();

	public <T extends ICreature> Collection<T> createCreatures(
			IEnvironment env,IComportement comportement, IDeplacement move, int count, IColorStrategy colorStrategy,
			Constructor<T> constructor) {
		Collection<T> creatures = new ArrayList<T>();
		Dimension s = env.getSize();
		for (int i = 0; i < count; i++) {
			// X coordinate
			double x = (rand.nextDouble() * s.getWidth()) - s.getWidth() / 2;
			// Y coordinate
			double y = (rand.nextDouble() * s.getHeight()) - s.getHeight() / 2;
			// direction
			double direction = Config.getInstance().getDirection();
			if(direction == -1){
				Random rand = new Random();
				direction = rand.nextDouble() * 2 * Math.PI;
			}
			// speed
			int speed = (int)maxSpeed;
			if(maxSpeed == -1){
				Random rand = new Random();
				speed = (int)(rand.nextDouble() * 10);
			}
			T creature = null;
			try {
				creature = constructor.newInstance(env,comportement,move,
						new Point2D.Double(x, y), speed, direction,
						colorStrategy.getColor());
			} catch (Exception e) {
				logger.info("calling constructor " + constructor
						+ " failed with exception " + e.getLocalizedMessage());
				e.printStackTrace();
			}
			creatures.add(creature);
		}
		return creatures;
	}

}
