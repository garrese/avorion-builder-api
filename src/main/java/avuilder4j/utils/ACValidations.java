package avuilder4j.utils;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.entities.dimensional.Cuboid;
import avuilder4j.error.ACErrors;

public class ACValidations {

	public static final String colorRegex = "";

	public static void validateVolumes(double... volume) {
		for (Double vol : volume) {
			if (vol <= 0)
				throw new IllegalArgumentException(ACErrors.VOLUME_NOT_POSITIVE);
		}
	}

	public static void validateLengths(double... length) {
		for (Double len : length) {
			if (len <= 0)
				throw new IllegalArgumentException(ACErrors.LENGTH_NOT_POSITIVE);
		}
	}

	public static void validateRatios(double... ratio) {
		for (Double rat : ratio) {
			if (rat <= 0)
				throw new IllegalArgumentException(ACErrors.RATIO_NOT_POSITIVE);
		}
	}

	public static void validateColors(String... colors) {
		for (String color : colors) {
			if (!color.matches("[0-9|a-fA-F]{8}"))
				throw new IllegalArgumentException(ACErrors.COLOR_INVALID_FORMAT);
		}
	}

	public static void validateAxesRepetition(int... axesIds) {
		validateConstantsArgsRepetition(axesIds, ACK.ALL_AXES, ACErrors.AXIS_NOT_EXISTS);
	}

	public static void validateAxesExistance(int... axesIds) {
		validateConstantsArgsExistance(axesIds, ACK.ALL_AXES, ACErrors.AXIS_NOT_EXISTS);
	}

	public static void validateAxes(int... axesIds) {
		validateAxesRepetition(axesIds);
		validateAxesExistance(axesIds);
	}

	public static void validateCornersExistance(int... corners) {
		validateConstantsArgsExistance(corners, Cuboid.CORNERS, ACErrors.CORNER_NOT_EXISTS);
	}

	public static void validateFacesExistance(int... faces) {
		validateConstantsArgsExistance(faces, Cuboid.FACES, ACErrors.FACE_NOT_EXISTS);
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
