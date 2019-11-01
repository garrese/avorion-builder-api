package avuilder4j.values;

import avuilder4j.entities.game.TypeLook;

public class Game {

	public static final Double BASE_MATERIAL_COST = 5.0;

	public static final TypeLook TYPE_LOOK_ZERO = new TypeLook(0, 0);

	public static final String DEFAULT_COLOR = "ffffffff";
	public static final Double DEFAULT_LENGTH = 2.0;
	public static final Integer DEFAULT_MATERIAL = Mats.IRON;
	public static final TypeLook DEFAULT_ORIENTATION = TYPE_LOOK_ZERO;
	public static final Integer DEFAULT_TYPE = Types.BLANK_HULL;

	public static final int MAX_LOOK = 5;
	public static final int MIN_LOOK = 0;
}
