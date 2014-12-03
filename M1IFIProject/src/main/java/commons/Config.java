package commons;

import comportements.IComportement;
import comportements.Toric;
import creatures.IColorStrategy;

public class Config {
	private static Config config = new Config();
	private int vitesseSimu;
	private IColorStrategy couleur;
	private int vitesse;
	private double direction;
	private IComportement environnement;
	
	private Config() {
		// TODO Auto-generated constructor stub
		vitesseSimu = 0;
		vitesse = 0;
		direction = 0d;
		environnement = Toric.getInstance();
	}
	
	public static Config getInstance(){
		return config;
	}
	
	public void setVitesseSimu(int vitesseSimu){
		this.vitesseSimu = vitesseSimu;
	}
	
	public int getVitesseSimu(){
		return vitesseSimu;
	}
	
	public void setColor(IColorStrategy couleur){
		this.couleur = couleur;
	}
	
	public IColorStrategy getColor(){
		return couleur;
	}
	
	public void setVitesse(int vitesse){
		this.vitesse = vitesse;
	}
	
	public int getVitesse(){
		return vitesse;
	}
	
	public void setDirection(double direction){
		this.direction = direction;
	}
	
	public double getDirection(){
		return direction;
	}
	
	public void setEnvironnement(IComportement environnement){
		this.environnement = environnement;
	}
	
	public IComportement getEnvironnement(){
		return environnement;
	}
}