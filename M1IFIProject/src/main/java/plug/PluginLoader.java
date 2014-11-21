package plug;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/*
 * Non Generic Class Loader (PluginLoader<P> would be a pain, see why?)
 */
public class PluginLoader {

	/**
	 * current classloader object for plugins
	 */
	private URLClassLoader loader;

	/**
	 * directory where plugins are loaded from
	 */
	private String pluginDirectory;

	/**
	 * plugin cache (loaded plugin)
	 */
	private Map<String, Class<IPlugin>> loadedPluginClasses = new HashMap<String, Class<IPlugin>>();

	/**
	 * logger facilities to trace plugin loading...
	 */
	private static Logger logger = Logger.getLogger("plug.PluginLoader");

	/**
	 * type of the plugins (subtype of IPlugin), use to filter out plugins
	 */
	protected Class<? extends IPlugin> ptype;

	/**
	 * create a plugin loader, loading from directory
	 * 
	 * @param directory
	 *            loading location (out of classpath for reloading)
	 */
	public PluginLoader(String directory, Class<? extends IPlugin> type)
			throws MalformedURLException {
		// check whether the URL is a directory
		File dirFile = new File(directory);
		if (dirFile == null || !dirFile.isDirectory()) {
			String errmsg = directory + " is not a directory";
			logger.warning(errmsg);
			throw new IllegalArgumentException(errmsg);
		}
		// add an ending "/" if needed (classloader seems to need this)
		if (!directory.endsWith("/")) {
			directory += "/";
		}
		this.pluginDirectory = directory;
		ptype = type;
		createNewClassLoader();
	}

	/**
	 * load all plugins of type ptype (does not change any plugin already
	 * loaded)
	 */
	public void loadPlugins() {
		// Indirection to the "directory" version, could be extended with Jar
		// support
		loadFromDirectory();
	}

	/**
	 * Reload all plugins of type P (erasing all previously loaded plugins)
	 */
	public void reloadPlugins() {
		try {
			createNewClassLoader();
		} catch (MalformedURLException ex) {
			// big problem here, should not happen because of check at
			// construction
			ex.printStackTrace();
		}
		erasePluginClasses();
		loadPlugins();
	}

	/**
	 * Create a new class loader for plugin loader
	 */
	private void createNewClassLoader() throws MalformedURLException {
		logger.info("****** Creation of a new loader");
		loader = URLClassLoader
				.newInstance(new URL[] { getURL(pluginDirectory) });
	}

	/**
	 * @return a new list of the currently loaded plugin classes
	 */
	public List<Class<IPlugin>> getPluginClasses() {
		return new ArrayList<Class<IPlugin>>(loadedPluginClasses.values());
	}

	/**
	 * delete all plugin classes from the cache
	 */
	private void erasePluginClasses() {
		loadedPluginClasses.clear();
	}

	/**
	 * Load plugins from the plugin directory
	 */
	private void loadFromDirectory() {
		logger.info("=+=+=+=+=+ Entry in loadFromDirectory=+=+=+=+");
		loadFromSubdirectory(new File(pluginDirectory), pluginDirectory);
		logger.info("=+=+=+=+=+ Exit from loadFromDirectory=+=+=+=+");
	}

