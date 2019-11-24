package avuilder4j.error;

import avuilder4j.design.sub.Orientation;

public class AvErrors {

	public static final String ARGUMENT_CASE_NOT_RECOGNIZED = "Argument case not recognized.";
	public static final String AXIS_AMOUNT = "Error solving axes amount.";
	public static final String AXIS_NOT_EXISTS = "Invalid axis ID. Axis ID does not exist.";
	public static final String AXIS_NOT_RECOGNIZED = "Axis ID not recognized.";
	public static final String AXIS_REPEATED = "Invalid axes. Some axis is repeated.";
	public static final String COLOR_INVALID_FORMAT = "Invalid color format. Color must be in 8 digit color hex format "
			+ AvValidations.COLOR_REGEX + ".";
	public static final String COLOR_NOT_NULLABLE = "Color values are not nullable.";
	public static final String CORNER_NOT_EXISTS = "Invalid corner ID. Corner ID does not exist.";
	public static final String CORNER_NOT_RECOGNIZED = "Corner ID not recognized.";
	public static final String END_NOT_EXISTS = "Invalid end ID. End ID does not exist.";
	public static final String END_NOT_LOWER = "Lower point must be lower than upper point.";
	public static final String END_NOT_NULLABLES = "End points are not nullable.";
	public static final String END_NOT_RECOGNIZED = "End ID not recognized.";
	public static final String END_NOT_UPPER = "Upper point must be higher than lower point.";
	public static final String FACE_FIXED_AXES = "Can not be more than one fixed face in the same axis";
	public static final String FACE_FIXED_MAX_NUMBER = "Invalid fixed faces ammount. Maximum is "
			+ AvValidations.MAX_FIXED_FACES + ".";
	public static final String FACE_NOT_EXISTS = "Invalid face. Face ID does not exist.";
	public static final String FACE_NOT_RECOGNIZED = "Face ID not recognized.";
	public static final String FACE_REPEATED = "Invalid faces. Some face is repeated.";
	public static final String INDEX_NEGATIVE = "Index values must be greather than 0.";
	public static final String INDEX_NOT_IN_MAP = "Index value not found in the map.";
	public static final String INDEX_NOT_NULLABLE = "Index values are not nullable.";
	public static final String LENGTH_NOT_POSITIVE = "Invalid length. Length must be greather than 0.";
	public static final String NOT_MUTABLE_OBJECT = "Impossible to change immutable object.";
	public static final String NOT_NULLABLE_APPEND = " is not nullable.";
	public static final String NOT_STATS_REGISTERED_MATERIAL = "No stats registered for material index ";
	public static final String NOT_STATS_REGISTERED_TYPE = "No stats registered for type index ";
	public static final String NOT_SUFFICIENTLY_DEFINED = "Object not sufficiently defined.";
	public static final String MAP_NOT_FOUND = "Map not found: ";
	public static final String OBJET_CLOSED = "Unable to modify closed objects.";
	public static final String ORIENTATION_NOT_IN_RANGE = "Orientation values must be between " + Orientation.MIN_LOOK
			+ " and " + Orientation.MAX_LOOK + ".";
	public static final String ORIENTATION_NOT_NULLABLE = "Orientation values are not nullable.";
	public static final String POINT_NOT_RECOGNIZED = "Point ID not recognized.";
	public static final String RATIO_NOT_POSITIVE = "Invalid ratio. Ratio must be greather than 0.";
	public static final String ROTATION_NOT_EXISTS = "Invalid rotation ID. Rotation ID does not exist.";
	public static final String ROTATION_NOT_RECOGNIZED = "Rotation ID not recognized.";
	public static final String VOLUME_NOT_POSITIVE = "Invalid volume. Volume must be greather than 0.";

}
