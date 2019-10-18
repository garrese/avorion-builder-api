package avuilder.core.utils;

import java.util.ArrayList;
import java.util.List;

import avuilder.core.error.Errors;

public class Validations {

	public static void validateVolumes(double... volume) {
		for (Double vol : volume) {
			if (vol <= 0) {
				throw new IllegalArgumentException(Errors.VOLUME_NOT_POSITIVE);
			}
		}
	}

	public static void validateLengths(double... length) {
		for (Double len : length) {
			if (len <= 0) {
				throw new IllegalArgumentException(Errors.LENGTH_NOT_POSITIVE);
			}
		}
	}

	public static void validateRatios(double... ratio) {
		for (Double rat : ratio) {
			if (rat <= 0) {
				throw new IllegalArgumentException(Errors.RATIO_NOT_POSITIVE);
			}
		}
	}

	public static void validateDimensionsRepetition(int... dimensions) {
		validateConstantsArgsRepetition(dimensions, K.ALL_DIMENSIONS, Errors.DIMENSION_NOT_EXISTS);
	}

	public static void validateDimensionsExistance(int... dimensions) {
		validateConstantsArgsExistance(dimensions, K.ALL_DIMENSIONS, Errors.DIMENSION_NOT_EXISTS);
	}

	public static void validateDimensions(int... dimensions) {
		validateDimensionsRepetition(dimensions);
		validateDimensionsExistance(dimensions);
	}

	public static void validateCornersExistance(int... corners) {
		validateConstantsArgsExistance(corners, K.ALL_CORNERS, Errors.CORNER_NOT_EXISTS);
	}

	public static void validateFacesExistance(int... faces) {
		validateConstantsArgsExistance(faces, K.ALL_FACES, Errors.FACE_NOT_EXISTS);
	}

	public static void validateConstantsArgsExistance(int[] validatingArgs, int[] allConstants, String errorMsg) {
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

	public static void validateConstantsArgsRepetition(int[] validatingArgs, int[] allConstants, String errorMsg) {
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

}
