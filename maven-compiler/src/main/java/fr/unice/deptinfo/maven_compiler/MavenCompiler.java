package fr.unice.deptinfo.maven_compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class MavenCompiler {
	
	private final static String POM_FILE="pom.xml";
	
	private final static String WAR_GOAL="war:war"; 
	
	private final static String CLEAN_GOAL="clean";
	
	private final static String INSTALL_GOAL="install";
	
	private final static String RUN_GOAL="-Prun";
	
	private final static String TARGET_DIRECTORY="target"; 
	
	private final static String DEFAULT_LAST_COMPILATION_EXECUTION_ERROR_MESSAGE="There was a problem with the maven compilation. The providers distribution was not generated"; 
	
	private final static String DEFAULT_LAST_COMPILATION_EXECUTION_OK_MESSAGE="The Maven compilation was successful. The providers distribution was generated"; 
	
	private static Logger logger= Logger.getLogger(MavenCompiler.class);
	
	private String mavenHome = Constants.DEFAULT_MAVEN_HOME;
	
	private List<String> goals; 
	
	private boolean lastCompilationSuccessful= false;  
	
	private String lastCompilationExecutionMessage= null; 
	
	
	public MavenCompiler()
	{
		initialize(); 
	}
	
	
	protected void initialize()
	{
		if(System.getProperty(Constants.MAVEN_HOME_PROPERTY)!=null)
		{
			logger.debug("MavenCompiler- Setting the maven home "+ System.getProperty(Constants.MAVEN_HOME_PROPERTY)); 
			mavenHome= System.getProperty(Constants.MAVEN_HOME_PROPERTY); 
			
		}
		
		goals= new ArrayList<String>(); 
		
		goals.add(CLEAN_GOAL); 
		goals.add(INSTALL_GOAL);
	}
	
	public void addGoalRun() {
		goals.add(RUN_GOAL);
	}
	
	
	public void compile(File srcDir, File targetDir) 
	{
		
		lastCompilationSuccessful= false; 
		
		lastCompilationExecutionMessage= DEFAULT_LAST_COMPILATION_EXECUTION_ERROR_MESSAGE; 
		
		logger.debug("MavenCompiler- Setting the maven request"); 
		InvocationRequest request = new DefaultInvocationRequest();
		
		request.setPomFile( new File(srcDir,POM_FILE) );
		
		request.setGoals(goals); 

		Invoker invoker = new DefaultInvoker();
		
		
		logger.debug("MavenCompiler- Invoking the request"); 
		File mavenHomeFile= new File(mavenHome); 
		
		if(mavenHomeFile.isDirectory())
		{
			invoker.setMavenHome(mavenHomeFile); 
			try {
				InvocationResult result =invoker.execute( request );
				
				if(result.getExitCode()!=0)
				{
					if(result.getExecutionException()!=null)
						lastCompilationExecutionMessage= "There was a problem with the maven compilation:\n"+result.getExecutionException().getMessage(); 
				}
				else
				{
					lastCompilationSuccessful= true; 
					
					lastCompilationExecutionMessage= DEFAULT_LAST_COMPILATION_EXECUTION_OK_MESSAGE; 
				}

				File generatedDir= new File(srcDir, TARGET_DIRECTORY); 
				
				if(generatedDir.exists())
				{
					File[] files= generatedDir.listFiles(); 
					
					for(File f:files)
					{
						if(f.isFile() && f.getName().endsWith(".war"))
							FileTool.copy(f, new File(targetDir, f.getName()));
					}	
				}
			} catch (MavenInvocationException e) {
				
				logger.error("MavenCompiler- Exception:\n"+e.getMessage()); 
			} catch (IOException e) {
				
				logger.error("MavenCompiler- Exception:\n"+e.getMessage()); 
			}
		
		logger.debug("MavenCompiler- request finished"); 
		}
		else
			logger.error("MavenCompiler- The maven home "+mavenHome+" does not exist. The providers will be compiled and packaged!"); 
	}


	public boolean isLastCompilationSucessful() {
		
		return lastCompilationSuccessful;
	}


	public String getLastCompilationMessage() {
		
		return lastCompilationExecutionMessage;
	}

}
