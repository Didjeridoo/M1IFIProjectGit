package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import plug.creatures.CreaturePluginFactory;
import plug.creatures.PluginMenuItemBuilder;
import visual.FormDesiredQuantity;
import visual.TestResultsDisplay;
import creatures.ICreature;
import creatures.visual.ColorCube;
import creatures.visual.CreatureInspector;
import creatures.visual.CreatureSimulator;
import creatures.visual.CreatureVisualizer;

/**
 * Just a simple test of the simulator.
 * 
 */
@SuppressWarnings("serial")
public class Launcher extends JFrame {
	private TestResultsDisplay testResultsDisplay;
	private int quantity;

	private final CreaturePluginFactory factory;

	private final CreatureInspector inspector;
	private final CreatureVisualizer visualizer;
	private final CreatureSimulator simulator;

	private PluginMenuItemBuilder menuBuilder;
	private JMenuBar mb = new JMenuBar();
	private Constructor<? extends ICreature> currentConstructor = null;

	public Launcher() {
		quantity = 0;
		factory = CreaturePluginFactory.getInstance();

		setName("Creature Simulator Plugin Version");
		setLayout(new BorderLayout());

		JPanel buttons = new JPanel();
		JButton loader = new JButton("Load plugins");
		loader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factory.load();
				buildPluginMenus();
			}
		});
		buttons.add(loader);

		JButton reloader = new JButton("Reload plugins");
		reloader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factory.reload();
				buildPluginMenus();
			}
		});
		buttons.add(reloader);

		JButton restart = new JButton("(Re-)start simulation");
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
							.createCreatures(simulator, quantity, new ColorCube(50),
									currentConstructor);
					simulator.addAllCreatures(creatures);
					simulator.start();
				}
			}
		});
		buttons.add(restart);

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

	}

	private void exit(WindowEvent evt) {
		System.exit(0);
	}

	public void buildPluginMenus() {
		mb.removeAll();
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// the name of the plugin is in the ActionCommand
				
				String[] labels = { "Enter the desired quantity" };
			    char[] mnemonics = { 'Q' };
			    int[] widths = { 4 };
			    String[] descs = { "Enter the desired quantity" };

			    final FormDesiredQuantity form = new FormDesiredQuantity(labels, mnemonics, widths, descs);

			    JButton submit = new JButton("Submit");

			    final JFrame f = new JFrame("Configuration");
			    f.getContentPane().add(form, BorderLayout.NORTH);
			    JPanel p = new JPanel();
			    p.add(submit);
			    f.getContentPane().add(p, BorderLayout.SOUTH);
			    f.pack();
			    f.setVisible(true);
			    
			    submit.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			        quantity = Integer.parseInt(form.getText(0));
			        f.dispose();
			      }
			    });
				
				currentConstructor = factory.getConstructorMap().get(
						((JMenuItem) e.getSource()).getActionCommand());
			}
		};
		menuBuilder = new PluginMenuItemBuilder(factory.getConstructorMap(),
				listener);
		menuBuilder.setMenuTitle("Creatures");
		menuBuilder.buildMenu();
		mb.add(menuBuilder.getMenu());
		setJMenuBar(mb);
	}

	public static void main(String args[]) {
		Logger.getLogger("plug").setLevel(Level.INFO);
		double myMaxSpeed = 5;
		CreaturePluginFactory.init(myMaxSpeed);
		Launcher launcher = new Launcher();
		launcher.setVisible(true);
	}

}
