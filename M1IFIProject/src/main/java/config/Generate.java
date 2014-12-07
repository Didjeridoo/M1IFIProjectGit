package config;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import comportements.Circular;
import comportements.Closed;
import comportements.Toric;

/**
 * Permet de generer les plugins, et donc construire un produit a partir de la
 * configuration entree par l'utilisateur.
 * 
 * 
 * @author Marc
 * 
 */
public class Generate {
	private Config config;
	private String path;
	HashMap<String, ArrayList<String>> collecConfig;
	private FileWriter writer;

	public Generate(HashMap<String, ArrayList<String>> fml) {
		// TODO Auto-generated constructor stub
		config = Config.getInstance();
		collecConfig = fml;
		path = new File("").getAbsolutePath();
	}

	/**
	 * Genere les plugins demandes par l'utilisateur
	 * @throws IOException 
	 */
	public void generateConfig() throws IOException {
		if (collecConfig != null) {
			try {
				writer = new FileWriter(path + File.separator + "src"
						+ File.separator + "main" + File.separator + "java"
						+ File.separator + "config" + File.separator
						+ "ConfigFile.java", false);
				writer.write("package config;\n");
				writer.write("public class ConfigFile{\n");
				writer.write("public static int nombre=99;\n");
				writer.write("}");
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			generateMoteur(collecConfig.get("VitesseSimu"));
			generateColor(collecConfig.get("Couleur"));
			generateVitesse(collecConfig.get("Vitesse"));
			generateDirection(collecConfig.get("Direction"));
			generateEnvironnement(collecConfig.get("Environnement"));
			generateDeplacement(collecConfig.get("Deplacement"));
			generateNombre(collecConfig.get("Nombre"));
			WriteConfig wc = new WriteConfig();
	        wc.write();
		} else {
			System.out
					.println("Erreur : Vous avez quitté la configuration du simulateur.");
		}

	}

	public void generateMoteur(ArrayList<String> vitesse) {
		generateVitesseSimu(vitesse);
	}

	/**
	 * 
	 * @ArrayList<String> vitesseSimu correspondante a la vitesse d'execution de
	 *                    la simulation.
	 * 
	 *                    lent : Execution delay in milliseconds 20ms moyen :
	 *                    Execution delay in milliseconds 10ms rapide :
	 *                    Execution delay in milliseconds 5ms
	 * 
	 */
	private void generateVitesseSimu(ArrayList<String> vitesseSimu) {
		// TODO Auto-generated method stub
		if (vitesseSimu == null) {
			config.setVitesseSimu(10);
			System.out.println("Vitesse de simulation non définie.");
			System.out.println("Vitesse de simulation par défaut : 10.");
		} else if (vitesseSimu.get(0).equalsIgnoreCase("lent")) {
			config.setVitesseSimu(20);
		} else if (vitesseSimu.get(0).equalsIgnoreCase("moyen")) {
			config.setVitesseSimu(10);
		} else if (vitesseSimu.get(0).equalsIgnoreCase("rapide")) {
			config.setVitesseSimu(5);
		}
	}

	/**
	 * 
	 * @ArrayList<String> couleur correspondante au plugin de couleur voulu pour
	 *                    les creatures.
	 */
	private void generateColor(ArrayList<String> couleur) {
		// if(couleur.equalsIgnoreCase("cube")){
		// config.setColor(new Cube());
		// }else if(couleur.equalsIgnoreCase("unique")){
		// config.setColor(new Unique());
		// } else if(couleur.equalsIgnoreCase("group")){
		// config.setColor(new Group());
		// }
		if (couleur == null) {
			System.out.println("Couleur des créatures non définie.");
			System.out.println("Couleur par défaut : Unique.");
			Path pathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "color" + File.separator + "Unique"
					+ ".class");
			Path pathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator + "color"
					+ File.separator + "Unique" + ".class");

			Path testPathSource = Paths.get(path + File.separator
					+ "myPluginsList" + File.separator + "color"
					+ File.separator + "Unique" + "Test" + ".class");
			Path testPathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator + "color"
					+ File.separator + "Unique" + "Test" + ".class");

