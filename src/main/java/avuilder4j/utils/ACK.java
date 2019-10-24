package avuilder4j.utils;

import avuilder4j.entities.dimensional.Point;

/**
 * General purpose constants.
 */
public class ACK {

	/**
	 * Block's base material cost
	 */
	public static final Double BASE_MATERIAL_COST = 5.0;

	public static final Point POINT_CERO = new Point(0.0, 0.0, 0.0);

	public static final int AXIS_X = 0;
	public static final int AXIS_Y = 1;
	public static final int AXIS_Z = 2;
	public static final int[] ALL_AXES = new int[] { AXIS_X, AXIS_Y, AXIS_Z };

}
