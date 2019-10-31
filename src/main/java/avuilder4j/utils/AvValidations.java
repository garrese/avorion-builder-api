package avuilder4j.utils;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.entities.dimensional.Cuboid;
import avuilder4j.error.AvErrors;

public class AvValidations {

	public static final String COLOR_REGEX = "[0-9|a-fA-F]{8}";
	public static final int MAX_FIXED_FACES = 3;

	public static void validateVolumes(double... volume) {
		for (Double vol : volume) {
			if (vol <= 0)
				throw new IllegalArgumentException(AvErrors.VOLUME_NOT_POSITIVE);
		}
	}

	public static void validateLengths(Double... length) {
		for (Double len : length) {
			if (len != null && len <= 0)
				throw new IllegalArgumentException(AvErrors.LENGTH_NOT_POSITIVE);
		}
	}

	public static void validateRatios(double... ratio) {
		for (Double rat : ratio) {
			if (rat <= 0)
				throw new IllegalArgumentException(AvErrors.RATIO_NOT_POSITIVE);
		}
	}

	public static void validateColors(String... colors) {
		for (String color : colors) {
			if (!color.matches(COLOR_REGEX))
				throw new IllegalArgumentException(AvErrors.COLOR_INVALID_FORMAT);
		}
	}

	public static void validateRotationsExistance(int... rotationIds) {
		validateIdArgsExistance(rotationIds, AvK.ROTATIONS_LIST, AvErrors.ROTATION_NOT_EXISTS);
	}

	public static void validateAxesExistance(int... axesIds) {
		validateIdArgsExistance(axesIds, AvK.ALL_AXES, AvErrors.AXIS_NOT_EXISTS);
	}

	public static void validateAxesRepetition(int... axesIds) {
		validateIdArgsRepetition(axesIds, AvErrors.AXIS_REPEATED);
	}

	public static void validateCornersExistance(int... cornersIds) {
		validateIdArgsExistance(cornersIds, Cuboid.CORNERS_LIST, AvErrors.CORNER_NOT_EXISTS);
	}

	public static void validateFacesExistance(int... facesIds) {
		validateIdArgsExistance(facesIds, Cuboid.FACES_LIST, AvErrors.FACE_NOT_EXISTS);
	}

	public static void validateFacesRepetition(int... facesIds) {
		validateIdArgsRepetition(facesIds, AvErrors.FACE_REPEATED);
	}

	public static void validateFixedFacesMaxNumber(int... fixedFacesIds) {
		validateIdArgsMaxNumber(fixedFacesIds, MAX_FIXED_FACES, AvErrors.FACE_FIXED_MAX_NUMBER);
	}

	public static void validateFixedFacesAxes(int... fixedFacesIds) {
		int[] fixedFacesAxes = new int[fixedFacesIds.length];
		for (int i = 0; i < fixedFacesIds.length; i++) {
			fixedFacesAxes[i] = AvU.getAxisIdByFaceId(fixedFacesIds[i]);
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

	public static void validateIdArgsMaxNumber(int[] validatingArgs, int maxNumber, String errorMsg) {
		if (validatingArgs.length > maxNumber)
			throw new IllegalArgumentException(errorMsg);
	}

}
