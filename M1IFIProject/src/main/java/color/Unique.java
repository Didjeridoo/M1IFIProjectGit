package color;

import java.awt.Color;

public class Unique implements IColorStrategy{

	public Color getColor() {
		return Color.RED;
	}

	public String getName() {
		return getClass().getName();
	}

}
