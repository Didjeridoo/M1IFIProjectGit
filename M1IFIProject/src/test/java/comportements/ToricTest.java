package comportements;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Dimension;

import org.junit.Before;
import org.junit.Test;

import creatures.visual.CreatureSimulator;

public class ToricTest {

	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;

	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
	}

	@Test
	public void testDirectLeftUp() throws Exception {
//		Creature creature = new Creature(environment, new Point2D.Double(
//				-w / 2 + 1, 0), 10, toRadians(150), Color.RED, new Closed(),
//				new Stupid());
//		creature.move();
//
//		assertEquals(toRadians(30), creature.getDirection(), 0.01);
//		assertEquals(-w / 2 + 6, creature.getPosition().getX(), 2);
//		assertEquals(-6, creature.getPosition().getY(), 2);
	}

	@Test
	public void testDirectLeftDown() throws Exception {
//		Creature creature = new Creature(environment, new Point2D.Double(
//				-w / 2 + 1, 0), 10, toRadians(210), Color.RED, new Closed(),
//				new Stupid());
//		creature.move();
//
//		assertEquals(toRadians(330), creature.getDirection(), 0.01);
//		assertEquals(-w / 2 + 6, creature.getPosition().getX(), 2);
//		assertEquals(6, creature.getPosition().getY(), 2);
	}

	@Test
	public void testDirectRightUp() throws Exception {
//		Creature creature = new Creature(environment,
//				new Point2D.Double(w / 2 - 1, 0), 10, toRadians(30), Color.RED, new Closed(), new Stupid());
//		creature.move();
//
//		assertEquals(toRadians(150), creature.getDirection(), 0.01);
//		assertEquals(w / 2 - 6, creature.getPosition().getX(), 2);
//		assertEquals(-6, creature.getPosition().getY(), 2);
	}

	@Test
	public void testDirectRightDown() throws Exception {
//		Creature creature = new Creature(environment,
//				new Point2D.Double(w / 2 - 1, 0), 10, toRadians(330), Color.RED, new Closed(), new Stupid());
//		creature.move();
//
//		assertEquals(toRadians(210), creature.getDirection(), 0.01);
//		assertEquals(w / 2 - 6, creature.getPosition().getX(), 2);
//		assertEquals(6, creature.getPosition().getY(), 2);
		
		assertTrue(true);
	}

	// etc......

	public String getName() {
		return getClass().getName();
	}
}
