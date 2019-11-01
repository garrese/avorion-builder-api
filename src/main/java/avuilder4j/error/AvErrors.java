package avuilder4j.error;

import avuilder4j.utils.AvValidations;
import avuilder4j.values.Spatial;

public class AvErrors {

	public static final String ROTATION_NOT_EXISTS = "Invalid rotation ID. Rotation ID does not exist.";
	public static final String ROTATION_NOT_RECOGNIZED = "Rotation ID not recognized.";

	public static final String CORNER_NOT_EXISTS = "Invalid corner ID. Corner ID does not exist.";
	public static final String CORNER_NOT_RECOGNIZED = "Corner ID not recognized.";

	public static final String AXIS_NOT_EXISTS = "Invalid axis ID. Axis ID does not exist.";
	public static final String AXIS_NOT_RECOGNIZED = "Axis ID not recognized.";
	public static final String AXIS_REPEATED = "Invalid axes. Some axis is repeated.";
	public static final String AXIS_AMOUNT = "Error solving axes amount.";

	public static final String END_NOT_EXISTS = "Invalid end ID. End ID does not exist.";
	public static final String END_NOT_RECOGNIZED = "End ID not recognized.";

	public static final String FACE_NOT_EXISTS = "Invalid face. Face ID does not exist.";
	public static final String FACE_NOT_RECOGNIZED = "Face ID not recognized.";
	public static final String FACE_REPEATED = "Invalid faces. Some face is repeated.";
	public static final String FACE_FIXED_MAX_NUMBER = "Invalid fixed faces ammount. Maximum is "
			+ Spatial.MAX_FIXED_FACES + ".";
	public static final String FACE_FIXED_AXES = "Can not be more than one fixed face in the same axis";

	public static final String POINT_NOT_RECOGNIZED = "Point ID not recognized.";

	public static final String LENGTH_NOT_POSITIVE = "Invalid length. Length must be greather than 0.";

	public static final String NOT_SUFFICIENTLY_DEFINED = "Object not sufficiently defined.";

	public static final String RATIO_NOT_POSITIVE = "Invalid ratio. Ratio must be greather than 0.";

	public static final String VOLUME_NOT_POSITIVE = "Invalid volume. Volume must be greather than 0.";

	public static final String COLOR_INVALID_FORMAT = "Invalid color format. Color must be in 8 digit color hex format "
			+ AvValidations.COLOR_REGEX + ".";

}
