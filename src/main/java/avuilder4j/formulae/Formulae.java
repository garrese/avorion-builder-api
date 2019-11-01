package avuilder4j.formulae;

public class Formulae {

	public static double getSlotVolumeBarrier(int slots) {
		double base = 2000.0;
		double powBase = 2.5;
		double powModTill10 = 6;
		double pow = 0;
		if (slots > 10) {
			pow = slots - powModTill10;
		} else {
			switch (slots) {
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
			}
		}
		return base * Math.pow(powBase, pow);
	}
}
