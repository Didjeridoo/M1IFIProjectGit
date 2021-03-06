package creatures;

import static java.lang.Math.toRadians;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import color.Unique;
import color.IColorStrategy;
import comportements.Toric;
import creatures.visual.CreatureSimulator;
import deplacements.Stupid;
import plug.IPlugin;

public class StupidCreatureTest implements IPlugin{

	
	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;
	IColorStrategy color = new Unique();

	
	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
	}

	@Test
	public void testDirectLeftUp() throws Exception {
		StupidCreature creature = new StupidCreature(environment, Toric.getInstance(),
				new Stupid(), new Point2D.Double(
				-w / 2 + 1, 0), 10, toRadians(150), Color.RED);
		creature.act();

		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(w / 2, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 3);
	}

	@Test
	public void testDirectLeftDown() throws Exception {
		StupidCreature creature = new StupidCreature(environment, Toric.getInstance(),
				new Stupid(), new Point2D.Double(
				-w / 2 + 1, 0), 10, toRadians(210), Color.RED);
		creature.act();

		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(w / 2, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 3);
	}

	@Test
	public void testDirectRightUp() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),
		new Stupid(), new Point2D.Double(w / 2 - 1, 0), 10, toRadians(30), Color.RED);
		creature.act();

		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(-w / 2, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 3);
	}

	@Test
	public void testDirectRightDown() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),
		new Stupid(), new Point2D.Double(w / 2 - 1, 0), 10, toRadians(330), Color.RED);
		creature.act();

		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(-w / 2, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 3);
	}

	@Test
	public void testDirectDownLeft() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),
		new Stupid(), new Point2D.Double(0, h / 2 - 1), 10, toRadians(210), Color.RED);
		creature.act();

		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(h / 2 - 5, creature.getPosition().getY(), 2);
		assertEquals(-6, creature.getPosition().getX(), 3);
	}
	
	@Test
	public void testDirectDownRight() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),
		new Stupid(), new Point2D.Double(0, h / 2 - 1), 10, toRadians(330), Color.RED);
		creature.act();

		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(h / 2 - 5, creature.getPosition().getY(), 2);
		assertEquals(6, creature.getPosition().getX(), 3);
	}
	
	@Test
	public void testDirectTopLeft() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),
		new Stupid(), new Point2D.Double(0, -h / 2 + 1), 10, toRadians(150), Color.RED);
		creature.act();

		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(-h / 2 + 5, creature.getPosition().getY(), 2);
		assertEquals(-6, creature.getPosition().getX(), 3);
	}
	
	@Test
	public void testDirectTopRight() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),
		new Stupid(), new Point2D.Double(0, -h / 2 + 1), 10, toRadians(30), Color.RED);
		creature.act();

		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(-h / 2 + 5, creature.getPosition().getY(), 2);
		assertEquals(6, creature.getPosition().getX(), 3);
	}

	@Test
	public void testUpperRightCorner45() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),new Stupid(), new Point2D.Double(w/2, -h/2), 10, toRadians(45), Color.RED);
		creature.act();
		
		assertEquals(toRadians(45), creature.getDirection(), 0.01);
		assertEquals(-w/2, creature.getPosition().getX(), 1);
		assertEquals(-h/2, creature.getPosition().getY(), 10);
    }	
	
	@Test
	public void testUpperRightCorner30() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),new Stupid(), new Point2D.Double(w/2, -h/2), 10, toRadians(30), Color.RED);
		creature.act();
		
		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(-w/2, creature.getPosition().getX(), 1);
		assertEquals(-h/2, creature.getPosition().getY(), 10);
    }	
	
	@Test
	public void testDirectBottom() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),new Stupid(), new Point2D.Double(0, h/2), 10, toRadians(270), Color.RED);
		creature.act();

		assertEquals(toRadians(270), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 1);
		assertEquals(h/2 - 10, creature.getPosition().getY(), 1);
		
	}
	
	@Test
	public void testDirectTop() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),new Stupid(), new Point2D.Double(0, -h/2), 10, toRadians(90), Color.RED);
		creature.act();

		assertEquals(toRadians(90), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 1);
		assertEquals(-h/2 +  10, creature.getPosition().getY(), 1);
		
	}

	// Special case: in a corner but not really facing both sides
	@Test
	public void testSpecialCorner() throws Exception {
		StupidCreature creature = new StupidCreature(environment,Toric.getInstance(),new Stupid(), new Point2D.Double(w/2, h/2), 1, toRadians(210), Color.RED);
		creature.act();

		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(h/2, creature.getPosition().getY(), 1);		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}
}
