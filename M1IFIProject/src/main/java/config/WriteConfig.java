package config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class WriteConfig {
	private HashMap<String, ArrayList<String>> sendConfig;
	public WriteConfig(HashMap<String, ArrayList<String>> sendConfig){
		this.sendConfig = sendConfig;
	}
	
	public void write() throws IOException{
		String path = new File("").getAbsolutePath();
		FileWriter writer = new FileWriter(path + File.separator + "src"
                + File.separator + "main" + File.separator + "java"
                + File.separator + "config" + File.separator
                + "ConfigFile.java", false);
		 writer.write("package config;");
         writer.write("public class ConfigFile{");
         writer.write("public static int nombre=" + sendConfig.get("Nombre").get(0) + ";");
         
         writer.write("}");
         writer.close();
	}
	
}
