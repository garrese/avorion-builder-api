package avuilder4j.error;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.Face;
import avuilder4j.design.sub.Orientation;

public class AvValidations {

	public static final String COLOR_REGEX = "[0-9|a-fA-F]{8}";

	public static final int MAX_FIXED_FACES = 2;

	public static void volumes(double... volume) {
		for (Double vol : volume) {
			if (vol <= 0)
				throw new IllegalArgumentException(AvErrors.VOLUME_NOT_POSITIVE);
		}
	}

	public static void lengths(Double... length) {
		for (Double len : length) {
			if (len != null && len <= 0)
				throw new IllegalArgumentException(AvErrors.LENGTH_NOT_POSITIVE);
		}
	}

	public static void ratios(double... ratio) {
		for (Double rat : ratio) {
			if (rat <= 0)
				throw new IllegalArgumentException(AvErrors.RATIO_NOT_POSITIVE);
		}
	}

	public static void orientation(boolean nullable, Integer... orientationValues) {
		for (Integer orientation : orientationValues) {
			if (orientation == null) {
				if (!nullable)
					throw new IllegalArgumentException(AvErrors.ORIENTATION_NOT_NULLABLE);
			} else if (orientation < 0 || orientation > 6) {
				throw new IllegalArgumentException(AvErrors.ORIENTATION_NOT_IN_RANGE);
			}
		}
	}

	public static void orientation(boolean nullable, Orientation... orientationValues) {
		for (Orientation orientation : orientationValues) {
			if (orientation == null) {
				if (!nullable)
					throw new IllegalArgumentException(AvErrors.ORIENTATION_NOT_NULLABLE);
			} else {
				orientation(nullable, orientation.getLook(), orientation.getUp());
			}
		}
	}

	public static void notNull(Object o, String fieldName) {
		if (o == null)
			throw new IllegalArgumentException(fieldName + AvErrors.NOT_NULLABLE_APPEND);
	}

	public static void ends(boolean nullable, Double lower, Double upper) {
		if (lower == null || upper == null) {
			if (!nullable)
				throw new IllegalArgumentException(AvErrors.END_NOT_NULLABLES);
		} else if (upper <= lower) {
			throw new IllegalArgumentException(AvErrors.END_NOT_UPPER);
		}
	}

	public static void colors(boolean nullable, String... colors) {
		for (String color : colors) {
			if (color == null) {
				if (!nullable)
					throw new IllegalArgumentException(AvErrors.COLOR_NOT_NULLABLE);
			} else if (!color.matches(COLOR_REGEX)) {
				throw new IllegalArgumentException(AvErrors.COLOR_INVALID_FORMAT);
			}
		}
	}

	public static void indexes(boolean nullable, Integer... indexes) {
		for (Integer index : indexes) {
			if (index == null) {
				if (!nullable)
					throw new IllegalArgumentException(AvErrors.INDEX_NOT_NULLABLE);
			} else if (index < 0) {
				throw new IllegalArgumentException(AvErrors.INDEX_NEGATIVE);
			}
		}
	}

	public static void validateAxesRepetition(Axis... axesIds) {
		validateIdArgsRepetition(axesIds, AvErrors.AXIS_REPEATED);
	}

	public static void validateFacesRepetition(Face... facesIds) {
		validateIdArgsRepetition(facesIds, AvErrors.FACE_REPEATED);
	}

	public static void validateFixedFacesMaxNumber(Face... fixedFacesIds) {
		validateIdArgsMaxNumber(fixedFacesIds, MAX_FIXED_FACES, AvErrors.FACE_FIXED_MAX_NUMBER);
	}

	public static void validateFixedFacesAxes(Face... fixedFacesIds) {
		Axis[] fixedFacesAxes = new Axis[fixedFacesIds.length];
		for (int i = 0; i < fixedFacesIds.length; i++) {
			fixedFacesAxes[i] = Axis.getAxisIdByFaceId(fixedFacesIds[i]);
		}
		AvValidations.validateIdArgsRepetition(fixedFacesAxes, AvErrors.FACE_FIXED_AXES);
	}

	public static void validateIdArgsExistance(int[] validatingArgs, int[] allConstants, String errorMsg) {
		boolean found = false;
		for (int arg : validatingArgs) {
			for (int constant : allConstants) {
				if (arg == constant) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IllegalArgumentException(errorMsg);
			}
			found = false;
		}
	}

	public static void validateIdArgsRepetition(int[] validatingArgs, String errorMsg) {
		List<Integer> listedArgs = new ArrayList<Integer>();
		for (int validating : validatingArgs) {
			for (int listed : listedArgs) {
				if (validating == listed) {
					throw new IllegalArgumentException(errorMsg);
				}
			}
			listedArgs.add(validating);
		}
	}

	public static void validateIdArgsRepetition(Object[] validatingArgs, String errorMsg) {
		List<Object> listedArgs = new ArrayList<Object>();
		for (Object validating : validatingArgs) {
			for (Object listed : listedArgs) {
				if (validating.equals(listed)) {
					throw new IllegalArgumentException(errorMsg);
				}
			}
			listedArgs.add(validating);
		}
	}

	public static void validateIdArgsMaxNumber(int[] validatingArgs, int maxNumber, String errorMsg) {
		if (validatingArgs.length > maxNumber)
			throw new IllegalArgumentException(errorMsg);
	}

	public static void validateIdArgsMaxNumber(Object[] validatingArgs, int maxNumber, String errorMsg) {
		if (validatingArgs.length > maxNumber)
			throw new IllegalArgumentException(errorMsg);
	}

}
