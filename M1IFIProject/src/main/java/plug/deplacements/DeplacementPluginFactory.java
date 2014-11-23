package plug.deplacements;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import deplacements.IDeplacement;
import plug.IPlugin;
import plug.PluginLoader;

public class DeplacementPluginFactory {

	/*
	 * singleton for the abstract factory
	 */
	protected static DeplacementPluginFactory _singleton;

	protected PluginLoader pluginLoader;

	private final String pluginDir = "myplugins/repository";

	protected Map<String, Constructor<? extends IDeplacement>> constructorMap;

	/**
	 * logger facilities to trace plugin loading...
	 */
	private static Logger logger = Logger
			.getLogger("plug.DeplacementPluginFactory");

	public static void init(double inMaxSpeed) {
		if (_singleton != null) {
			throw new RuntimeException("CreatureFactory already created by "
					+ _singleton.getClass().getName());
		} else {
			_singleton = new DeplacementPluginFactory();
		}
	}

	public static DeplacementPluginFactory getInstance() {
		return _singleton;
	}

	private DeplacementPluginFactory() {
		try {
			pluginLoader = new PluginLoader(pluginDir, IDeplacement.class);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
		constructorMap = new HashMap<String, Constructor<? extends IDeplacement>>();
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
			Constructor<? extends IDeplacement> c = null;
			try {
				c = (Constructor<? extends IDeplacement>) p
						.getDeclaredConstructor();
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

	public Map<String, Constructor<? extends IDeplacement>> getConstructorMap() {
		return constructorMap;
	}

	public <T extends IDeplacement> T createBehaviour(Constructor<T> constructor) {
		T behaviour = null;
		try {
			behaviour = constructor.newInstance();
		} catch (Exception e) {
			logger.info("calling constructor " + constructor
					+ " failed with exception " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return behaviour;
	}
}
