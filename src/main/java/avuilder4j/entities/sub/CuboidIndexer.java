package avuilder4j.entities.sub;

import avuilder4j.generics.CuboidIndexerGeneric;

public class CuboidIndexer extends CuboidIndexerGeneric<Cuboid, CuboidStructure> {

	@Override
	protected Cuboid getBlockInstance() { return new Cuboid(); }

	@Override
	protected CuboidStructure getStructureInstance() { return new CuboidStructure(); }

}
