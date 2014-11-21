package plug;

import java.util.List;

import javax.swing.JFrame;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;

public class PluginTest implements IPlugin{

	JUnitCore runner;
	String resultat;
	
	public String getStringTestResult(Class class_)
	{
		runner = new JUnitCore();

		Result result = runner.run(class_);
		int nbFail = result.getFailureCount();
		
		if(nbFail == 0)
		{
			resultat = "Pas d'erreur concernant la "+class_;
		}
		else
		{
			List<Failure> testFailed = result.getFailures();
			
			for (Failure f : testFailed) {
				resultat +="Erreur dans la classe " +class_+ ":";
				resultat +="\n\t - Méthode "+f.getTestHeader();
			}
		}
		return resultat;
	}
	
	public String getName() {
		return getClass().getName();
	}

}
