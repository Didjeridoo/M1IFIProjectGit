package plug.creatures;

import java.lang.reflect.*;
import java.util.Map;
import java.util.logging.Logger;
import java.awt.event.*;

import javax.swing.*;

import creatures.ICreature;

public class PluginMenuItemBuilderCreature {

	private JMenu menu;

	private Map<String, Constructor<? extends ICreature>> constructors;

	private ActionListener listener;

	private static Logger logger = Logger.getLogger("plug.Menu");

	public PluginMenuItemBuilderCreature(
			Map<String, Constructor<? extends ICreature>> mc,
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
