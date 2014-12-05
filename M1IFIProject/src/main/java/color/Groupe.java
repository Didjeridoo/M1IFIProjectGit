package color;

import java.awt.Color;
import java.util.Random;

public class Groupe implements IColorStrategy {
	int number;
	Random rand = new Random();
	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b = rand.nextFloat();

	public String getName() {
		return getClass().getName();
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		int j = 0;
		if (number % 10 == 0 && number != 0 && number / 10 > j) {
			j++;
			r = rand.nextFloat();
			g = rand.nextFloat();
			b = rand.nextFloat();
		}

		return new Color(r, g, b);
	}
}
