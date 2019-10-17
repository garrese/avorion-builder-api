package avuilder.core.utils;

import avuilder.core.error.Errors;

public class ValidationUtils {

	public static void validateVolumeArgs(Double... volume) {
		for (Double vol : volume) {
			if (vol == null || vol <= 0) {
				throw new IllegalArgumentException(Errors.INVALID_VOLUME);
			}
		}
	}

	public static void validateLengthArgs(Double... length) {
		for (Double len : length) {
			if (len == null || len <= 0) {
				throw new IllegalArgumentException(Errors.INVALID_LENGTH);
			}
		}
	}

	public static void validateRatioArgs(Double... ratio) {
		for (Double rat : ratio) {
			if (rat == null || rat <= 0) {
				throw new IllegalArgumentException(Errors.INVALID_RATIO);
			}
		}
	}
}
