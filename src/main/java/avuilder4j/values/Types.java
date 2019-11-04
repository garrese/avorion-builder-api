package avuilder4j.values;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import avuilder4j.entities.game.TypeLook;
import avuilder4j.formulae.TypeStats;

public class Types {

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
	public static final int ENERGY_CONTAINER = 51;
	public static final int GENERATOR = 52;
	public static final int INTEGRITY_FIELD_GENERATOR = 53;
	public static final int SHIELD_GENERATOR = 50;
	public static final int HYPERSPACE_CORE = 55;
	public static final int COMPUTER_CORE = 54;
	public static final int ACADEMY = 57;
	public static final int TRANSPORTER = 56;
	public static final int CLONING_PODS = 58;
	public static final int SOLAR_PANEL = 60;
	public static final int LIGHT = 61;
	public static final int GLOW = 150;
	public static final int GLASS = 170;
	public static final int REFLECTOR = 180;
	public static final int HOLOGRAM = 190;
	public static final int RICH_STONE = 510;
	public static final int SUPER_RICH_STONE = 520;

	public static final TypeLook DIRECTIONAL_THRUSTER_X = new TypeLook(0, 3);
	public static final TypeLook DIRECTIONAL_THRUSTER_Y = new TypeLook(2, 0);
	public static final TypeLook DIRECTIONAL_THRUSTER_Z = new TypeLook(5, 3);
	public static final TypeLook GYRO_ARRAY_AROUND_X = new TypeLook(1, 2);
	public static final TypeLook GYRO_ARRAY_AROUND_Y = new TypeLook(2, 0);
	public static final TypeLook GYRO_ARRAY_AROUND_Z = new TypeLook(5, 2);

	protected Map<Integer, TypeStats> typeBlocks;

	public Types() {
		Map<Integer, TypeStats> m = new HashMap<Integer, TypeStats>();
		m.put(INTEGRITY_FIELD_GENERATOR, new TypeStats(INTEGRITY_FIELD_GENERATOR, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
		this.typeBlocks = Collections.unmodifiableMap(m);
	}

	public Map<Integer, TypeStats> getAllMaterials() {
		return typeBlocks;
	}

	public TypeStats getMaterial(Integer typeIndex) {
		return typeBlocks.get(typeIndex);
	}

}
