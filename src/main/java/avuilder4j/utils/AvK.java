package avuilder4j.utils;

import avuilder4j.entities.dimensional.Point;
import avuilder4j.entities.game.Orientation;

/**
 * General purpose constants.
 */
public class AvK {

	/**
	 * Block's base material cost
	 */
	public static final Double BASE_MATERIAL_COST = 5.0;

	public static final Point POINT_ZERO = new Point(0.0, 0.0, 0.0);
	public static final Orientation ORIENTATION_ZERO = new Orientation(0, 0);

	public static final String DEFAULT_COLOR = "00000000";
	public static final Integer DEFAULT_MATERIAL = 1;
	public static final Integer DEFAULT_TYPE = 1;
	public static final Double DEFAULT_LENGTH = 2.0;
	public static final Orientation DEFAULT_ORIENTATION = ORIENTATION_ZERO;

	public static final int AXIS_X = 0;
	public static final int AXIS_Y = 1;
	public static final int AXIS_Z = 2;
	public static final int[] ALL_AXES = new int[] { AXIS_X, AXIS_Y, AXIS_Z };

}
