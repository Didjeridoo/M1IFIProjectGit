package plug.test;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import plug.IPlugin;

public class TestPlugin implements IPlugin {

	JUnitCore runner;
	String resultat;
	private static TestPlugin testPlugin = new TestPlugin();

	private TestPlugin() {
		// TODO Auto-generated constructor stub
		runner = new JUnitCore();
		resultat = "";
	}

	public static TestPlugin getInstance() {
		return testPlugin;
	}

	public boolean test(Class<?> class_)
	{
		Result result = runner.run(class_);
		int nbFail = result.getFailureCount();
		
		resultat += class_.getName() + "\n";
		resultat += "Runs: " + result.getRunCount() + "\n";
		resultat += "Failures: " + nbFail + "\n";
		resultat += "Finished after " + result.getRunTime() / 1000.f + " seconds" + "\n";
		
		if(nbFail == 0)
		{
			resultat += "----------------------\n";
			return true;
		}
		else
		{
			List<Failure> testFailed = result.getFailures();

			for (Failure f : testFailed) {
				resultat += "\nMethod failed: "+f.getTestHeader()+"\n";
				resultat += "Exception: " + f.getException() + "\n";
			}
			resultat += "----------------------\n";
			return false;
		}
	}

	public String getResultTest() {
		return resultat;
	}

	public String getName() {
		return getClass().getName();
	}

}
