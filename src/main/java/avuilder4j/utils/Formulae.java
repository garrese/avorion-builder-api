package avuilder4j.utils;

public class Formulae {

	public static double getSystemSlotVolumeBarrier(int systemSlots) {
		double base = 2000.0;
		double powBase = 2.5;
		double powModTill11 = 6;

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
				pow = 4;
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
		return base * Math.pow(powBase, pow);
	}
}
