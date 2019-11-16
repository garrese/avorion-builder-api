package avuilder4j.values.loaders;

import avuilder4j.dtos.TypeStats;
import avuilder4j.dtos.TypeStatsFilled;
import avuilder4j.dtos.TypeStatsShaped;

public class Types extends EntityMap<TypeStats> {

	public static final int HULL = 1;
	public static final int BLANK_HULL = 2;
	public static final int ENGINE = 3;
	public static final int STONE = 4;
	public static final int CARGO_BAY = 5;
	public static final int CREW_QUARTERS = 6;
	public static final int THRUSTER = 7;
	public static final int ARMOR = 8;
	public static final int FRAMEWORK = 9;
	public static final int HANGAR = 10;
	public static final int DOCK = 11;
	public static final int TURRET_ROTATION_LOCK = 12;
	public static final int DIRECTIONAL_THRUSTER = 13;
	public static final int GYRO_ARRAY = 14;
	public static final int INERTIA_DAMPENER = 15;
	public static final int FLIGHT_RECORDER = 16;
	public static final int ASSEMBLY = 17;
	public static final int TORPEDO_LAUNCHER = 18;
	public static final int TORPEDO_STORAGE = 19;
	public static final int TURRET_BASE = 20;

	public static final int SHIELD_GENERATOR = 50;
	public static final int ENERGY_CONTAINER = 51;
	public static final int GENERATOR = 52;
	public static final int INTEGRITY_FIELD_GENERATOR = 53;
	public static final int COMPUTER_CORE = 54;
	public static final int HYPERSPACE_CORE = 55;
	public static final int TRANSPORTER = 56;
	public static final int ACADEMY = 57;
	public static final int CLONING_PODS = 58;
	public static final int SOLAR_PANEL = 60;
	public static final int LIGHT = 61;

	public static final int EDGE = 100;
	public static final int CORNER_1 = 101;
	public static final int CORNER_2 = 102;
	public static final int CORNER_3 = 103;
	public static final int EDGE_ARMOR = 104;
	public static final int CORNER_ARMOR_1 = 105;
	public static final int CORNER_ARMOR_2 = 106;
	public static final int CORNER_ARMOR_3 = 107;
	public static final int TWISTED_CORNER_1 = 108;
	public static final int TWISTED_CORNER_2 = 109;
	public static final int TWISTED_CORNER_ARMOR_1 = 110;
	public static final int TWISTED_CORNER_ARMOR_2 = 111;

	public static final int GLOW = 150;
	public static final int GLOW_EDGE = 151;
	public static final int GLOW_CORNER_1 = 152;
	public static final int GLOW_CORNER_2 = 153;
	public static final int GLOW_CORNER_3 = 154;
	public static final int GLOW_TWISTED_CORNER_1 = 155;
	public static final int GLOW_TWISTED_CORNER_2 = 156;

	public static final int GLASS = 170;
	public static final int GLASS_EDGE = 171;
	public static final int GLASS_CORNER_1 = 172;
	public static final int GLASS_CORNER_2 = 173;
	public static final int GLASS_CORNER_3 = 174;
	public static final int GLASS_TWISTED_CORNER_1 = 175;
	public static final int GLASS_TWISTED_CORNER_2 = 176;

	public static final int REFLECTOR = 180;
	public static final int REFLECTOR_EDGE = 181;
	public static final int REFLECTOR_CORNER_1 = 182;
	public static final int REFLECTOR_CORNER_2 = 183;
	public static final int REFLECTOR_CORNER_3 = 184;

	public static final int STONE_EDGE = 185;
	public static final int STONE_CORNER_1 = 186;
	public static final int STONE_CORNER_2 = 187;
	public static final int STONE_CORNER_3 = 188;

	public static final int HOLOGRAM = 190;
	public static final int HOLOGRAM_EDGE = 191;
	public static final int HOLOGRAM_CORNER_1 = 192;
	public static final int HOLOGRAM_CORNER_2 = 193;
	public static final int HOLOGRAM_CORNER_3 = 194;

	public static final int REFLECTOR_TWISTED_CORNER_1 = 195;
	public static final int REFLECTOR_TWISTED_CORNER_2 = 196;
	public static final int STONE_TWISTED_CORNER_1 = 197;
	public static final int STONE_TWISTED_CORNER_2 = 198;
	public static final int HOLOGRAM_TWISTED_CORNER_1 = 199;
	public static final int HOLOGRAM_TWISTED_CORNER_2 = 200;

