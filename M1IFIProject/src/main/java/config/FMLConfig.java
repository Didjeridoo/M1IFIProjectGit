package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import fr.unice.deptinfo.familiar_interpreter.FMEngineException;
import fr.unice.deptinfo.familiar_interpreter.impl.FamiliarInterpreter;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.VariableAmbigousConflictException;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class FMLConfig
{
	public static FMLConfig fmlConfig = new FMLConfig();
	FamiliarInterpreter fi;
	String fmName;
	String configName;
	String url_file_fml;
	private static Generate generate;
	HashMap<String,ArrayList<String> > hashMap;
	ArrayList<String> params;
	ArrayList<String> deplacements;
	ArrayList<String> colors;
	private String path;
	private static File fileColor;
	private static File fileComportements;
	private static File fileDeplacements;
	
	private FMLConfig()
	{
		fi = FamiliarInterpreter.getInstance();
		fmName = "fmTechno";
		configName = "config";
		url_file_fml = "doc/simulateur.fml";
		hashMap = new HashMap<String, ArrayList<String>>();
		deplacements = new ArrayList<String>();
		params = new ArrayList<String>();
		colors = new ArrayList<String>();
		path = new File("").getAbsolutePath();
		fileColor = new File(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator + "color");
		fileComportements = new File(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator + "comportements");
		fileDeplacements = new File(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator + "deplacements");
	}
	
	public static void deleteFiles(){
		String[] myFiles;
    	if(fileColor.isDirectory()){
    		if(fileColor.list().length>0){
    			myFiles = fileColor.list();
    			for(int i = 0; i<myFiles.length; i++){
    				File myFile = new File(fileColor, myFiles[i]);
    				myFile.delete();
    			}
    		}
    	}
    	if(fileComportements.isDirectory()){
    		if(fileComportements.list().length>0){
    			myFiles = fileComportements.list();
    			for(int i = 0; i<myFiles.length; i++){
    				File myFile = new File(fileComportements, myFiles[i]);
    				myFile.delete();
    			}
    		}
    	}
    	if(fileDeplacements.isDirectory()){
    		if(fileDeplacements.list().length>0){
    			myFiles = fileDeplacements.list();
    			for(int i = 0; i<myFiles.length; i++){
    				File myFile = new File(fileDeplacements, myFiles[i]);
    				myFile.delete();
    			}
    		}
    	}
	}
	
    public HashMap<String,ArrayList<String>> getFMLConfig()
    {
    	try {
    		
			try {
				//Récupération du fichier fml
				fi.evalFile(url_file_fml);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//récupération du FMLTechno
			FeatureModelVariable fmv = fi.getFMVariable(fmName);
	    	
	    	System.out.println("Instancied FM : "+fmv.getSyntacticalRepresentation());
	    	
	    	fi.eval(configName+" = configuration "+fmName);
	    	
	    	
	        Scanner scan = new Scanner(System.in);
	        String s = "";
	        String selectCmd = "select ";
	        
	        do {
	        	System.out.println("Enter the name of features you wish to select, or type exit to exit.");
	        	s = scan.nextLine();
	        	if(s.equalsIgnoreCase("exit"))
	        	{
	        		scan.close();
	        		return null;
	        	}
	        	if (!s.equalsIgnoreCase("valider")) {
		        	fi.eval(selectCmd+s+" in "+configName);
		        	System.out.println("Selected features :"+fi.getSelectedFeature(configName));
		        	System.out.println("Deselected features :"+fi.getDeselectedFeature(configName));
		        	System.out.println("Unselected features :"+fi.getUnselectedFeature(configName));
		        	System.out.println("The configuration is complete : "+fi.getConfigurationVariable(configName).isComplete());
	        	}
	        } while (!s.equalsIgnoreCase("valider") || !fi.getConfigurationVariable(configName).isValid());
	        scan.close();
	        
	        Collection<String> fmList = fi.getSelectedFeature(configName);
	        for(String feature : fmList)
	        {
	        	if(feature.equalsIgnoreCase("Couleur")|| feature.equalsIgnoreCase("Nombre")||
	        		feature.equalsIgnoreCase("Vitesse")||feature.equalsIgnoreCase("Environnement")||
	        		feature.equalsIgnoreCase("Deplacement")||feature.equalsIgnoreCase("VitesseSimu")||
	        		feature.equalsIgnoreCase("Direction"))
	        	{
	        		continue;
	        	}
	        	else
	        	{
	        		if(feature.equalsIgnoreCase("Unique")||
	        				feature.equalsIgnoreCase("Cube")||
	        				feature.equalsIgnoreCase("Groupe"))
	        		{
	        			colors.add(feature);
	        		}
	        		else if(feature.equalsIgnoreCase("Fixe")||
	        				feature.equalsIgnoreCase("Dizaine")||
	        				feature.equalsIgnoreCase("Centaine")||
	        				feature.equalsIgnoreCase("Milliers"))
	        		{
	        			params = new ArrayList<String>();
	        			params.add(feature);
	        			hashMap.put("Nombre",params);
	        		}
	        		else if(feature.equalsIgnoreCase("DFixe")||
	        				feature.equalsIgnoreCase("DAleatoire"))
	        		{
	        			params = new ArrayList<String>();
	        			params.add(feature);
	        			hashMap.put("Direction", params);
	        		}
	        		else if (feature.equalsIgnoreCase("VFixe")||
	        				feature.equalsIgnoreCase("VAleatoire"))
	        		{
	        			params = new ArrayList<String>();
	        			params.add(feature);
	        			hashMap.put("Vitesse", params);
	        		}
	        		else if(feature.equalsIgnoreCase("Circular")||
	        				feature.equalsIgnoreCase("Toric")||
	        				feature.equalsIgnoreCase("Closed"))
	        		{
	        			params = new ArrayList<String>();
	        			params.add(feature);
	        			hashMap.put("Environnement", params);
	        		}
	        		else if(feature.equalsIgnoreCase("Hasard")||
	        				feature.equalsIgnoreCase("Troupeau")||
	        				feature.equalsIgnoreCase("Stupid"))
	        		{
	        			deplacements.add(feature);
	        		}
	        		else if(feature.equalsIgnoreCase("Lent")||
	        				feature.equalsIgnoreCase("Normal")||
	        				feature.equalsIgnoreCase("Rapide"))
	        		{
	        			params = new ArrayList<String>();
	        			params.add(feature);
	        			hashMap.put("VitesseSimu", params);
	        		}
	        	}
	        }
	        hashMap.put("Couleur", colors);
	        hashMap.put("Deplacement", deplacements);
	        return hashMap;
		} catch (FMEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VariableNotExistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VariableAmbigousConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    public static FMLConfig getinstance(){return fmlConfig;}
    
    public static void main(String []args){
    	deleteFiles();
    	FMLConfig fml = FMLConfig.getinstance();
    	
    	HashMap<String, ArrayList<String>> configFML = fml.getFMLConfig();
    	
    	System.out.println(configFML);
    	generate = new Generate(configFML);
    	generate.generateConfig();
    }
}
