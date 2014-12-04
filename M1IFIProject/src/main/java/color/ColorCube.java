package color;

import java.awt.Color;

public class ColorCube implements IColorStrategy {

	private final float colorPhase;
	private float r = 0.0f;
	private float g = 0.0f;
	private float b = 0.0f;

	public ColorCube() {
		float creaturesCountCubeRoot = (float) Math.pow(100, 1.0 / 3.0);
		colorPhase = (float) (1.0 / creaturesCountCubeRoot);
	}



	public Color getColor() {
		r += colorPhase;
		if (r > 1.0) {
			r -= 1.0f;
			g += colorPhase;
			if (g > 1.0) {
				g -= 1.0f;
				b += colorPhase;
				if (b > 1.0)
					b -= 1.0f;
			}
		}

		return new Color(r, g, b);
	}



	public String getName() {
		return getClass().getName();
	}

}
