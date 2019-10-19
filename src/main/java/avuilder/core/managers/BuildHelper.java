package avuilder.core.managers;

import avuilder.core.entities.dimensional.Cuboid;
import avuilder.core.entities.dimensional.Margins;
import avuilder.core.error.AvuilderManagerException;
import avuilder.core.error.Errors;
import avuilder.core.utils.K;
import avuilder.core.utils.Validations;

public class BuildHelper {

	public void escalate(Cuboid cuboid, double ratio, int... dimensions) {
		try {
			cuboid.validate();
			Validations.validateRatios(ratio);
			Validations.validateDimensions(dimensions);
			if (dimensions.length == 0) {
				dimensions = K.ALL_AXES;
			}
			for (int d : dimensions) {
				cuboid.setAxisUpper(d, cuboid.getAxisUpper(d) * ratio);
				cuboid.setAxisLower(d, cuboid.getAxisLower(d) * ratio);
			}
		} catch (Exception e) {
			throw new AvuilderManagerException(e);
		}
	}

	public void escalateByVolume(Cuboid cuboid, double finalVolume, int... dimensions) {
		try {
			cuboid.validate();
			Validations.validateVolumes(finalVolume);
			Validations.validateDimensions(dimensions);
			double ratio;
			switch (dimensions.length) {
			case 0:
			case 3:
				ratio = Math.cbrt(finalVolume / cuboid.getVolume());
				break;
			case 2:
				ratio = Math.sqrt(finalVolume / cuboid.getVolume());
				break;
			case 1:
				ratio = finalVolume / cuboid.getVolume();
				break;
			default:
				throw new AvuilderManagerException(Errors.AXIS_AMOUNT);
			}
			escalate(cuboid, ratio, dimensions);
		} catch (Exception e) {
			throw new AvuilderManagerException(e);
		}
	}

	public void matchFace(Cuboid cuboid, Cuboid matched, String matchedFace, Margins margins) {

	}

}
