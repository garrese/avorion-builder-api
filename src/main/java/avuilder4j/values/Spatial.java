package avuilder4j.values;

import avuilder4j.entities.spatial.util.Point;
import avuilder4j.error.AvErrors;

public class Spatial {

	public static final int AXIS_X = 0;
	public static final int AXIS_Y = 1;
	public static final int AXIS_Z = 2;
	public static final int[] AXES_LIST = new int[] { AXIS_X, AXIS_Y, AXIS_Z };

	public static final int CORNER_BASE_1 = 0;
	public static final int CORNER_BASE_2 = 1;
	public static final int CORNER_BASE_3 = 2;
	public static final int CORNER_BASE_4 = 3;
	public static final int CORNER_TOP_1 = 4;
	public static final int CORNER_TOP_2 = 5;
	public static final int CORNER_TOP_3 = 6;

	public static final int CORNER_TOP_4 = 7;
	public static final int[] CORNERS_LIST = new int[] { // @formatter:off
			CORNER_BASE_1,
			CORNER_BASE_2,
			CORNER_BASE_3,
			CORNER_BASE_4,
			CORNER_TOP_1,
			CORNER_TOP_2,
			CORNER_TOP_3,
			CORNER_TOP_4
	}; //@formatter:on

	public static final int FACE_WALL_XL = 0;
	public static final int FACE_WALL_XU = 1;
	public static final int FACE_WALL_YL = 2;
	public static final int FACE_WALL_YU = 3;
	public static final int FACE_WALL_ZL = 4;
	public static final int FACE_WALL_ZU = 5;
	public static final int[] FACES_LIST = new int[] { // @formatter:off
			FACE_WALL_XU,
			FACE_WALL_XL,
			FACE_WALL_YU, 
			FACE_WALL_YL, 
			FACE_WALL_ZU, 
			FACE_WALL_ZL
	}; // @formatter:on
	public static final int MAX_FIXED_FACES = 3;

	public static final Point POINT_ZERO = new Point(0.0, 0.0, 0.0);

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
	public static final int END_UPPER = 1;
	public static final int END_LOWER = 0;
	public static final int[] ENDS_LIST = new int[] { END_LOWER, END_UPPER };

	public static int getAxisIdByFaceId(int faceId) {
		switch (faceId) {
		case FACE_WALL_XU:
		case FACE_WALL_XL:
			return AXIS_X;
		case FACE_WALL_YU:
		case FACE_WALL_YL:
			return AXIS_Y;
		case FACE_WALL_ZU:
		case FACE_WALL_ZL:
			return AXIS_Z;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

	public static int[] getAxesIdsInvolvedInRotation(int rotationId) {

		int[] axes = new int[2];
		switch (rotationId) {
		case ROTATION_X:
		case ROTATION_X_INVERSE:
			axes[0] = AXIS_Y;
			axes[1] = AXIS_Z;
			break;
		case ROTATION_Y:
		case ROTATION_Y_INVERSE:
			axes[0] = AXIS_X;
			axes[1] = AXIS_Z;
			break;
		case ROTATION_Z:
		case ROTATION_Z_INVERSE:
			axes[0] = AXIS_Z;
			axes[1] = AXIS_Y;
			break;
		default:
			throw new IllegalArgumentException(AvErrors.ROTATION_NOT_RECOGNIZED);
		}
		return axes;
	}

	public static int getEndIdByFaceId(int faceId) {
		switch (faceId) {
		case FACE_WALL_XU:
		case FACE_WALL_YU:
		case FACE_WALL_ZU:
			return Spatial.END_UPPER;
		case FACE_WALL_XL:
		case FACE_WALL_YL:
		case FACE_WALL_ZL:
			return Spatial.END_LOWER;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

	public static int[] getFacesIdsByAxisId(int axisId) {
		switch (axisId) {
		case AXIS_X:
			return new int[] { FACE_WALL_XL, FACE_WALL_XU };
		case AXIS_Y:
			return new int[] { FACE_WALL_YL, FACE_WALL_YU };
		case AXIS_Z:
			return new int[] { FACE_WALL_ZL, FACE_WALL_ZU };
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public static int getOppositeEndId(int endId) {
		switch (endId) {
		case Spatial.END_UPPER:
			return Spatial.END_LOWER;
		case Spatial.END_LOWER:
			return Spatial.END_UPPER;
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

}
