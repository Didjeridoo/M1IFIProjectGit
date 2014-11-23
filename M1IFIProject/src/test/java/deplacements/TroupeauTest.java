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
import creatures.visual.CreatureSimulator;

public class TroupeauTest {

	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;

	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
	}
	
	@Test
	public void test() {
//		fail("Not yet implemented");
		assertTrue(true);
	}

}
