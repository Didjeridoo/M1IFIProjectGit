package commons;

import creatures.IColorStrategy;

public class Config {
	private static Config config = new Config();
	private int vitesseSimu;
	private IColorStrategy couleur;
	
	private Config() {
		// TODO Auto-generated constructor stub
		vitesseSimu = 0;
	}
	
	public static Config getInstance(){
		return config;
	}
	
	public void setColor(IColorStrategy couleur){
		this.couleur = couleur;
	}
	
	public IColorStrategy getColor(){
		return couleur;
	}
}