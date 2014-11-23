package creatures;

import static java.lang.Math.toRadians;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import comportements.Closed;
import comportements.Toric;
import creatures.visual.CreatureSimulator;
import deplacements.Stupid;
import deplacements.Troupeau;

public class SmartCreatureTest {

CreatureSimulator environment = mock(CreatureSimulator.class);
final double w = 200;
final double h = 100;
	CustomCreature cuscrea1;
	CustomCreature cuscrea2;
	CustomCreature cuscrea3;
	CustomCreature cuscrea4;
	CustomCreature cuscrea5;
	
	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int)w, (int)h));
		cuscrea1 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 3), 10, toRadians(0), Color.RED);
		cuscrea2 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 10), 10, toRadians(20), Color.RED);
		cuscrea3 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 25), 10, toRadians(56), Color.RED);
		cuscrea4 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 45), 10, toRadians(95), Color.RED);
		cuscrea5 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 35), 10, toRadians(180), Color.RED);
	}

	@Test
	public void testDirectLeftUp() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(-w/2+1, 0), 10, toRadians(150), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(-w/2+6, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 2);
    }	
	
	@Test
	public void testDirectLeftDown() throws Exception {
		SmartCreature creature = new SmartCreature(environment, Closed.getInstance(),new Troupeau(), new Point2D.Double(-w/2+1, 0), 10, toRadians(210), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(-w/2+6, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 2);
    }	
	
	
	@Test 
	public void testDirectRightUp() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(w/2-1, 0), 10, toRadians(30), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(w/2-6, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 2);
    }	
	
	@Test 
	public void testDirectRightDown() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(w/2-1, 0), 10, toRadians(330), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(w/2-6, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 2);
    }	
	
	
	@Test
	public void testDirectUpRight() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(0, -h/2+1), 10, toRadians(30), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(8, creature.getPosition().getX(), 2);
		assertEquals(-h/2+4, creature.getPosition().getY(), 2);
    }	
	
	@Test
	public void testDirectUpLeft() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(0, -h/2+1), 10, toRadians(150), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(-8, creature.getPosition().getX(), 2);
		assertEquals(-h/2+4, creature.getPosition().getY(), 2);
    }
	
	@Test
	public void testDirectDownRight() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(0, h/2-1), 10, toRadians(330), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(8, creature.getPosition().getX(), 2);
		assertEquals(h/2-4, creature.getPosition().getY(), 2);
    }	
	
	@Test
	public void testDirectDownLeft() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(0, h/2-1), 10, toRadians(210), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(-8, creature.getPosition().getX(), 2);
		assertEquals(h/2-4, creature.getPosition().getY(), 2);
    }
	
	
	@Test
	public void testUpperRightCorner45() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(w/2, -h/2), 1, toRadians(45), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(225), creature.getDirection(), 0.01);
		assertEquals(w/2, creature.getPosition().getX(), 1);
		assertEquals(-h/2, creature.getPosition().getY(), 1);
    }	
	
	@Test
	public void testUpperRightCorner30() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(w/2, -h/2), 1, toRadians(30), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		
		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(w/2, creature.getPosition().getX(), 1);
		assertEquals(-h/2, creature.getPosition().getY(), 1);
    }	
	
	@Test
	public void testDirectBottom() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(0, h/2), 1, toRadians(270), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();

		assertEquals(toRadians(90), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 1);
		assertEquals(h/2 -(51/6)*((toRadians(90) + cuscrea1.getDirection() + cuscrea2.getDirection() + cuscrea3.getDirection() + cuscrea4.getDirection()+ cuscrea5.getDirection())/6), creature.getPosition().getY(), 1);
		
	}
	
	@Test
	public void testDirectTop() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(0, -h/2), 1, toRadians(90), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();

		assertEquals(toRadians(270), creature.getDirection(), 0.01);
		assertEquals(0, creature.getPosition().getX(), 1);
		assertEquals(-h/2, creature.getPosition().getY(), 1);
		
	}

	// Special case: in a corner but not really facing both sides
	@Test
	public void testSpecialCorner() throws Exception {
		SmartCreature creature = new SmartCreature(environment,Closed.getInstance(),new Troupeau(), new Point2D.Double(w/2, h/2), 1, toRadians(210), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();

		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(h/2, creature.getPosition().getY(), 1);		
	}

	public String getName() {
		return getClass().getName();
	}
}
