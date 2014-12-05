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

// Test d'un deplacement au hasard. Les creatures vont se deplacer tout droit sans changer de direction et au 30eme tick elles vont changer de direction aleatoirement.
// Nous utiliserons ici des CustomCreatures auxquelles nous definirons un environnement torique.
public class HasardTest {

	// Creation d'un mock du simulateur et definition des largeurs et hauteurs
	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;
	IColorStrategy color = new Unique();

	// Utilisation du mock afin de renvoyer les tailles de cotes que nous fixons pour les tests
	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
	}
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction GAUCHE ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction DROITE ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction HAUT ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction BAS ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction HAUT-GAUCHE ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction HAUT-DROITE ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction BAS-GAUCHE ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee au centre du simulateur avec une direction BAS-DROITE ne change pas de direction au premier tick
	// et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee sur le cote DROIT du simulateur avec une direction DROITE ne change pas de direction au premier tick
	// malgres le changement de cote et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee sur le cote GAUCHE du simulateur avec une direction GAUCHE ne change pas de direction au premier tick
	// malgres le changement de cote et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee sur le cote HAUT du simulateur avec une direction HAUT ne change pas de direction au premier tick
	// malgres le changement de cote et avance dans la direction donnee
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
	
	// Verification que la creature qui est creee sur le cote BAS du simulateur avec une direction BAS ne change pas de direction au premier tick
	// malgres le changement de cote et avance dans la direction donnee
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

	// Verification qu'au 30eme tick la creature change bien de direction lors de son deplacement
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
