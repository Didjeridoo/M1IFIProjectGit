package config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import fr.unice.deptinfo.familiar_interpreter.FMEngineException;
import fr.unice.deptinfo.familiar_interpreter.impl.FamiliarInterpreter;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.VariableAmbigousConflictException;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * Hello world!
 *
 */
public class FMLConfig
{
	public static FMLConfig fmlConfig = new FMLConfig();
	FamiliarInterpreter fi;
	String fmName;
	String configName;
	String url_file_fml;
	
	private FMLConfig()
	{
		fi = FamiliarInterpreter.getInstance();
		fmName = "fmTechno";
		configName = "config";
		url_file_fml = "doc/simulateur.fml";
	}
	
	
    public Collection<String> getFMLConfig()
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
	        } while (!s.equalsIgnoreCase("valider") || !fi.getConfigurationVariable(configName).isComplete() || !fi.getConfigurationVariable(configName).isValid());
	        scan.close();
	        return fi.getSelectedFeature(configName);
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

    
    
    public static void main(String [] args)
    {
    	FMLConfig fml = FMLConfig.getinstance();
    	
    	Collection<String> coucou = fml.getFMLConfig();
    	System.out.println(coucou);
    }
}

