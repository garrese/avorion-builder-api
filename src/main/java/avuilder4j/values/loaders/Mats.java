package avuilder4j.values.loaders;

import avuilder4j.dtos.MaterialStats;
import avuilder4j.error.AvErrors;
import avuilder4j.utils.Formulae;

public class Mats extends Loader<MaterialStats> {

	public static final int AVORION = 6;
	public static final int IRON = 0;
	public static final int NAONITE = 2;
	public static final int OGONITE = 5;
	public static final int TITANIUM = 1;
	public static final int TRINIUM = 3;
	public static final int XANION = 4;

	public static final int[] ALL = {//@formatter:off
			IRON, 
			TITANIUM, 
			NAONITE, 
			TRINIUM, 
			XANION, 
			OGONITE, 
			AVORION
		};//@formatter:on

	public static final int MATERIAL_COST_BASE = 5;

	public static int getMaterialDensity(int materialIndex) {
		switch (materialIndex) {
		case Mats.IRON:
			return 51;
		case Mats.TITANIUM:
			return 30;
		case Mats.NAONITE:
			return 33;
		case Mats.TRINIUM:
			return 21;
		case Mats.XANION:
			return 27;
		case Mats.OGONITE:
			return 45;
		case Mats.AVORION:
			return 36;
		default:
			throw new IllegalArgumentException(AvErrors.ARGUMENT_CASE_NOT_RECOGNIZED);
		}
	}

	public static String getMaterialName(int materialIndex) {
		switch (materialIndex) {
		case Mats.IRON:
			return "Iron";
		case Mats.TITANIUM:
			return "Titanium";
		case Mats.NAONITE:
			return "Naonite";
		case Mats.TRINIUM:
			return "Trinium";
		case Mats.XANION:
			return "Xanion";
		case Mats.OGONITE:
			return "Ogonite";
		case Mats.AVORION:
			return "Avorion";
		default:
			throw new IllegalArgumentException(AvErrors.ARGUMENT_CASE_NOT_RECOGNIZED);
		}
	}

	public Mats() {

		for (int matIndex : Mats.ALL) {

			MaterialStats mat = new MaterialStats(matIndex);
			mat.setName(getMaterialName(matIndex));
			mat.setBaseDensity(getMaterialDensity(matIndex));
			mat.setBaseDurability(Formulae.getMaterialDurability(matIndex));
			mat.setBaseCreditCost(Formulae.getMaterialCreditCost(matIndex));
			mat.setBaseMaterialCost(MATERIAL_COST_BASE);
			add(mat);
		}

	}

	@Override
	public void add(MaterialStats materialStats) {
		materialStats.close();
		super.add(materialStats);
	}

}
