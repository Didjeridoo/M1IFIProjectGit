package creatures;

import static commons.Utils.filter;
import static commons.Utils.mkString;
import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static java.lang.Math.toDegrees;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commons.Utils.Predicate;
import comportement.IComportement;
import deplacements.IDeplacement;


public abstract class AbstractCreature implements ICreature {

	public static final int DEFAULT_SIZE = 80;
	public static final int DEFAULT_VISION_DISTANCE = 50;

	/**
	 * The field of view (FOV) is the extent of the observable world that is
	 * seen at any given moment by a creature in radians.
	 */
	protected double fieldOfView = (PI / 4);

	/**
	 * The distance indicating how far a creature see in front of itself in
	 * pixels.
	 */
	protected double visionDistance = DEFAULT_VISION_DISTANCE;

	/** Position */
	protected Point2D position;

	/** Speed in pixels */
	protected double speed;

	/** Direction in radians (0,2*pi) */
	protected double direction;

	/** Color of the creature */
	protected Color color;

	/** Reference to the environment */
	protected final IEnvironment environment;

	/** Size of the creature in pixels */
	protected final int size = DEFAULT_SIZE;
	
	protected final IComportement comport;
	protected final IDeplacement move;

	public AbstractCreature(IEnvironment environment, IComportement comportement, IDeplacement deplacement, Point2D position) {
		this.environment = environment;
		comport = comportement;
		move = deplacement;
		setPosition(position);
	}

	// ----------------------------------------------------------------------------
	// Getters and Setters
	// ----------------------------------------------------------------------------

	public IEnvironment getEnvironment() {
		return environment;
	}
	
	public double getFieldOfView() {
		return fieldOfView;
	}

	public double getLengthOfView() {
		return visionDistance;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction % (PI * 2);
	}
	
	public void changeDirection(double direction){
		this.direction = direction;
	}

	public Color getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	public Point2D getPosition() {
		return new Point2D.Double(position.getX(), position.getY());
	}

	public void setPosition(Point2D position) {
		setPosition(position.getX(), position.getY());
	}
	
	public void changePosition(Point2D position){
		this.position = position;
	}

	public void setPosition(double x, double y) {
		Dimension s = environment.getSize();
		
		if (x > s.getWidth() / 2) {
			x = -s.getWidth() / 2;
		} else if (x < -s.getWidth() / 2) {
			x = s.getWidth() / 2;
		}

		if (y > s.getHeight() / 2) {
			y = -s.getHeight() / 2;
		} else if (y < -s.getHeight() / 2) {
			y = s.getHeight() / 2;
		}

		this.position = new Point2D.Double(x, y);
	}

	// ----------------------------------------------------------------------------
	// Positioning methods
	// ----------------------------------------------------------------------------

	protected void move(double incX, double incY) {
		setPosition(position.getX() + incX, position.getY() + incY);
	}

	protected void rotate(double angle) {
		this.direction += angle;
	}

	// ----------------------------------------------------------------------------
	// Methods for calculating the direction
	// ----------------------------------------------------------------------------

	/**
	 * Computes the direction between the given point {@code (x1, y1)} and the
	 * current position in respect to a given {@code axis}.
	 * 
	 * @return direction in radians between given point and current position in
	 *         respect to a given {@code axis}.
	 */
	public double directionFormAPoint(Point2D p, double axis) {
		double b = 0d;

		// use a inverse trigonometry to get the angle in an orthogonal triangle
		// formed by the points (x,y) and (x1,y1)
		if (position.getX() != p.getX()) {
			// if we are not in the same horizontal axis
			b = atan((position.getY() - p.getY()) / (position.getX() - p.getX()));
		} else if (position.getY() < p.getY()) {
			// below -pi/2
			b = -PI / 2;
		} else {
			// above +pi/2
			b = PI / 2;
		}

		// make a distinction between the case when the (x1, y1)
		// is right from the (x,y) or left
		if (position.getX() < p.getX()) {
			b += PI;
		}

		// align with the axis of the origin (x1,y1)
		b = b - axis;

		// make sure we always take the smaller angle
		// keeping the range between (-pi, pi)
		if (b >= PI)
			b = b - PI * 2;
		else if (b < -PI)
			b = b + PI * 2;

		return b % (PI * 2);
	}

	/**
	 * Distance between the current position and a given point {@code(x1, y1)}.
	 * 
	 * @return distance between the current position and a given point.
	 */
	public double distanceFromAPoint(Point2D p) {
		return getPosition().distance(p);
	}

	// ----------------------------------------------------------------------------
	// Painting
	// ----------------------------------------------------------------------------

	public void paint(Graphics2D g2) {
		// center the point
		g2.translate(position.getX(), position.getY());
		// center the surrounding rectangle
		g2.translate(-size / 2, -size / 2);
		// center the arc
		// rotate towards the direction of our vector
		g2.rotate(-direction, size / 2, size / 2);

		// useful for debugging
		// g2.drawRect(0, 0, size, size);

		// set the color
		g2.setColor(color);
		// we need to do PI - FOV since we want to mirror the arc
		g2.fillArc(0, 0, size, size, (int) toDegrees(-fieldOfView / 2),
				(int) toDegrees(fieldOfView));

	}

	// ----------------------------------------------------------------------------
	// Description
	// ----------------------------------------------------------------------------

	public String toString() {
		Class<?> cl = getClass();

		StringBuilder sb = new StringBuilder();
		sb.append(getFullName(cl));
		sb.append("\n---\n");
		sb.append(mkString(getProperties(cl), "\n"));

		return sb.toString();
	}

	private List<String> getProperties(Class<?> clazz) {
		List<String> properties = new ArrayList<String>();

		Iterable<Field> fields = filter(
				Arrays.asList(clazz.getDeclaredFields()),
				new Predicate<Field>() {
					public boolean apply(Field input) {
						return !Modifier.isStatic(input.getModifiers());
					}
				});

		for (Field f : fields) {
			String name = f.getName();
			Object value = null;

			try {
				value = f.get(this);
			} catch (IllegalArgumentException e) {
				value = "unable to get value: " + e;
			} catch (IllegalAccessException e) {
				value = "unable to get value: " + e;
			} finally {
				properties.add(name + ": " + value);
			}
		}

		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			properties.addAll(getProperties(superclass));
		}

		return properties;
	}

	private String getFullName(Class<?> clazz) {
		String name = clazz.getSimpleName();
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			return getFullName(superclass) + " > " + name;
		} else {
			return name;
		}
	}
	
	public String getName() {
		return getClass().getName();
	}
	
}
