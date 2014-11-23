package plug.deplacements;

import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import deplacements.IDeplacement;

public class PluginMenuItemBuilderDeplacement {
	private JMenu menu;

	private Map<String, Constructor<? extends IDeplacement>> constructors;

	private ActionListener listener;

	private static Logger logger = Logger.getLogger("plug.Menu");

	public PluginMenuItemBuilderDeplacement(
			Map<String, Constructor<? extends IDeplacement>> mc,
			ActionListener listener) {
		menu = new JMenu();
		this.constructors = mc;
		this.listener = listener;
	}

	public void setMenuTitle(String title) {
		menu.setText(title);
	}

	public void buildMenu() {
		logger.info("Building plugin menu");
		menu.removeAll();
		for (String name : constructors.keySet()) {
			System.out.println(name);
			JMenuItem mi = new JMenuItem(name);
			// ActionCommand contains the name of the plugin = key in the map
			mi.setActionCommand(name);
			mi.addActionListener(listener);
			menu.add(mi);
		}
	}

	public JMenu getMenu() {
		return menu;
	}

}