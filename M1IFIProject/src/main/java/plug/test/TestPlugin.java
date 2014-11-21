package plug.test;

import java.util.List;



import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import plug.IPlugin;

public class TestPlugin implements IPlugin{

	JUnitCore runner;
	String resultat;
	
	public void test(Class<?> class_)
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
			resultat +="Erreur dans la classe " +class_+ ":";

			for (Failure f : testFailed) {
				resultat +="\n\t - Méthode "+f.getTestHeader();
			}
		}
	}
	
	public String getResultTest(){return resultat;}
	
	public String getName() {
		return getClass().getName();
	}

}
