package avuilder4j.utils;

import java.util.ArrayList;

public class AvUtils {

	public static int[] findMissingIds(int[] ids, int[] allIds) {
		ArrayList<Integer> missed = new ArrayList<Integer>();
		for (int id : ids) {
			boolean found = false;
			for (int idRef : allIds) {
				if (id == idRef)
					found = true;
			}
			if (!found)
				missed.add(id);
		}

		int[] array = new int[missed.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = missed.get(i);
		}
		return array;
	}

//	public static final double VOLUME_S01 = 10;
//	public static final double VOLUME_S02 = 51;
//	public static final double VOLUME_S03 = 128;
//	public static final double VOLUME_S04 = 320;
//	public static final double VOLUME_S05 = 800;
//	public static final double VOLUME_S06 = 2000;
//	public static final double VOLUME_S07 = 5000;
//	public static final double VOLUME_S08 = 12500;
//	public static final double VOLUME_S09 = 19764;
//	public static final double VOLUME_S10 = 31250;
//	public static final double VOLUME_S11 = 43065;
//	public static final double VOLUME_S12 = 59348;
//	public static final double VOLUME_S13 = 78125;
//	public static final double VOLUME_S14 = 107554;
//	public static final double VOLUME_S15 = 148371;
}
