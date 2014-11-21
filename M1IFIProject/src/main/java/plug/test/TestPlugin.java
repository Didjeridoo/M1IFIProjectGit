package plug.test;

import java.util.List;



import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import plug.IPlugin;

public class TestPlugin implements IPlugin{

	JUnitCore runner;
	String resultat;
	
	private static TestPlugin testplugin = new TestPlugin();
	public static TestPlugin getInstance(){return testplugin;}
	
	private TestPlugin()
	{
		runner = new JUnitCore();
		resultat="";
	}
	
	public boolean test(Class<?> class_)
	{
		Result result = runner.run(class_);
		int nbFail = result.getFailureCount();
		
		if(nbFail == 0)
		{
			resultat += "Pas d'erreur concernant "+class_+"\n";
			return true;
		}
		else
		{
			List<Failure> testFailed = result.getFailures();
			resultat +="Erreur dans " +class_+ ":\n";

			for (Failure f : testFailed) {
				resultat +="\t - Méthode "+f.getTestHeader()+"\n";
			}
			return false;
		}
	}
	
	public String getResultTest(){return resultat;}
	
	public String getName() {
		return getClass().getName();
	}

}
