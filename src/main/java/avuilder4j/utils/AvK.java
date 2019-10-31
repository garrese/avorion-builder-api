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

	public static final int ROTATION_X = 0;
	public static final int ROTATION_X_INVERSE = 1;
	public static final int ROTATION_Y = 2;
	public static final int ROTATION_Y_INVERSE = 3;
	public static final int ROTATION_Z = 4;
	public static final int ROTATION_Z_INVERSE = 5;
	public static final int[] ROTATIONS_LIST = new int[] { // @formatter:off
			ROTATION_X,
			ROTATION_X_INVERSE,
			ROTATION_Y,
			ROTATION_Y_INVERSE,
			ROTATION_Z,
			ROTATION_Z_INVERSE,
	}; //@formatter:on

	public static final Point POINT_ZERO = new Point(0.0, 0.0, 0.0);
	public static final Orientation ORIENTATION_ZERO = new Orientation(0, 0);

	public static final String DEFAULT_COLOR = "ffffffff";
	public static final Integer DEFAULT_MATERIAL = Mats.IRON;
	public static final Integer DEFAULT_TYPE = Types.BLANK_HULL;
	public static final Double DEFAULT_LENGTH = 2.0;
	public static final Orientation DEFAULT_ORIENTATION = ORIENTATION_ZERO;

	public static final int AXIS_X = 0;
	public static final int AXIS_Y = 1;
	public static final int AXIS_Z = 2;
	public static final int[] ALL_AXES = new int[] { AXIS_X, AXIS_Y, AXIS_Z };

}
