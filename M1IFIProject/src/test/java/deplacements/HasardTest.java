package deplacements;

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
import creatures.CustomCreature;
import creatures.visual.CreatureSimulator;

public class HasardTest {

	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;
	IColorStrategy color = new Unique();

	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
	}
	
	@Test
	public void testDirectLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(180), Color.RED);
		creature.act();

		assertEquals(toRadians(180), creature.getDirection(), 0.01);
		assertEquals(-10, creature.getPosition().getX(), 2);
		assertEquals(0, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testDirectRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(0), Color.RED);
		creature.act();

		assertEquals(toRadians(0), creature.getDirection(), 0.01);
		assertEquals(10, creature.getPosition().getX(), 2);
		assertEquals(0, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testDirectTop() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(90), Color.RED);
		creature.act();

		assertEquals(toRadians(90), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 2);
		assertEquals(-10, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testDirectDown() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(270), Color.RED);
		creature.act();

		assertEquals(toRadians(270), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 2);
		assertEquals(10, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testCornerTopLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(135), Color.RED);
		creature.act();

		assertEquals(toRadians(135), creature.getDirection(), 0.01);
		assertEquals(-5, creature.getPosition().getX(), 3);
		assertEquals(-5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testCornerTopRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(45), Color.RED);
		creature.act();

		assertEquals(toRadians(45), creature.getDirection(), 0.01);
		assertEquals(5, creature.getPosition().getX(), 3);
		assertEquals(-5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testCornerDownLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(225), Color.RED);
		creature.act();

		assertEquals(toRadians(225), creature.getDirection(), 0.01);
		assertEquals(-5, creature.getPosition().getX(), 3);
		assertEquals(5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testCornerDownRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(315), Color.RED);
		creature.act();

		assertEquals(toRadians(315), creature.getDirection(), 0.01);
		assertEquals(5, creature.getPosition().getX(), 3);
		assertEquals(5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testExtremeRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				w / 2 - 1, 0), 10, toRadians(0), Color.RED);
		creature.act();

		assertEquals(toRadians(0), creature.getDirection(), 0.01);
		assertEquals(-w/2, creature.getPosition().getX(), 3);
		assertEquals(0, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testExtremeLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				-w / 2 + 1, 0), 10, toRadians(180), Color.RED);
		creature.act();

		assertEquals(toRadians(180), creature.getDirection(), 0.01);
		assertEquals(w/2, creature.getPosition().getX(), 3);
		assertEquals(0, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testExtremeTop() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, -h/2 + 1), 10, toRadians(90), Color.RED);
		creature.act();

		assertEquals(toRadians(90), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 3);
		assertEquals(h/2, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testExtremeDown() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, h/2 - 1), 10, toRadians(270), Color.RED);
		creature.act();

		assertEquals(toRadians(270), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 3);
		assertEquals(-h/2, creature.getPosition().getY(), 3);
	}

	@Test
	public void testChangeDirection() throws Exception{
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				0, 0), 10, toRadians(315), Color.RED);
		creature = mock(CustomCreature.class);
		when(creature.getCurrCycle()).thenReturn(29);
		creature.act();
		
		assertNotEquals(toRadians(315), creature.getDirection(), 0.01);
		//assertNotEquals(0, creature.getPosition().getX(), 0.01);
		//assertNotEquals(0, creature.getPosition().getY(), 0.01);
	}
}
