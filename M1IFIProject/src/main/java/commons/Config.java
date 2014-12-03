package commons;

import creatures.IColorStrategy;

public class Config {
	private static Config config = new Config();
	private int vitesseSimu;
	private IColorStrategy couleur;
	private int vitesse;
	private double direction;
	private int nombre;
	
	private Config() {
		// TODO Auto-generated constructor stub
		vitesseSimu = 0;
		vitesse = 0;
		direction = 0d;
		nombre = 0;
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
	
	public void setNombre(int nombre){
		this.nombre = nombre;
	}
	
	public int getNombre(){
		return nombre;
	}
}