	public static final int RICH_STONE = 510;
	public static final int RICH_STONE_EDGE = 511;
	public static final int RICH_STONE_CORNER_1 = 512;
	public static final int RICH_STONE_CORNER_2 = 513;
	public static final int RICH_STONE_CORNER_3 = 514;
	public static final int RICH_STONE_TWISTED_CORNER_1 = 515;
	public static final int RICH_STONE_TWISTED_CORNER_2 = 516;

	public static final int SUPER_RICH_STONE = 520;
	public static final int SUPER_RICH_STONE_EDGE = 521;
	public static final int SUPER_RICH_STONE_CORNER_1 = 522;
	public static final int SUPER_RICH_STONE_CORNER_2 = 523;
	public static final int SUPER_RICH_STONE_CORNER_3 = 524;
	public static final int SUPER_RICH_STONE_TWISTED_CORNER_1 = 525;
	public static final int SUPER_RICH_STONE_TWISTED_CORNER_2 = 526;

	public Types() {
		TypeStatsFilled t;

		t = getDefaultStats(HULL);
		add(t);
		addFilledVariant(EDGE, Shapes.EDGE, t);
		addFilledVariant(CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(BLANK_HULL);
		add(t);

		t = getDefaultStats(ENGINE);
		t.setDensityMod(0.5);
		t.setDurabilityMod(0.5);
		t.setCreditCostMod(2.5);
		t.setMaterialCostMod(2.5);
		t.setMechanics(10.0);
		t.setEngineers(100.0 / 3.0);
		add(t);

		t = getDefaultStats(STONE);
		t.setDensityMod(4.0);
		t.setCreditCostMod(0.0);
		t.setMaterialCostMod(0.3);
		t.setMechanics(0.0);
		t.setProcessingMod(0.0);
		add(t);
		addFilledVariant(STONE_EDGE, Shapes.EDGE, t);
		addFilledVariant(STONE_CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(STONE_CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(STONE_CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(STONE_TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(STONE_TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(CARGO_BAY);
		t.setDensityMod(1.0 / 3.0);
		t.setCreditCostMod(2.5);
		t.setMaterialCostMod(2.5);
		add(t);

		t = getDefaultStats(CREW_QUARTERS);
		t.setDensityMod(2.0 / 3.0);
		t.setCreditCostMod(2.25);
		t.setMaterialCostMod(2.25);
		add(t);

		t = getDefaultStats(THRUSTER);
		t.setDensityMod(0.5);
		t.setDurabilityMod(1.0 / 8.0);
		t.setCreditCostMod(1.5);
		t.setMaterialCostMod(1.5);
		t.setMechanics(10.0);
		t.setEngineers(20.0);
		add(t);
		addFilledVariant(DIRECTIONAL_THRUSTER, Shapes.FILLED, t);

		t = getDefaultStats(ARMOR);
		t.setDensityMod(1.0 + 2.0 / 3.0);
		t.setDurabilityMod(3.75);
		t.setCreditCostMod(1.0 / 3.0);
		t.setMaterialCostMod(1.0 + 2.0 / 3.0);
		t.setMechanics((1.0 + 2.0 / 3.0) / 10.0);
		add(t);
		addFilledVariant(EDGE_ARMOR, Shapes.EDGE, t);
		addFilledVariant(CORNER_ARMOR_1, Shapes.CORNER_1, t);
		addFilledVariant(CORNER_ARMOR_2, Shapes.CORNER_2, t);
		addFilledVariant(CORNER_ARMOR_3, Shapes.CORNER_3, t);
		addFilledVariant(TWISTED_CORNER_ARMOR_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(TWISTED_CORNER_ARMOR_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(FRAMEWORK);
		t.setDensityMod(1.0 / 1500);
		t.setDurabilityMod(1.0 / 800);
		t.setCreditCostMod(0.5);
		t.setMaterialCostMod(0.15);
		t.setMechanics(2.0 / 30.0);
		t.setProcessingMod(0.0);
		add(t);

		t = getDefaultStats(HANGAR);
		t.setDensityMod(2.0 / 30.0);
		t.setDurabilityMod(4.0 / 27.0);
		t.setCreditCostMod(4.5);
		t.setMaterialCostMod(10.0);
		t.setMechanics(2.0);
		add(t);

		t = getDefaultStats(DOCK);
		t.setDensityMod(1.0 / 3.0);
		t.setDurabilityMod(0.5);
		t.setCreditCostMod(4.5);
		t.setMaterialCostMod(4.5);
		add(t);

		t = getDefaultStats(TURRET_ROTATION_LOCK);
		t.setDurabilityMod(0.5);
		t.setCreditCostMod(2.0);
		t.setMaterialCostMod(2.0);
		add(t);

		t = getDefaultStats(GYRO_ARRAY);
		t.setDensityMod(1.0 / 6.0);
		t.setDurabilityMod(0.5);
		t.setCreditCostMod(5.0);
		t.setMaterialCostMod(5.0);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(INERTIA_DAMPENER);
		t.setDensityMod(0.5);
		t.setDurabilityMod(1.0 / 8.0);
		t.setCreditCostMod(30.0);
		t.setMaterialCostMod(30.0);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(FLIGHT_RECORDER);
		t.setDensityMod(0.6);
		t.setDurabilityMod(2.0);
		t.setCreditCostMod(20.0);
		t.setMaterialCostMod(20.0);
		t.setMechanics((1.0 + 2.0 / 3.0) / 10.0);
		add(t);

		t = getDefaultStats(ASSEMBLY);
		t.setDurabilityMod(0.5);
		t.setCreditCostMod(50.0);
		t.setMaterialCostMod(6.0);
		t.setMechanics(10.0 + 10.0 / 3.0);
		add(t);

		t = getDefaultStats(TORPEDO_LAUNCHER);
		t.setCreditCostMod(4.5);
		t.setMaterialCostMod(4.5);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(TORPEDO_STORAGE);
		t.setCreditCostMod(4.5);
		t.setMaterialCostMod(4.5);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(TURRET_BASE);
		t.setDensityMod(1.0 + 1.0 / 3.0);
		t.setDurabilityMod(2.0);
		t.setCreditCostMod(2.0);
		t.setMaterialCostMod(2.0);
		t.setMechanics(10.0 / 3.0);
		add(t);

		t = getDefaultStats(SHIELD_GENERATOR);
		t.setDensityMod(1.0 + 1.0 / 3.0);
		t.setDurabilityMod(0.125);
		t.setCreditCostMod(29.6639184667521);
		t.setMaterialCostMod(20.0);
		t.setMechanics(10.0);
		t.setEngineers(40.0);
		add(t);

		t = getDefaultStats(ENERGY_CONTAINER);
		t.setDurabilityMod(0.25);
		t.setCreditCostMod(3.5);
		t.setMaterialCostMod(3.5);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(GENERATOR);
		t.setDensityMod(1.0 + 1.0 / 3);
		t.setDurabilityMod(0.125);
		t.setCreditCostMod(16.6904039029617);
		t.setMaterialCostMod(10.0);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(INTEGRITY_FIELD_GENERATOR);
		t.setDensityMod(1.0 + 1.0 / 3);
		t.setDurabilityMod(0.125);
		t.setCreditCostMod(8.71689112327899);
		t.setMaterialCostMod(5.0);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(COMPUTER_CORE);
		t.setDensityMod(1.0 + 1.0 / 3);
		t.setDurabilityMod(0.125);
		t.setCreditCostMod(19.867564493116);
		t.setMaterialCostMod(5.0);
		t.setMechanics(2.0);
		t.setProcessingMod(5.0);
		add(t);

		t = getDefaultStats(HYPERSPACE_CORE);
		t.setDensityMod(1.33333333333333);
		t.setDurabilityMod(0.125);
		t.setCreditCostMod(64.8675637794729);
		t.setMaterialCostMod(50.0);
		t.setMechanics(10.0);
		add(t);

		t = getDefaultStats(TRANSPORTER);// TODO
//		t.setDensityMod(densityMod);
//		t.setDurabilityMod(durabilityMod);
//		t.setCreditCostMod(creditCostMod);
//		t.setMaterialCostMod(50.0);
//		t.setMechanics(10.0);
//		add(t);

		t = getDefaultStats(ACADEMY);// TODO
//		t.setDensityMod(densityMod);
//		t.setDurabilityMod(durabilityMod);
//		t.setCreditCostMod(creditCostMod);
//		t.setMaterialCostMod(materialCostMod);
//		t.setMechanics(mechanics);
//		t.setEngineers(engineers);
//		t.setProcessingMod(processingMod);
//		t.setVolumeMod(volumeMod);
//		add(t);

		t = getDefaultStats(CLONING_PODS);// TODO
//		t.setDensityMod(densityMod);
//		t.setDurabilityMod(durabilityMod);
//		t.setCreditCostMod(creditCostMod);
//		t.setMaterialCostMod(materialCostMod);
//		t.setMechanics(mechanics);
//		t.setEngineers(engineers);
//		t.setProcessingMod(processingMod);
//		t.setVolumeMod(volumeMod);
//		add(t);

		t = getDefaultStats(SOLAR_PANEL);
		t.setDensityMod(1.0 / 1.6);
		t.setCreditCostMod(4.5);
		add(t);

		t = getDefaultStats(LIGHT);
		t.setDensityMod(1.0 / 3.0);
		add(t);

		t = getDefaultStats(GLOW);
		t.setDensityMod(1.0 / 3.0);
		t.setMechanics(8.0 / 30.0);
		add(t);
		addFilledVariant(GLOW_EDGE, Shapes.EDGE, t);
		addFilledVariant(GLOW_CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(GLOW_CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(GLOW_CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(GLOW_TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(GLOW_TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(GLASS);
		t.setDensityMod(1.0 / 3.0);
		t.setDurabilityMod(0.25);
		t.setMaterialCostMod(0.0);
		t.setMechanics(8.0 / 30.0);
		t.setProcessingMod(0.0);
		add(t);
		addFilledVariant(GLASS_EDGE, Shapes.EDGE, t);
		addFilledVariant(GLASS_CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(GLASS_CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(GLASS_CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(GLASS_TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(GLASS_TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(REFLECTOR); // TODO
		add(t);
		addFilledVariant(REFLECTOR_EDGE, Shapes.EDGE, t);
		addFilledVariant(REFLECTOR_CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(REFLECTOR_CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(REFLECTOR_CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(REFLECTOR_TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(REFLECTOR_TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(HOLOGRAM);
		t.setDensityMod(0.0);
		t.setDurabilityMod(0.0);
		t.setCreditCostMod(1.0 / 60.0);
		t.setMaterialCostMod(0.0);
		t.setMechanics(0.0);
		t.setProcessingMod(0.0);
		t.setVolumeMod(0.0);
		add(t);
		addFilledVariant(HOLOGRAM_EDGE, Shapes.EDGE, t);
		addFilledVariant(HOLOGRAM_CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(HOLOGRAM_CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(HOLOGRAM_CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(HOLOGRAM_TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(HOLOGRAM_TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(RICH_STONE);
		t.setDensityMod(4.0);
		t.setDurabilityMod(1.5);
		t.setCreditCostMod(0.0);
		t.setMaterialCostMod(0.15);
		t.setMechanics(0.0);
		t.setProcessingMod(0.0);
		add(t);
		addFilledVariant(RICH_STONE_EDGE, Shapes.EDGE, t);
		addFilledVariant(RICH_STONE_CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(RICH_STONE_CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(RICH_STONE_CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(RICH_STONE_TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(RICH_STONE_TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

		t = getDefaultStats(SUPER_RICH_STONE); // TODO
//		t.setDensityMod(0.0);
//		t.setDurabilityMod(0.0);
//		t.setCreditCostMod(1.0 / 60.0);
//		t.setMaterialCostMod(0.0);
//		t.setMechanics(0.0);
//		t.setProcessingMod(0.0);
//		t.setVolumeMod(0.0);
		add(t);
		addFilledVariant(SUPER_RICH_STONE_EDGE, Shapes.EDGE, t);
		addFilledVariant(SUPER_RICH_STONE_CORNER_1, Shapes.CORNER_1, t);
		addFilledVariant(SUPER_RICH_STONE_CORNER_2, Shapes.CORNER_2, t);
		addFilledVariant(SUPER_RICH_STONE_CORNER_3, Shapes.CORNER_3, t);
		addFilledVariant(SUPER_RICH_STONE_TWISTED_CORNER_1, Shapes.TWISTED_CORNER_1, t);
		addFilledVariant(SUPER_RICH_STONE_TWISTED_CORNER_2, Shapes.TWISTED_CORNER_2, t);

	}

	public TypeStatsFilled getDefaultStats(Integer index) {

		TypeStatsFilled def = new TypeStatsFilled(index);
		def.setShape(Shapes.FILLED);
		def.setDensityMod(1.0);
		def.setDurabilityMod(1.0);
		def.setCreditCostMod(1.0);
		def.setMaterialCostMod(1.0);
		def.setMechanics(5.0);
		def.setProcessingMod(1.0);
		def.setVolumeMod(1.0);

		return def;
	}

	@Override
	public void add(TypeStats typeStats) {
		typeStats.close();
		super.add(typeStats);
	}

	public void addFilledVariant(Integer index, Integer shapeIndex, TypeStatsFilled filled) {
		add(new TypeStatsShaped(index, shapeIndex, filled));
	}

}
