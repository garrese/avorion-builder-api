package avuilder4j.data;

public class Formulae {

	public static double getProcessingPowerBySystemSlotsBarrier(int systemSlots) {
		double powModTill11 = 6.0;
		double pow;
		if (systemSlots >= 1 && systemSlots <= 10) {
			pow = systemSlots - powModTill11;
		} else {
			switch (systemSlots) {
			case 11:
				pow = 3.35;
				break;
			case 12:
				pow = 3.7;
				break;
			case 13:
				pow = 4.0;
				break;
			case 14:
				pow = 4.35;
				break;
			case 15:
				pow = 4.7;
				break;
			default:
				throw new IllegalArgumentException("System slots number must be from 1 to 15.");
			}
		}
		return 2000 * Math.pow(2.5, pow);
	}

	public static double getProcessingPowerBySystemSlots(int systemSlots) {
		return 1 + getProcessingPowerBySystemSlotsBarrier(systemSlots);
	}

	public static double materialDurability(int materialIndex) {
		return 4 * Math.pow(1.5, materialIndex);
	}

	public static double materialCreditCost(int materialIndex) {
		double baseCost = 10 / 9;
		double basePow = 1.35;
		return baseCost * Math.pow(basePow, materialIndex);
	}

	public static double generator(int materialIndex, double volume) {
		return volume * 1875000 * (40 + 3 * materialIndex + Math.pow(materialIndex, 2));
	}

	public static double shieldGenerator(int materialIndex, double volume) {
		return volume * 70 * Math.pow(1.5, materialIndex);
	}

	public static double storagesEffectiveVolume(double x, double y, double z) {
		return (x - 0.5) * (y - 0.5) * (z - 0.5);
	}

	public static double cargoHold(double x, double y, double z) {
		return 3.5 * storagesEffectiveVolume(x, y, z);
	}

	public static double hangar(double x, double y, double z) {
		return 0.5 * storagesEffectiveVolume(x, y, z);
	}

	public static double torpedoStorage(double x, double y, double z) {
		return 3.5 * storagesEffectiveVolume(x, y, z);
	}
}
