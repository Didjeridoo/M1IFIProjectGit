package config;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import comportements.Circular;
import comportements.Closed;
import comportements.Toric;

/**
 * Permet de gï¿½nerer les plugins, et donc construire un produit ï¿½ partir de
 * la configuration entrï¿½e par l'utilisateur.
 * 
 * classe singleton
 * 
 * @author Marc
 *
 */
public class Generate {
	private Config config;
	//private String[] features;
	private String path;
	HashMap<String,ArrayList<String> > collecConfig;
	/*private static Generate instance = new Generate(new String[] { "moyen",
			"Unique", "VAleatoire", "DAleatoire", "Circular", "CustomCreature",
			"Troupeau"});*/

	public Generate(HashMap<String,ArrayList<String> > fml) {
		// TODO Auto-generated constructor stub
		System.out.println("Bueno");
		config = Config.getInstance();
		collecConfig = fml;
		//features = args;
		path = new File("").getAbsolutePath();
	}

	/**
	 * 
	 * @return l'instance de la classe Generate
	 */
	/*public static Generate getInstance() {
		return instance;
	}*/

	/**
	 * Gï¿½nï¿½re les plugins demandï¿½s par l'utilisateur
	 */
	public void generateConfig() {
		if(collecConfig != null){
			generateMoteur(collecConfig.get("VitesseSimu"));
			generateColor(collecConfig.get("Couleur"));
			generateVitesse(collecConfig.get("Vitesse"));
			generateDirection(collecConfig.get("Direction"));
			generateEnvironnement(collecConfig.get("Environnement"));
			//generateCreature(collecConfig.get("Creature"));
			generateDeplacement(collecConfig.get("Deplacement"));
			generateNombre(collecConfig.get("Nombre"));
		}else{
			System.out.println("Erreur : Vous avez quitté la configuration du simulateur.");
		}
		
	}

	public void generateMoteur(ArrayList<String> vitesse) {
		generateVitesseSimu(vitesse);
	}

	/**
	 * 
	 * @String vitesseSimu correspondante ï¿½ la vitesse d'execution de la
	 *         simulation.
	 * 
	 *         lent : Execution delay in milliseconds 20ms moyen : Execution
	 *         delay in milliseconds 10ms rapide : Execution delay in
	 *         milliseconds 5ms
	 * 
	 */
	private void generateVitesseSimu(ArrayList<String> vitesseSimu) {
		// TODO Auto-generated method stub
		if (vitesseSimu.get(0).equalsIgnoreCase("lent")) {
			config.setVitesseSimu(20);
		} else if (vitesseSimu.get(0).equalsIgnoreCase("moyen")) {
			config.setVitesseSimu(10);
		} else if (vitesseSimu.get(0).equalsIgnoreCase("rapide")) {
			config.setVitesseSimu(5);
		}
	}

	/**
	 * 
	 * @String couleur correspondante au plugin de couleur voulu pour les
	 *         crï¿½atures.
	 */
	private void generateColor(ArrayList<String> couleur) {
		// if(couleur.equalsIgnoreCase("cube")){
		// config.setColor(new Cube());
		// }else if(couleur.equalsIgnoreCase("unique")){
		// config.setColor(new Unique());
		// } else if(couleur.equalsIgnoreCase("group")){
		// config.setColor(new Group());
		// }
		for(int i = 0; i < couleur.size(); i++){
			Path pathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "color" + File.separator + couleur.get(i)
					+ ".class");
			Path pathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator + "color"
					+ File.separator + couleur + ".class");

			Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "color" + File.separator + couleur.get(i) + "Test"
					+ ".class");
			Path testPathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator + "color"
					+ File.separator + couleur.get(i) + "Test" + ".class");

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
	 * @String vitesse correspondante ï¿½ la vitesse des crï¿½atures.
	 */
	public void generateVitesse(ArrayList<String> vitesse) {
		if (vitesse.get(0).equalsIgnoreCase("VAleatoire")) {
			config.setVitesse(-1);
		} else if (vitesse.get(0).equalsIgnoreCase("VFixe")) {
			config.setVitesse(5);
		}
	}

	/**
	 * 
	 * @String direction correspondante ï¿½ la direction des crï¿½atures.
	 */
	public void generateDirection(ArrayList<String> direction) {
		if (direction.get(0).equalsIgnoreCase("DAleatoire")) {
			config.setDirection(-1);
		} else if (direction.get(0).equalsIgnoreCase("DFixe")) {
			config.setDirection(0.d);
		}
	}

	/**
	 * 
	 * @String nombre correspondante au nombre de crï¿½atures ï¿½ crï¿½er.
	 */
	public void generateNombre(ArrayList<String> nombre) {
		if (nombre.get(0).equalsIgnoreCase("Fixe")) {
			config.setNombre(-1);
		} else if (nombre.get(0).equalsIgnoreCase("Dizaine")) {
			Random r = new Random();
			int i1 = (r.nextInt(100 - 10) + 10);
			config.setNombre(i1);
		} else if (nombre.get(0).equalsIgnoreCase("Centaine")) {
			Random r = new Random();
			int i1 = (r.nextInt(1000 - 100) + 100);
			config.setNombre(i1);
		} else if (nombre.get(0).equalsIgnoreCase("Millier")) {
			Random r = new Random();
			int i1 = (r.nextInt(10000 - 1000) + 1000);
			config.setNombre(i1);
		}
	}

	/**
	 * 
	 * @String environnement correspondante au comportement aux bords pour les
	 *         crï¿½atures.
	 * 
	 *         Closed : monde fermï¿½ Toric : monde libre Circular : rebonds en
	 *         haut et en bas de la fenï¿½tre, libre sur les cotï¿½s.
	 */
	private void generateEnvironnement(ArrayList<String> environnement) {

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
				+ "comportements" + File.separator + environnement.get(0) + ".class");

		Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "comportements" + File.separator
				+ environnement.get(0) + "Test" + ".class");
		Path testPathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "comportements" + File.separator + environnement.get(0) + "Test"
				+ ".class");

		try {
			Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
			Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @String creature correspondante aux types de crï¿½atures que
	 *         l'utilisateur souhaite pouvoir selectionner.
	 */
	/*private void generateCreature(ArrayList<String> creature) {

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
	}*/

	/**
	 * 
	 * @String deplacement correspondante au type de dï¿½placement des
	 *         crï¿½atures.
	 * 
	 *         Stupid : dï¿½placement dans une seule direction. Troupeau :
	 *         dï¿½placement en fonction des crï¿½atures autour d'elle. Hasard :
	 *         dï¿½placement alï¿½atoire qui change ï¿½ un tick donnï¿½.
	 */
	public void generateDeplacement(ArrayList<String> deplacement) {

		for(int i = 0; i < deplacement.size(); i++){
			Path pathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "deplacements" + File.separator
					+ deplacement.get(i) + ".class");
			Path pathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "deplacements" + File.separator + deplacement.get(i) + ".class");

			Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
					+ File.separator + "deplacements" + File.separator
					+ deplacement.get(i) + "Test" + ".class");
			Path testPathTarget = Paths.get(path + File.separator + "myplugins"
					+ File.separator + "repository" + File.separator
					+ "deplacements" + File.separator + deplacement.get(i) + "Test"
					+ ".class");

			try {
				Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
				Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}