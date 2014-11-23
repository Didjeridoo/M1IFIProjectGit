package creatures;

import static org.junit.Assert.*;

import org.junit.Test;

import plug.IPlugin;

public class StupidCreatureTest implements IPlugin{

	@Test
	public void test() {
		assertTrue(false);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}
}
