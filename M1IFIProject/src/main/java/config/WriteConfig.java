package config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteConfig {
	public WriteConfig(){
		
	}
	
	public void write() throws IOException{
		String path = new File("").getAbsolutePath();
		FileWriter writer = new FileWriter(path + File.separator + "src"
                + File.separator + "main" + File.separator + "java"
                + File.separator + "config" + File.separator
                + "ConfigFile.java", false);
		 writer.write("package config;\n");
         writer.write("public class ConfigFile{\n");
         writer.write("public static int nombre=13;\n");
         writer.write("}");
         writer.close();
	}
	
}
