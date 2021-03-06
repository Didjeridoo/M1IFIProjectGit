package comportements;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static java.lang.Math.toRadians;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import creatures.CustomCreature;
import creatures.visual.CreatureSimulator;
import deplacements.Hasard;

//Test d'un monde torique. Les creatures ne vont rebondir sur aucun des cotes et vont reapparaitre sur le cote oppose.
//Nous utiliserons ici des CustomCreatures auxquelles nous definirons un deplacement au hasard.
public class ToricTest {

	// Creation d'un mock du simulateur et definition des largeurs et hauteurs
	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;

	// Utilisation du mock afin de renvoyer les tailles de cotes que nous fixons pour les tests
	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
	}

	// Verification que la creature avec une direction HAUT-GAUCHE ne rebondis pas et reapparait bien sur le cote DROIT sans changer de direction
	// lorsque la creature sort du cadre par la GAUCHE
	@Test
	public void testDirectLeftUp() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				-w / 2 + 1, 0), 10, toRadians(150), Color.RED);
		creature.act();

		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(w / 2, creature.getPosition().getX(), 2);
	}

	// Verification que la creature avec une direction BAS-GAUCHE ne rebondis pas et reapparait bien sur le cote DROIT sans changer de direction
	// lorsque la creature sort du cadre par la GAUCHE
	@Test
	public void testDirectLeftDown() throws Exception {
		CustomCreature creature = new CustomCreature(environment, Toric.getInstance(),
				new Hasard(), new Point2D.Double(
				-w / 2 + 1, 0), 10, toRadians(210), Color.RED);
		creature.act();

		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(w / 2, creature.getPosition().getX(), 2);
	}

	// Verification que la creature avec une direction HAUT-DROITE ne rebondis pas et reapparait bien sur le cote GAUCHE sans changer de direction
	// lorsque la creature sort du cadre par la DROITE
	@Test
	public void testDirectRightUp() throws Exception {
		CustomCreature creature = new CustomCreature(environment,Toric.getInstance(),
		new Hasard(), new Point2D.Double(w / 2 - 1, 0), 10, toRadians(30), Color.RED);
		creature.act();

		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(-w / 2, creature.getPosition().getX(), 2);
	}

	// Verification que la creature avec une direction BAS-DROITE ne rebondis pas et reapparait bien sur le cote GAUCHE sans changer de direction
	// lorsque la creature sort du cadre par la DROITE
	@Test
	public void testDirectRightDown() throws Exception {
		CustomCreature creature = new CustomCreature(environment,Toric.getInstance(),
		new Hasard(), new Point2D.Double(w / 2 - 1, 0), 10, toRadians(330), Color.RED);
		creature.act();

		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(-w / 2, creature.getPosition().getX(), 2);
	}

	// Verification que la creature avec une direction BAS-GAUCHE ne rebondis pas et reapparait bien sur le cote HAUT sans changer de direction
	// lorsque la creature sort du cadre par la BAS
	@Test
	public void testDirectDownLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment,Toric.getInstance(),
		new Hasard(), new Point2D.Double(0, h / 2 - 1), 10, toRadians(210), Color.RED);
		creature.act();

		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(-h / 2, creature.getPosition().getY(), 2);
	}
	
	// Verification que la creature avec une direction BAS-DROITE ne rebondis pas et reapparait bien sur le cote HAUT sans changer de direction
	// lorsque la creature sort du cadre par la BAS
	@Test
	public void testDirectDownRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment,Toric.getInstance(),
		new Hasard(), new Point2D.Double(0, h / 2 - 1), 10, toRadians(330), Color.RED);
		creature.act();

		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(-h / 2, creature.getPosition().getY(), 2);
	}
	
	// Verification que la creature avec une direction HAUT-GAUCHE ne rebondis pas et reapparait bien sur le cote BAS sans changer de direction
	// lorsque la creature sort du cadre par la HAUT
	@Test
	public void testDirectTopLeft() throws Exception {
		CustomCreature creature = new CustomCreature(environment,Toric.getInstance(),
		new Hasard(), new Point2D.Double(0, -h / 2 + 1), 10, toRadians(150), Color.RED);
		creature.act();

		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(h / 2, creature.getPosition().getY(), 2);
	}
	
	// Verification que la creature avec une direction HAUT-DROITE ne rebondis pas et reapparait bien sur le cote BAS sans changer de direction
	// lorsque la creature sort du cadre par la HAUT
	@Test
	public void testDirectTopRight() throws Exception {
		CustomCreature creature = new CustomCreature(environment,Toric.getInstance(),
		new Hasard(), new Point2D.Double(0, -h / 2 + 1), 10, toRadians(30), Color.RED);
		creature.act();

		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(h / 2, creature.getPosition().getY(), 2);
	}

	public String getName() {
		return getClass().getName();
	}
}
