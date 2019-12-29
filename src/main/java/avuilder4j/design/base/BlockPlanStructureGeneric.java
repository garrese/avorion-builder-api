package avuilder4j.design.base;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import avuilder4j.util.java.Nullable;

@SuppressWarnings("rawtypes")
public abstract class BlockPlanStructureGeneric<T extends BlockPlanGeneric, S extends BlockPlanStructureGeneric>
		extends CuboidStructureGeneric<T, S> {
	private static final long serialVersionUID = 185965756164498588L;

	public BlockPlanStructureGeneric() {
		super();
	}

	public BlockPlanStructureGeneric(Collection<? extends T> c) {
		super(c);
	}

	public BlockPlanStructureGeneric(int initialCapacity) {
		super(initialCapacity);
	}

	public List<T> findByColor(String color) {
		return this.stream().filter(b -> Nullable.m(() -> b.getColor().equals(color), false))
				.collect(Collectors.toList());
	}

}
