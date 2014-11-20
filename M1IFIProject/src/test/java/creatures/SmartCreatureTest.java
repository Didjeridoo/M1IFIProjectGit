package creatures;

import static java.lang.Math.toRadians;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import creatures.visual.CreatureSimulator;

public class SmartCreatureTest {

	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 100;
	final double h = 100;
	
	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int)w, (int)h));
	}

	@Test
	public void testEmerginBehavior() throws Exception {
		SmartCreature main = new SmartCreature(environment, new Point2D.Double(0, 0), toRadians(0), 5, Color.RED);

		ICreature other = mock(ICreature.class);
		when(other.getDirection()).thenReturn(toRadians(270));
		when(other.getSpeed()).thenReturn(10.0);
		when(other.getPosition()).thenReturn(new Point2D.Double(1,0));
		when(other.distanceFromAPoint(eq(main.getPosition()))).thenReturn(1.0);
		when(other.directionFormAPoint(eq(main.getPosition()), eq(main.direction))).thenReturn(0.0);

		ArrayList<ICreature> creaturesAround = new ArrayList<ICreature>();
		creaturesAround.add(other);
		
		when(environment.getCreatures()).thenReturn(creaturesAround);
		
		main.act();
		
		assertEquals(toRadians((270+0)/2), main.getDirection(), .01);
		assertEquals((10.0+5.0)/2, main.getSpeed(), .01);
		
		verify(other).getPosition();
		verify(other).getDirection();
		verify(other).getSpeed();
		verify(other).directionFormAPoint(eq(main.getPosition()),eq(0.0));
		verify(other).distanceFromAPoint(eq(main.getPosition()));
		verifyNoMoreInteractions(other);
	}	
}
