package fr.unice.deptinfo.maven_compiler;

import java.io.File;

public class Launcher {

	public static void main(String[] args) {
		MavenCompiler mv = new MavenCompiler();
		String path = new File("").getAbsolutePath();
		File srcDir = new File(path + File.separator + ".." + File.separator + "M1IFIProject" + File.separator);
		File targetDir = new File(path + File.separator + ".." );
		mv.compile(srcDir, targetDir);

	}

}
