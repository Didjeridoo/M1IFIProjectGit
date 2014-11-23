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

import comportements.Toric;
import creatures.CustomCreature;
import creatures.ICreature;
import creatures.visual.CreatureSimulator;

public class TroupeauTest {

	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;
	//Troupeau troup = mock(Troupeau.class);

	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
		/*when(troup.creaturesAround()).thenReturn(new Iterable<ICreature>(new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(180), Color.RED)));*/
	}
	
	@Test
	public void testDirectLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(180), Color.RED);
		creature.act();

		assertEquals(toRadians(180), creature.getDirection(), 0.01);
		assertEquals(-10, creature.getPosition().getX(), 2);
		assertEquals(0, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testDirectRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(0), Color.RED);
		creature.act();

		assertEquals(toRadians(0), creature.getDirection(), 0.01);
		assertEquals(10, creature.getPosition().getX(), 2);
		assertEquals(0, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testDirectTop() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(90), Color.RED);
		creature.act();

		assertEquals(toRadians(90), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 2);
		assertEquals(-10, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testDirectDown() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(270), Color.RED);
		creature.act();

		assertEquals(toRadians(270), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 2);
		assertEquals(10, creature.getPosition().getY(), 2);
	}
	
	@Test
	public void testCornerTopLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(135), Color.RED);
		creature.act();

		assertEquals(toRadians(135), creature.getDirection(), 0.01);
		assertEquals(-5, creature.getPosition().getX(), 3);
		assertEquals(-5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testCornerTopRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(45), Color.RED);
		creature.act();

		assertEquals(toRadians(45), creature.getDirection(), 0.01);
		assertEquals(5, creature.getPosition().getX(), 3);
		assertEquals(-5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testCornerDownLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(225), Color.RED);
		creature.act();

		assertEquals(toRadians(225), creature.getDirection(), 0.01);
		assertEquals(-5, creature.getPosition().getX(), 3);
		assertEquals(5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testCornerDownRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(315), Color.RED);
		creature.act();

		assertEquals(toRadians(315), creature.getDirection(), 0.01);
		assertEquals(5, creature.getPosition().getX(), 3);
		assertEquals(5, creature.getPosition().getY(), 3);
	}
}
