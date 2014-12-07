package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import plug.color.ColorPluginFactory;
import plug.color.PluginMenuItemBuilderColor;
import plug.comportements.ComportementPluginFactory;
import plug.comportements.PluginMenuItemBuilderComportement;
import plug.creatures.CreaturePluginFactory;
import plug.creatures.PluginMenuItemBuilderCreature;
import plug.deplacements.DeplacementPluginFactory;
import plug.deplacements.PluginMenuItemBuilderDeplacement;
import visual.FormDesiredQuantity;
import visual.TestResultsDisplay;
import color.Cube;
import color.Groupe;
import color.IColorStrategy;
import color.Unique;

import comportements.Circular;
import comportements.Closed;
import comportements.IComportement;
import comportements.Toric;

import config.Config;
import config.ConfigFile;
import creatures.ICreature;
import creatures.visual.CreatureInspector;
import creatures.visual.CreatureSimulator;
import creatures.visual.CreatureVisualizer;
import deplacements.IDeplacement;

/**
 * Just a simple test of the simulator.
 * 
 */
@SuppressWarnings("serial")
public class Launcher extends JFrame {

	private TestResultsDisplay testResultsDisplay;
	private int quantity;

	private IComportement comportement;
	private IDeplacement move;

	private final CreaturePluginFactory factory;
	private final ComportementPluginFactory comportementFactory;
	private final DeplacementPluginFactory deplacementFactory;
	private final ColorPluginFactory colorFactory;

	private final CreatureInspector inspector;
	private final CreatureVisualizer visualizer;
	private final CreatureSimulator simulator;

	private PluginMenuItemBuilderCreature menuBuilderCreature;
	private PluginMenuItemBuilderComportement menuBuilderComportement;
	private PluginMenuItemBuilderDeplacement menuBuilderDeplacement;
	private PluginMenuItemBuilderColor menuBuilderColor;
	private JMenuBar mb = new JMenuBar();
	private Constructor<? extends ICreature> currentConstructor = null;
	private Constructor<? extends IComportement> constructorComportement = null;
	private Constructor<? extends IDeplacement> constructorDeplacement = null;
	private Constructor<? extends IColorStrategy> constructorColor = null;
	final JButton restart;
	private String path;

