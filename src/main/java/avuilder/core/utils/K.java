package avuilder.core.utils;

import avuilder.core.entities.dimensional.Point;

/**
 * General purpose constants.
 */
public class K {

	/**
	 * Block's base material cost
	 */
	public static final Double BASE_MATERIAL_COST = 5.0;

	public static final Point DEFAULT_CENTER = new Point(0.0, 0.0, 0.0);

	public static final int FACE_BASE = 0;
	public static final int FACE_TOP = 1;
	public static final int FACE_WALL_1 = 2;
	public static final int FACE_WALL_2 = 3;
	public static final int FACE_WALL_3 = 4;
	public static final int FACE_WALL_4 = 5;
	public static final int[] ALL_FACES = new int[] { //@formatter:off
			FACE_BASE,
			FACE_TOP,
			FACE_WALL_1,
			FACE_WALL_2,
			FACE_WALL_3,
			FACE_WALL_4
	}; //@formatter:on

	public static final int CORNER_BASE_1 = 0;
	public static final int CORNER_BASE_2 = 1;
	public static final int CORNER_BASE_3 = 2;
	public static final int CORNER_BASE_4 = 3;
	public static final int CORNER_TOP_1 = 4;
	public static final int CORNER_TOP_2 = 5;
	public static final int CORNER_TOP_3 = 6;
	public static final int CORNER_TOP_4 = 7;
	public static final int[] ALL_CORNERS = new int[] { //@formatter:off
			CORNER_BASE_1,
			CORNER_BASE_2,
			CORNER_BASE_3,
			CORNER_BASE_4,
			CORNER_TOP_1,
			CORNER_TOP_2,
			CORNER_TOP_3,
			CORNER_TOP_4
	}; //@formatter:on

	public static final int X = 0;
	public static final int Y = 1;
	public static final int Z = 2;
	public static final int[] ALL_DIMENSIONS = new int[] { X, Y, Z };

	public static final int UPPER = 0;
	public static final int LOWER = 1;

}
