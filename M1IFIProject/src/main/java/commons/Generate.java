package commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import creatures.visual.ColorCube;

public class Generate {
	private Config config;
	private String[] features;
	private String path;
	private BufferedReader bufferedReader;
	private int vitesseSimu;
	private static Generate instance = new Generate(new String[] {"rapide", "cube"});

	private Generate(String[] args) {
		// TODO Auto-generated constructor stub
		config = Config.getInstance();
		features = args;
		bufferedReader = null;
		path = new File("").getAbsolutePath();
	}
	
	public static Generate getInstance(){
		return instance;
	}
	
	public void generateConfig() {
		generateMoteur(features[0]);
		generateColor(features[1]);
	}

	/**
	 * 
	 * @param vitesse
	 */
	public void generateMoteur(String vitesse) {
		// ecrit un fichier Moteur.java
		generateVitesseSimu(vitesse);
	}

	private void generateVitesseSimu(String vitesseSimu) {
		// TODO Auto-generated method stub
		try {

			bufferedReader = new BufferedReader(new FileReader(path + File.separator + "featuresConfig" + File.separator + "vitesseSimu" + File.separator + vitesseSimu + ".txt"));

			String line = bufferedReader.readLine();
			this.vitesseSimu = Integer.parseInt(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public int getVitesseSimu(){
		return vitesseSimu;
	}
	
	private void generateColor(String couleur){
		if(couleur.equalsIgnoreCase("cube")){
			config.setColor(new ColorCube(50)); 
		}
	}
}