package avuilder4j.design;

import java.util.Collection;

import avuilder4j.design.base.BlockFunctionalStructureGeneric;

public class Structure extends BlockFunctionalStructureGeneric<Block, Structure> {
	private static final long serialVersionUID = -7289127183511354727L;

	public Structure() {}

	public Structure(Collection<? extends Block> c) {
		super(c);
	}

	public Structure(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public Structure chain() {
		return this;
	}
}
