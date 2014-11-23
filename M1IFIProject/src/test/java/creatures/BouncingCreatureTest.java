package creatures;

import static java.lang.Math.toRadians;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import comportements.IComportement;

import creatures.visual.CreatureSimulator;
import deplacements.IDeplacement;

public class BouncingCreatureTest {
	
	CreatureSimulator environment = mock(CreatureSimulator.class);
	
	IDeplacement deplacement;
	IComportement comportement;
	
	final double w = 200;
	final double h = 100;
	
	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int)w, (int)h));
	}

	
	@Test
	public void testDirectLeftUp() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement,deplacement, new Point2D.Double(-w/2+1, 0), 10, toRadians(150), Color.RED);
		creature.move();
		
		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(-w/2+6, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 2);
    }	
	
	@Test
	public void testDirectLeftDown() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment, comportement, deplacement, new Point2D.Double(-w/2+1, 0), 10, toRadians(210), Color.RED);
		creature.move();
		
		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(-w/2+6, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 2);
    }	
	
	
	@Test 
	public void testDirectRightUp() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(w/2-1, 0), 10, toRadians(30), Color.RED);
		creature.move();
		
		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(w/2-6, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 2);
    }	
	
	@Test 
	public void testDirectRightDown() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(w/2-1, 0), 10, toRadians(330), Color.RED);
		creature.move();
		
		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(w/2-6, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 2);
    }	
	
	
	@Test
	public void testDirectUpRight() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(0, -h/2+1), 10, toRadians(30), Color.RED);
		creature.move();
		
		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(8, creature.getPosition().getX(), 2);
		assertEquals(-h/2+4, creature.getPosition().getY(), 2);
    }	
	
	@Test
	public void testDirectUpLeft() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(0, -h/2+1), 10, toRadians(150), Color.RED);
		creature.move();
		
		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(-8, creature.getPosition().getX(), 2);
		assertEquals(-h/2+4, creature.getPosition().getY(), 2);
    }
	
	@Test
	public void testDirectDownRight() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(0, h/2-1), 10, toRadians(330), Color.RED);
		creature.move();
		
		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(8, creature.getPosition().getX(), 2);
		assertEquals(h/2-4, creature.getPosition().getY(), 2);
    }	
	
	@Test
	public void testDirectDownLeft() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(0, h/2-1), 10, toRadians(210), Color.RED);
		creature.move();
		
		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(-8, creature.getPosition().getX(), 2);
		assertEquals(h/2-4, creature.getPosition().getY(), 2);
    }
	
	
	@Test
	public void testUpperRightCorner45() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(w/2, -h/2), 1, toRadians(45), Color.RED);
		creature.move();
		
		assertEquals(toRadians(225), creature.getDirection(), 0.01);
		assertEquals(w/2, creature.getPosition().getX(), 1);
		assertEquals(-h/2, creature.getPosition().getY(), 1);
    }	
	
	@Test
	public void testUpperRightCorner30() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(w/2, -h/2), 1, toRadians(30), Color.RED);
		creature.move();
		
		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(w/2, creature.getPosition().getX(), 1);
		assertEquals(-h/2, creature.getPosition().getY(), 1);
    }	
	
	@Test
	public void testDirectBottom() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(0, h/2), 1, toRadians(270), Color.RED);
		creature.move();

		assertEquals(toRadians(90), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 1);
		assertEquals(h/2, creature.getPosition().getY(), 1);
		
	}

	// Special case: in a corner but not really facing both sides
	@Test
	public void testSpecialCorner() throws Exception {
		BouncingCreature creature = new BouncingCreature(environment,comportement, deplacement, new Point2D.Double(w/2, h/2), 1, toRadians(210), Color.RED);
		creature.move();

		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(h/2, creature.getPosition().getY(), 1);		
	}


	public String getName() {
		return getClass().getName();
	}
}
