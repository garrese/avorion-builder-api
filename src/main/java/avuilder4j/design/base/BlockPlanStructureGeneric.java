package avuilder4j.design.base;

import java.util.List;
import java.util.stream.Collectors;

import avuilder4j.util.java.NullSafe;

@SuppressWarnings("rawtypes")
public class BlockPlanStructureGeneric<T extends BlockPlanGeneric> extends CuboidStructureGeneric<T> {
	private static final long serialVersionUID = 185965756164498588L;

	public List<T> findByColor(String color) {
		return this.stream().filter(b -> NullSafe.run(() -> b.getColor().equals(color), false))
				.collect(Collectors.toList());
	}

}
