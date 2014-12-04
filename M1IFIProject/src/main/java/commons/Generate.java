package commons;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import creatures.visual.ColorCube;

public class Generate {
	private Config config;
	private String[] features;
	private String path;
	private static Generate instance = new Generate(new String[] { "moyen",
			"cube", "VAleatoire", "DAleatoire", "Circular", "CustomCreature",
			"Troupeau" });

	private Generate(String[] args) {
		// TODO Auto-generated constructor stub
		config = Config.getInstance();
		features = args;
		path = new File("").getAbsolutePath();
	}

	public static Generate getInstance() {
		return instance;
	}

	public void generateConfig() {
		generateMoteur(features[0]);
		generateColor(features[1]);
		generateVitesse(features[2]);
		generateDirection(features[3]);
		generateEnvironnement(features[4]);
		generateCreature(features[5]);
		generateDeplacement(features[6]);
	}

	public void generateMoteur(String vitesse) {
		generateVitesseSimu(vitesse);
	}

	private void generateVitesseSimu(String vitesseSimu) {
		// TODO Auto-generated method stub
		if (vitesseSimu.equalsIgnoreCase("lent")) {
			config.setVitesseSimu(20);
		} else if (vitesseSimu.equalsIgnoreCase("moyen")) {
			config.setVitesseSimu(10);
		} else if (vitesseSimu.equalsIgnoreCase("rapide")) {
			config.setVitesseSimu(5);
		}
	}

	private void generateColor(String couleur) {
		if (couleur.equalsIgnoreCase("cube")) {
			config.setColor(new ColorCube(50));
		} /*
		 * else if(couleur.equalsIgnoreCase("unique")){ config.setColor(new
		 * Unique()); } else if(couleur.equalsIgnoreCase("group")){
		 * config.setColor(new Group()); }
		 */
	}

	public void generateVitesse(String vitesse) {
		if (vitesse.equalsIgnoreCase("VAleatoire")) {
			config.setVitesse(-1);
		} else if (vitesse.equalsIgnoreCase("VFixe")) {
			config.setVitesse(5);
		}
	}

	public void generateDirection(String direction) {
		if (direction.equalsIgnoreCase("DAleatoire")) {
			config.setDirection(-1);
		} else if (direction.equalsIgnoreCase("DFixe")) {
			config.setDirection(0.d);
		}
	}

	private void generateEnvironnement(String environnement) {
		// TODO Auto-generated method stub
		// String env = "";
		// if (environnement.equalsIgnoreCase("torique")) {
		// env = "Toric";
		// config.setEnvironnement(Toric.getInstance());
		// } else if (environnement.equalsIgnoreCase("ferme")) {
		// env = "Closed";
		// config.setEnvironnement(Closed.getInstance());
		// } else if (environnement.equalsIgnoreCase("monde")) {
		// env = "Circular";
		// config.setEnvironnement(Circular.getInstance());
		// }

		// récupère le comportement aux bords voulu
		// String env = config.getEnvironnement().getName().split("\\.")[1];

		Path pathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "comportements" + File.separator
				+ environnement + ".class");
		Path pathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "comportements" + File.separator + environnement + ".class");

		Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "comportements" + File.separator
				+ environnement + "Test" + ".class");
		Path testPathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "comportements" + File.separator + environnement + "Test"
				+ ".class");

		try {
			Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
			Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generateCreature(String creature) {

		Path pathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "creatures" + File.separator + creature
				+ ".class");
		Path pathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator + "creatures"
				+ File.separator + creature + ".class");

		Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "creatures" + File.separator + creature
				+ "Test" + ".class");
		Path testPathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator + "creatures"
				+ File.separator + creature + "Test" + ".class");

		try {
			Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
			Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateDeplacement(String deplacement) {

		Path pathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "deplacements" + File.separator
				+ deplacement + ".class");
		Path pathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "deplacements" + File.separator + deplacement + ".class");

		Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "deplacements" + File.separator
				+ deplacement + "Test" + ".class");
		Path testPathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "deplacements" + File.separator + deplacement + "Test"
				+ ".class");

		try {
			Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
			Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}