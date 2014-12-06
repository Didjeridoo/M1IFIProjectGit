package fr.unice.deptinfo.maven_compiler;

import java.io.File;

public interface Constants 
{
	String path = new File("").getAbsolutePath();
	
	public static final String DEFAULT_MAVEN_HOME=path + File.separator + ".." + File.separator + "maven3"; 
	
	
	public static final String MAVEN_HOME_PROPERTY="mavenHome";
	
	public static final String DEFAULT_MAVEN_REPOSITORY="http://nyx.unice.fr:9007/nexus/content/repositories/i3s-public"; 
	
	public static final String MAVEN_REPOSITORY_PROPERTY="mavenRepository";

	public static final String MAVEN_REPOSITORY_ID_PROPERTY="mavenRepositoryID";
	
	public static final String DEFAULT_MAVEN_REPOSITORY_ID="releases"; 

	public static final String REQUEST_TIMEOUT_DEFAULT_VALUE="600000"; 


}
