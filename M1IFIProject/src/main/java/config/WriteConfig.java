package config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import comportements.IComportement;

public class WriteConfig {
	private HashMap<String, String> sendConfig;
	public WriteConfig(HashMap<String, String> sendConfig){
		this.sendConfig = sendConfig;
	}
	
	public void write() throws IOException{
		String path = new File("").getAbsolutePath();
		FileWriter writer = new FileWriter(path + File.separator + "src"
                + File.separator + "main" + File.separator + "java"
                + File.separator + "config" + File.separator
                + "ConfigFile.java", false);
		 writer.write("package config;");
		 writer.write("import comportements.*;");
         writer.write("public class ConfigFile{");
         writer.write("public static int nombre=" + sendConfig.get("Nombre") + ";");
         writer.write("public static int vitesseSimu=" + sendConfig.get("VitesseSimu") + ";");
         writer.write("public static double vitesse=" + sendConfig.get("Vitesse") + ";");
         writer.write("public static double direction=" + sendConfig.get("Direction") + ";");
         writer.write("public static IComportement environnement =" + sendConfig.get("Comportement") + ";");
         writer.write("}");
         writer.close();
	}
	
}
