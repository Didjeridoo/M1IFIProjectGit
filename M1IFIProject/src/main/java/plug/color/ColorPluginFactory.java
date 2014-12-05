package plug.color;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import plug.IPlugin;
import plug.PluginLoader;
import color.IColorStrategy;

public class ColorPluginFactory {
	/**
	 * singleton for the abstract factory
	 */
	protected static ColorPluginFactory _singleton;
	
	protected PluginLoader pluginLoader;

	private final String pluginDir = "myplugins/repository";

	protected Map<String, Constructor<? extends IColorStrategy>> constructorMap;

	/**
	 * logger facilities to trace plugin loading...
	 */
	private static Logger logger = Logger
			.getLogger("plug.ColorPluginFactory");

	public static void init() {
		if (_singleton != null) {
			throw new RuntimeException("ColorFactory already created by "
					+ _singleton.getClass().getName());
		} else {
			_singleton = new ColorPluginFactory();
		}
	}

	public static ColorPluginFactory getInstance() {
		return _singleton;
	}

	private ColorPluginFactory() {
		try {
			pluginLoader = new PluginLoader(pluginDir, IColorStrategy.class);
		} catch (MalformedURLException ex) {
		}
		constructorMap = new HashMap<String, Constructor<? extends IColorStrategy>>();
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
			Constructor<? extends IColorStrategy> c = null;
			try {
				c = (Constructor<? extends IColorStrategy>) p
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

	public Map<String, Constructor<? extends IColorStrategy>> getConstructorMap() {
		return constructorMap;
	}
}