	/**
	 * Load plugins in a sub-dir from the baseName (not in a jar)
	 * 
	 * @param baseName
	 *            name of the base dir (to determine package naming)
	 * @param dir
	 *            sub-directory, package naming of the plugin should correspond
	 *            to the relative position in the sub-dir
	 */
	private void loadFromSubdirectory(File dir, String baseName) {
		logger.info("Loading in subdir " + dir + " with basename " + baseName);
		int baseNameLength = baseName.length();
		File[] files = dir.listFiles();
		logger.info("Le listing : " + files);
		// for (int i = 0; i < files.length; i++) {
		for (File file : files) {
			if (file.isDirectory()) {
				loadFromSubdirectory(file, baseName);
				continue;
			}
			// Not a directory
			logger.info("Looking at file " + file.getPath() + ";"
					+ file.getName());
			String path = file.getPath();
			String qualifiedClassName = getQualifiedName(baseNameLength, path);
			if (qualifiedClassName != null) {
				// TODO
				System.out.println(qualifiedClassName);
				Class<IPlugin> plugin = loadOnePluginClass(qualifiedClassName);
				PluginTest plugTest = new PluginTest();
				if (plugin != null) {
					// debut lancement test plugins
					Class loadedClassTest;
					try {
						// TODO ICIIIIIIIII TESTER LES AUTRES SI RUNNER PASSE, L
						// IDEE EST LA MAIS
						// TODO IL NE ME TROUVE PAS LA CLASSE JUNITCORE
						loadedClassTest = loader.loadClass(plugin.getName()
								+ "Test");
						System.out.println(plugTest.getStringTestResult(loadedClassTest));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				if (plugin != null) {
					boolean notLoaded = (loadedPluginClasses.get(plugin
							.getName()) == null);
					if (notLoaded) {
						logger.info("Class " + qualifiedClassName
								+ " is a new plugin!");
						loadedPluginClasses.put(plugin.getName(), plugin);
					} else {
						logger.info("Class " + qualifiedClassName
								+ " is already loaded, IGNORING!");
					}
				}
			}
		}
	}

	/**
	 * Computing fully qualified name from classPath by removing baseNameLength
	 * characters from the start. Base dir is ended by "/", e.g., a/b/c/ (i.e.,
	 * 6 for baseNameLength) and a/b/c/d/e/F.class returns d.e.F
	 * 
	 * @param baseNameLength
	 *            number of characters of the baseName
	 * @param classPath
	 *            path to the class
	 * @return fully qualified name of the class, or null if it is an internal
	 *         class
	 */
	private static String getQualifiedName(int baseNameLength, String classPath) {
		logger.info("Computing fully qualified name from" + classPath
				+ " by removing " + baseNameLength
				+ " characters from the start");
		// A plugin cannot be an internal class
		if ((!classPath.endsWith(".class")) || (classPath.indexOf('$') != -1)) {
			return null;
		}
		classPath = classPath.substring(baseNameLength).replace(
				File.separatorChar, '.');
		// removing .class at end
		logger.info("Fully qualified name of the class: " + classPath);
		return classPath.substring(0, classPath.lastIndexOf('.'));
	}

	/**
	 * Transform the dir name into a coherent URL if the format was not
	 * respected
	 * 
	 * @param dir
	 *            directory name
	 * @return well-formed URL
	 * @throws MalformedURLException
	 *             if the dir naming cannot be figured out
	 */
	private static URL getURL(String dir) throws MalformedURLException {
		logger.info("URL not transformed: " + dir);
		// check for Windows naming
		if (dir.indexOf("\\") != -1) {
			// Windows!
			// 4 \ to obtain \\ in a regexp
			dir = dir.replaceAll("\\\\", "/");
		}
		/*
		 * dir starts with file:, we consider it as complete (classpath or
		 * absolute). Otherwise, we just add file: and assume classpath.
		 */
		if (!dir.startsWith("file:")) {
			dir = "file:" + dir;
		}
		logger.info("URL transformed: " + dir);
		return new URL(dir);
	}

	/**
	 * Load one class (of type ptype) from className
	 * 
	 * @param className
	 *            name of the plugin class
	 * @return plugin Class, or null if any problem
	 */
	@SuppressWarnings("unchecked")
	private Class<IPlugin> loadOnePluginClass(String className) {
		try {
			logger.info("Request for loading class " + className + " by "
					+ this);
			Class<?> loadedClass = loader.loadClass(className);
			// check that the class is of the right type
			if (ptype.isAssignableFrom(loadedClass)) {
				return (Class<IPlugin>) loadedClass;
			} else {
				logger.warning("Class " + className
						+ " is not from the expected type" + ptype.getName());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.warning("Plugin " + className + " not found");
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
			logger.warning("Class " + className + " not defined");
		}
		return null;
	}

	public Class<? extends IPlugin> getPtype() {
		return ptype;
	}

	public String getPluginDirectory() {
		return pluginDirectory;
	}

}