	public Launcher() {
		quantity = 0;
		factory = CreaturePluginFactory.getInstance();
		comportementFactory = ComportementPluginFactory.getInstance();
		deplacementFactory = DeplacementPluginFactory.getInstance();
		colorFactory = ColorPluginFactory.getInstance();
		path = new File("").getAbsolutePath();
		
		setName("Creature Simulator Plugin Version");
		setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel();
		JButton loader = new JButton("Load plugins");
		loader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factory.load();
				comportementFactory.load();
				deplacementFactory.load();
				colorFactory.load();
				// buildPluginMenus();
			}
		});
		buttons.add(loader);

		JButton reloader = new JButton("Reload plugins");
		reloader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factory.reload();
				comportementFactory.reload();
				deplacementFactory.reload();
				colorFactory.reload();
				// buildPluginMenus();
			}
		});
		buttons.add(reloader);
		final JButton add = new JButton("Add creatures");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentConstructor != null) {
					Collection<? extends ICreature> creatures = factory
							.createCreatures(simulator, ConfigFile.environnement, move, quantity, Config
									.getInstance().getColor(),
									currentConstructor);
					simulator.addAllCreatures(creatures);
					currentConstructor = null;
					menuBuilderCreature.getMenu().setEnabled(true);
					;
				}

			}
		});
		buttons.add(add);
		add.setEnabled(false);

		restart = new JButton("(Re-)start simulation");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentConstructor != null) {
					synchronized (simulator) {
						if (simulator.isRunning()) {
							simulator.stop();
						}
					}
					simulator.clearCreatures();
					Collection<? extends ICreature> creatures = factory
							.createCreatures(simulator, ConfigFile.environnement, move, quantity, Config
									.getInstance().getColor(),
									currentConstructor);
					simulator.addAllCreatures(creatures);
					simulator.start();
					add.setEnabled(true);
					menuBuilderCreature.getMenu().setEnabled(true);
					currentConstructor = null;
				}
			}
		});
		buttons.add(restart);
		restart.setEnabled(false);

		JButton rapportDeTests = new JButton("Rapport de tests");
		rapportDeTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (testResultsDisplay == null) {
					testResultsDisplay = new TestResultsDisplay();
				}
				if (!testResultsDisplay.isVisible()) {
					testResultsDisplay.setVisible(true);
				}
			}
		});
		buttons.add(rapportDeTests);

		add(buttons, BorderLayout.SOUTH);

		simulator = new CreatureSimulator(new Dimension(640, 480));
		inspector = new CreatureInspector();
		inspector.setFocusableWindowState(false);
		visualizer = new CreatureVisualizer(simulator);
		visualizer.setDebug(false);
		visualizer.setPreferredSize(simulator.getSize());

		add(visualizer, BorderLayout.CENTER);

		buildPluginMenus();

		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exit(evt);
			}
		});

		menuBuilderDeplacement.getMenu().setEnabled(false);
		menuBuilderCreature.getMenu().setEnabled(true);

	}

	private void exit(WindowEvent evt) {
		System.exit(0);
	}

	public void buildPluginMenus() {
		mb.removeAll();
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ConfigFile.nombre != -1) {
					quantity = ConfigFile.nombre;
					final String res = ((JMenuItem) e.getSource())
							.getActionCommand();
					if (res.equals("creatures.CustomCreature")) {
						menuBuilderDeplacement.getMenu().setEnabled(
								true);
					} else {
						menuBuilderDeplacement.getMenu().setEnabled(
								false);
						restart.setEnabled(true);
					}
					menuBuilderCreature.getMenu().setEnabled(false);
				} else {
					// the name of the plugin is in the ActionCommand

					String[] labels = { "Enter the desired quantity" };
					char[] mnemonics = { 'Q' };
					int[] widths = { 4 };
					String[] descs = { "Enter the desired quantity" };

					final FormDesiredQuantity form = new FormDesiredQuantity(
							labels, mnemonics, widths, descs);

					JButton submit = new JButton("Submit");

					final JFrame f = new JFrame("Configuration");
					f.getContentPane().add(form, BorderLayout.NORTH);
					JPanel p = new JPanel();
					p.add(submit);
					f.getContentPane().add(p, BorderLayout.SOUTH);
					f.pack();
					f.setVisible(true);

					final String res = ((JMenuItem) e.getSource())
							.getActionCommand();

					submit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							quantity = Integer.parseInt(form.getText(0));
							// quantity = config.getNombre();
							if (res.equals("creatures.CustomCreature")) {
								menuBuilderDeplacement.getMenu().setEnabled(
										true);
							} else {
								menuBuilderDeplacement.getMenu().setEnabled(
										false);
								restart.setEnabled(true);
							}
							menuBuilderCreature.getMenu().setEnabled(false);
							f.dispose();
						}
					});
				}

				currentConstructor = factory.getConstructorMap().get(
						((JMenuItem) e.getSource()).getActionCommand());

			}
		};
		ActionListener listenerComportement = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				constructorComportement = comportementFactory
						.getConstructorMap().get(
								((JMenuItem) e.getSource()).getActionCommand());
				String comportementAuxBords = constructorComportement.getName()
						.split("\\.")[1];
				if (comportementAuxBords.equalsIgnoreCase("Circular")) {
					Config.getInstance().setEnvironnement(
							Circular.getInstance());
				} else if (comportementAuxBords.equalsIgnoreCase("Toric")) {
					Config.getInstance().setEnvironnement(Toric.getInstance());
				} else if (comportementAuxBords.equalsIgnoreCase("Closed")) {
					Config.getInstance().setEnvironnement(Closed.getInstance());
				}
				try {
					comportement = constructorComportement.newInstance();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				menuBuilderComportement.getMenu().setEnabled(false);
				menuBuilderCreature.getMenu().setEnabled(true);
				menuBuilderComportement.setMenuTitle(Config.getInstance().getEnvironnement().getName());
			}
		};

		ActionListener listenerDeplacement = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				constructorDeplacement = deplacementFactory.getConstructorMap()
						.get(((JMenuItem) e.getSource()).getActionCommand());
				try {
					move = constructorDeplacement.newInstance();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				menuBuilderCreature.getMenu().setEnabled(true);
				menuBuilderDeplacement.getMenu().setEnabled(false);
				restart.setEnabled(true);
			}
		};

		ActionListener listenerColor = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				constructorColor = colorFactory.getConstructorMap().get(
						((JMenuItem) e.getSource()).getActionCommand());
				try {
					String couleur = constructorColor.getName().split("\\.")[1];
					if (couleur.equalsIgnoreCase("Unique")) {
						Config.getInstance().setColor(new Unique());
					} else if (couleur.equalsIgnoreCase("Cube")) {
						Config.getInstance().setColor(new Cube());
					} else if (couleur.equalsIgnoreCase("Groupe")) {
						Config.getInstance().setColor(new Groupe());
					}
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// menuBuilderCreature.getMenu().setEnabled(true);
				// menuBuilderDeplacement.getMenu().setEnabled(false);
				// restart.setEnabled(true);
			}
		};

		menuBuilderComportement = new PluginMenuItemBuilderComportement(
				comportementFactory.getConstructorMap(), listenerComportement);
		menuBuilderComportement.setMenuTitle(ConfigFile.environnement.getName());
		menuBuilderComportement.getMenu().setEnabled(false);
		menuBuilderComportement.buildMenu();
		mb.add(menuBuilderComportement.getMenu());

		menuBuilderCreature = new PluginMenuItemBuilderCreature(
				factory.getConstructorMap(), listener);
		menuBuilderCreature.setMenuTitle("Creatures");
		menuBuilderCreature.buildMenu();
		mb.add(menuBuilderCreature.getMenu());

		menuBuilderDeplacement = new PluginMenuItemBuilderDeplacement(
				deplacementFactory.getConstructorMap(), listenerDeplacement);
		menuBuilderDeplacement.setMenuTitle("Deplacements");
		menuBuilderDeplacement.buildMenu();
		mb.add(menuBuilderDeplacement.getMenu());

		menuBuilderColor = new PluginMenuItemBuilderColor(
				colorFactory.getConstructorMap(), listenerColor);
		menuBuilderColor.setMenuTitle("Color");
		menuBuilderColor.buildMenu();
		mb.add(menuBuilderColor.getMenu());

		setJMenuBar(mb);
	}

	public static void main(String args[]) {
		Logger.getLogger("plug").setLevel(Level.INFO);
		CreaturePluginFactory.init(ConfigFile.vitesse);
		ComportementPluginFactory.init();
		DeplacementPluginFactory.init();
		ColorPluginFactory.init();
		Launcher launcher = new Launcher();
		launcher.setVisible(true);
	}

}