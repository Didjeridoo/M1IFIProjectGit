package commons;

import comportements.IComportement;
import comportements.Toric;
import creatures.IColorStrategy;

public class Config {
	/**
	 * Instance de la classe Config. (Pattern Singleton).
	 */
	private static Config config = new Config();
	/**
	 * Execution delay in milliseconds.
	 */
	private int vitesseSimu;
	/**
	 * Strategy de couleur.
	 */
	private IColorStrategy couleur;
	/**
	 * Vitesse de la simulation.
	 */
	private int vitesse;
	/**
	 * Direction des créatures.
	 */
	private double direction;
	/**
	 * Nombre de créatures à créer.
	 */
	private int nombre;
	/**
	 * Comportement aux bords pour les créatures.
	 */
	private IComportement environnement;
	
	/**
	 * Constructeur.
	 * On initialise les variables de la classe.
	 * On part du principe que le monde est torique
	 * s'il n'est pas modifié par l'utilisateur.
	 */
	private Config() {
		// TODO Auto-generated constructor stub
		vitesseSimu = 0;
		vitesse = 0;
		direction = 0d;
		nombre = 0;
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
	
	public void setNombre(int nombre){
		this.nombre = nombre;
	}
	
	public int getNombre(){
		return nombre;
	}
	
	public void setEnvironnement(IComportement environnement){
		this.environnement = environnement;
	}
	
	public IComportement getEnvironnement(){
		return environnement;
	}
}