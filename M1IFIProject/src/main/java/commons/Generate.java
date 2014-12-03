package commons;

import comportements.Circular;
import comportements.Closed;
import comportements.Toric;
import creatures.visual.ColorCube;

public class Generate {
	private Config config;
	private String[] features;
	private static Generate instance = new Generate(new String[] {"moyen", "cube", "VAleatoire", "DAleatoire", "Ferme"});

	private Generate(String[] args) {
		// TODO Auto-generated constructor stub
		config = Config.getInstance();
		features = args;
	}
	
	public static Generate getInstance(){
		return instance;
	}
	
	public void generateConfig() {
		generateMoteur(features[0]);
		generateColor(features[1]);
		generateVitesse(features[2]);
		generateDirection(features[3]);
		generateEnvironnement(features[4]);
	}

	public void generateMoteur(String vitesse) {
		generateVitesseSimu(vitesse);
	}

	private void generateVitesseSimu(String vitesseSimu) {
		// TODO Auto-generated method stub
		if(vitesseSimu.equalsIgnoreCase("lent")){
			config.setVitesseSimu(20);
		} else if(vitesseSimu.equalsIgnoreCase("moyen")){
			config.setVitesseSimu(10);
		} else if(vitesseSimu.equalsIgnoreCase("rapide")){
			config.setVitesseSimu(5);
		}
	}
	
	private void generateColor(String couleur){
		if(couleur.equalsIgnoreCase("cube")){
			config.setColor(new ColorCube(50)); 
		} /*else if(couleur.equalsIgnoreCase("unique")){
			config.setColor(new Unique());
		} else if(couleur.equalsIgnoreCase("group")){
			config.setColor(new Group());
		}*/
	}
	
	public void generateVitesse(String vitesse){
		if(vitesse.equalsIgnoreCase("VAleatoire")){
			config.setVitesse(-1);
		} else if(vitesse.equalsIgnoreCase("VFixe")){
			config.setVitesse(5);
		}
	}
	
	public void generateDirection(String direction){
		if(direction.equalsIgnoreCase("DAleatoire")){
			config.setDirection(-1);
		} else if(direction.equalsIgnoreCase("DFixe")){
			config.setDirection(0.d);
		}
	}
	
	private void generateEnvironnement(String environnement) {
		// TODO Auto-generated method stub
		if(environnement.equalsIgnoreCase("torique")){
			config.setEnvironnement(Toric.getInstance());
		} else if(environnement.equalsIgnoreCase("ferme")){
			config.setEnvironnement(Closed.getInstance());
		} else if(environnement.equalsIgnoreCase("monde")){
			config.setEnvironnement(Circular.getInstance());
		}
	}
}