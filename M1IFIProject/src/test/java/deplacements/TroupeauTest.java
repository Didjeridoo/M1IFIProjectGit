package deplacements;

import static commons.Utils.filter;
import static java.lang.Math.toRadians;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import comportements.Toric;
import creatures.AbstractCreature;
import creatures.CustomCreature;
import creatures.ICreature;
import creatures.StupidCreature;
import creatures.visual.CreatureSimulator;
import deplacements.Troupeau.CreaturesAroundCreature;

public class TroupeauTest {

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
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
		cuscrea1 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(2, 3), 10, toRadians(10), Color.RED);
		cuscrea2 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 10), 5, toRadians(280), Color.RED);
		cuscrea3 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 25), 10, toRadians(56), Color.RED);
		cuscrea4 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 45), 5, toRadians(95), Color.RED);
		cuscrea5 = new CustomCreature(environment, Toric.getInstance(), new Stupid(), new Point2D.Double(0, 35), 10, toRadians(180), Color.RED);
		
	}
	
	@Test
	public void testDirectLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(180), Color.RED);
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
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
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
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
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
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
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
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
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
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
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
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
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
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
		List listCrea = new ArrayList();
		listCrea.add(creature);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();

		assertEquals(toRadians(315), creature.getDirection(), 0.01);
		assertEquals(5, creature.getPosition().getX(), 3);
		assertEquals(5, creature.getPosition().getY(), 3);
	}
	
	@Test
	public void testFollow() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Troupeau(), new Point2D.Double(
				0, 0), 10, toRadians(180), Color.RED);
		double avgSpeed = creature.getSpeed();
		double avgDir = creature.getDirection();
		int count = 0;
		List listCrea = new ArrayList();
		listCrea.add(creature);
		listCrea.add(cuscrea1);
		listCrea.add(cuscrea2);
		listCrea.add(cuscrea3);
		listCrea.add(cuscrea4);
		listCrea.add(cuscrea5);
		when(environment.getCreatures()).thenReturn(listCrea);
		creature.act();
		Iterable<ICreature> creatures = filter(environment.getCreatures(), new CreaturesAroundCreature(creature));
		for (ICreature c : creatures) {
			avgSpeed += c.getSpeed();
			avgDir += c.getDirection();
			count++;
		}
		avgSpeed = avgSpeed / (count + 1);
		avgDir = avgDir / (count + 1);
		
		assertEquals(avgDir,creature.getDirection(), 0.01);
		assertEquals(avgSpeed, creature.getSpeed(), 0.01);
	}
}
