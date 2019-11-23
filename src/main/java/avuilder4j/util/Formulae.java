package avuilder4j.util;

public class Formulae {

	public static double getSystemSlotVolumeBarrier(int systemSlots) {
		double volBase = 2000.0;
		double powBase = 2.5;
		double powModTill11 = 6.0;

		double pow;
		if (systemSlots > 0 && systemSlots < 11) {
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
		return volBase * Math.pow(powBase, pow);
	}

	public static double getMaterialDurability(int materialIndex) {
		double durBase = 4.0;
		double powBase = 1.5;
		return durBase * Math.pow(powBase, materialIndex);
	}

	public static double getMaterialCreditCost(int materialIndex) {
		double baseCost = 10.0 / 9.0;
		double basePow = 1.35;
		return baseCost * Math.pow(basePow, materialIndex);
	}
}
