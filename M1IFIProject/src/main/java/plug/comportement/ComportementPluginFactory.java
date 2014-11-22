package plug.comportement;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import plug.IPlugin;
import plug.PluginLoader;

import comportement.IComportement;

import creatures.AbstractCreature;

public class ComportementPluginFactory {
	/**
	 * singleton for the abstract factory
	 */
	protected static ComportementPluginFactory _singleton;

	private IComportement comportement;

	protected PluginLoader pluginLoader;

	private final String pluginDir = "myplugins/repository";

	protected Map<String, Constructor<? extends IComportement>> constructorMap;

	/**
	 * logger facilities to trace plugin loading...
	 */
	private static Logger logger = Logger
			.getLogger("plug.ComportementPluginFactory");

	public static void init(IComportement comportement) {
		if (_singleton != null) {
			throw new RuntimeException("ComportementFactory already created by "
					+ _singleton.getClass().getName());
		} else {
			_singleton = new ComportementPluginFactory(comportement);
		}
	}

	public static ComportementPluginFactory getInstance() {
		return _singleton;
	}

	private ComportementPluginFactory(IComportement comportement) {
		try {
			pluginLoader = new PluginLoader(pluginDir, IComportement.class);
		} catch (MalformedURLException ex) {
		}
		this.comportement = comportement;
		constructorMap = new HashMap<String, Constructor<? extends IComportement>>();
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
		System.out.println("1");
		for (Class<? extends IPlugin> p : pluginLoader.getPluginClasses()) {
			Constructor<? extends IComportement> c = null;
			try {
				c = (Constructor<? extends IComportement>) p
						.getDeclaredConstructor(AbstractCreature.class,
								double.class, double.class);
				c.setAccessible(true);
			} catch (SecurityException e) {
				logger.info("Cannot access (security) constructor for plugin"
						+ p.getName());
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				logger.info("No constructor in plugin " + p.getName()
						+ " with the correct signature");
				System.out.println("ooooo"+p.getName());
				e.printStackTrace();
			}
			if (c != null)
				constructorMap.put(p.getName(), c);
		}
	}

	public Map<String, Constructor<? extends IComportement>> getConstructorMap() {
		return constructorMap;
	}
}
