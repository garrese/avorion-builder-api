package avuilder.core.utils;

import java.util.ArrayList;
import java.util.List;

import avuilder.core.error.Errors;

public class ValidationUtils {

	public static void validateVolumes(double... volume) {
		for (Double vol : volume) {
			if (vol <= 0) {
				throw new IllegalArgumentException(Errors.INVALID_VOLUME);
			}
		}
	}

	public static void validateLengthArgs(double... length) {
		for (Double len : length) {
			if (len <= 0) {
				throw new IllegalArgumentException(Errors.INVALID_LENGTH);
			}
		}
	}

	public static void validateRatios(double... ratio) {
		for (Double rat : ratio) {
			if (rat <= 0) {
				throw new IllegalArgumentException(Errors.INVALID_RATIO);
			}
		}
	}

	public static void validateDimensions(int... dimensions) {
		validateDimensionsRepetition(dimensions);
		validateDimensionsExistance(dimensions);
	}

	public static void validateDimensionsRepetition(int... dimensions) {
		List<Integer> repeatedDims = new ArrayList<Integer>();
		for (int validating : dimensions) {
			for (int repeatedDim : repeatedDims) {
				if (validating == repeatedDim) {
					throw new IllegalArgumentException(Errors.INVALID_DIMENSION_REPEATED);
				}
			}
			repeatedDims.add(validating);
		}
	}

	public static void validateDimensionsExistance(int... dimensions) {
		boolean found = false;
		for (int validating : dimensions) {
			for (int constantDim : K.ALL_DIMENSIONS) {
				if (validating == constantDim) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IllegalArgumentException(Errors.INVALID_DIMENSION);
			}
			found = false;
		}

	}

}
