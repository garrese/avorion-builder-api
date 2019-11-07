package avuilder4j.spatial;

import avuilder4j.spatial.generics.CuboidIndexerGeneric;

public class CuboidIndexer extends CuboidIndexerGeneric<Cuboid, CuboidStructure> {

	@Override
	protected Cuboid getCuboidInstance() { return new Cuboid(); }

	@Override
	protected CuboidStructure getStructureInstance() { return new CuboidStructure(); }

}