			try {
				Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
				Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i < couleur.size(); i++) {
				Path pathSource = Paths.get(path + File.separator
						+ "myPluginsList" + File.separator + "color"
						+ File.separator + couleur.get(i) + ".class");
				Path pathTarget = Paths.get(path + File.separator + "myplugins"
						+ File.separator + "repository" + File.separator
						+ "color" + File.separator + couleur.get(i) + ".class");

				Path testPathSource = Paths.get(path + File.separator
						+ "myPluginsList" + File.separator + "color"
						+ File.separator + couleur.get(i) + "Test" + ".class");
				Path testPathTarget = Paths.get(path + File.separator
						+ "myplugins" + File.separator + "repository"
						+ File.separator + "color" + File.separator
						+ couleur.get(i) + "Test" + ".class");

				try {
					Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
					Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 
	 * @ArrayList<String> vitesse correspondante a la vitesse des creatures.
	 */
	public void generateVitesse(ArrayList<String> vitesse) {
		if (vitesse == null) {
			config.setVitesse(5);
			System.out.println("Vitesse non définie.");
			System.out.println("Vitesse par défaut : 5.");
		} else if (vitesse.get(0).equalsIgnoreCase("VAleatoire")) {
			config.setVitesse(-1);
		} else if (vitesse.get(0).equalsIgnoreCase("VFixe")) {
			config.setVitesse(5);
		}
	}

	/**
	 * 
	 * @ArrayList<String> direction correspondante a la direction des creatures.
	 */
	public void generateDirection(ArrayList<String> direction) {
		if (direction == null) {
			config.setDirection(0.d);
			System.out.println("Direction non définie.");
			System.out.println("Direction par défaut : Fixe.");
		} else if (direction.get(0).equalsIgnoreCase("DAleatoire")) {
			config.setDirection(-1);
		} else if (direction.get(0).equalsIgnoreCase("DFixe")) {
			config.setDirection(0.d);
		}
	}

	/**
	 * 
	 * @ArrayList<String> nombre correspondante au nombre de creatures a creer.
	 */
	public void generateNombre(ArrayList<String> nombre) {
		if (nombre == null) {
			config.setNombre(-1);
			System.out.println("Nombre de créature non définie.");
			System.out
					.println("Nombre par défaut : Selection par l'utilisateur.");
		} else if (nombre.get(0).equalsIgnoreCase("Fixe")) {
			// ConfigFile.nombre = -1;
		} else if (nombre.get(0).equalsIgnoreCase("Dizaine")) {
			Random r = new Random();
			int i1 = (r.nextInt(100 - 10) + 10);
			// ConfigFile.nombre = i1;
		} else if (nombre.get(0).equalsIgnoreCase("Centaine")) {
			Random r = new Random();
			int i1 = (r.nextInt(1000 - 100) + 100);
			// ConfigFile.nombre = i1;
		} else if (nombre.get(0).equalsIgnoreCase("Millier")) {
			Random r = new Random();
			int i1 = (r.nextInt(10000 - 1000) + 1000);
			// ConfigFile.nombre = i1;
		}
	}

	/**
	 * 
	 * @ArrayList<String> correspondante au comportement aux bords pour les
	 *                    creatures.
	 * 
	 *                    Closed : monde ferme. Toric : monde libre. Circular :
	 *                    rebons en haut et en bas de la fenetre, libre sur les
	 *                    cotes.
	 */
	private void generateEnvironnement(ArrayList<String> environnement) {
		if (environnement == null) {
			config.setEnvironnement(Toric.getInstance());
			System.out.println("Environnement non définie.");
			System.out.println("Environnement par défaut : Toric.");

			Path pathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "comportements" + File.separator
					+ "Toric" + ".class");
			Path pathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "comportements" + File.separator + "Toric" + ".class");

			Path testPathSource = Paths.get(path + File.separator
					+ "myPluginsList" + File.separator + "comportements"
					+ File.separator + "Toric" + "Test" + ".class");
			Path testPathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "comportements" + File.separator + "Toric" + "Test"
					+ ".class");

			try {
				Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
				Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (environnement.get(0).equalsIgnoreCase("toric")) {
				config.setEnvironnement(Toric.getInstance());
			} else if (environnement.get(0).equalsIgnoreCase("closed")) {
				config.setEnvironnement(Closed.getInstance());
			} else if (environnement.get(0).equalsIgnoreCase("circular")) {
				config.setEnvironnement(Circular.getInstance());
			}

			Path pathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "comportements" + File.separator
					+ environnement.get(0) + ".class");
			Path pathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "comportements" + File.separator + environnement.get(0)
					+ ".class");

			Path testPathSource = Paths
					.get(path + File.separator + "myPluginsList"
							+ File.separator + "comportements" + File.separator
							+ environnement.get(0) + "Test" + ".class");
			Path testPathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "comportements" + File.separator + environnement.get(0)
					+ "Test" + ".class");

			try {
				Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
				Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @ArrayList<String> deplacement correspondante au type de deplacement des
	 *                    creatures.
	 * 
	 *                    Stupid : deplacement dans une seule direction.
	 *                    Troupeau : deplacement en fonction des creatures
	 *                    autour d'elle. Hasard : deplacement aleatoire qui
	 *                    change a un tick donne.
	 */
	public void generateDeplacement(ArrayList<String> deplacement) {

		if (deplacement == null) {
			System.out.println("Déplacement non définie.");
			System.out.println("Déplacement par défaut : Stupid.");
			Path pathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "deplacements" + File.separator
					+ "Stupid" + ".class");
			Path pathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "deplacements" + File.separator + "Stupid" + ".class");

			Path testPathSource = Paths.get(path + File.separator
					+ "myPluginsList" + File.separator + "deplacements"
					+ File.separator + "Stupid" + "Test" + ".class");
			Path testPathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "deplacements" + File.separator + "Stupid" + "Test"
					+ ".class");

			try {
				Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
				Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i < deplacement.size(); i++) {
				Path pathSource = Paths.get(path + File.separator
						+ "myPluginsList" + File.separator + "deplacements"
						+ File.separator + deplacement.get(i) + ".class");
				Path pathTarget = Paths.get(path + File.separator + "myplugins"
						+ File.separator + "repository" + File.separator
						+ "deplacements" + File.separator + deplacement.get(i)
						+ ".class");

				Path testPathSource = Paths.get(path + File.separator
						+ "myPluginsList" + File.separator + "deplacements"
						+ File.separator + deplacement.get(i) + "Test"
						+ ".class");
				Path testPathTarget = Paths.get(path + File.separator
						+ "myplugins" + File.separator + "repository"
						+ File.separator + "deplacements" + File.separator
						+ deplacement.get(i) + "Test" + ".class");

				try {
					Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
					Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}