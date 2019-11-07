package avuilder4j.spatial.generics;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder4j.spatial.auxs.Lengths;

public abstract class CuboidIndexerGeneric<C extends CuboidGeneric, S extends CuboidStructureGeneric<C>> {

	private S structure = getStructureInstance();
	private int indexCount;

	protected Lengths defaultLengths = new Lengths(2, 2, 2);

	public void indexBlock(C block) {
		indexCount++;
		block.setIndex(indexCount);
		structure.add(block);
	}

	public C createBlanckCuboid() {
		C b = getCuboidInstance();
		indexBlock(b);
		return b;
	}

	protected abstract C getCuboidInstance();

	protected abstract S getStructureInstance();

	public C createCuboid() {
		C cuboid = createBlanckCuboid();
		cuboid.setLengths(getDefaultLengths());
		return cuboid;
	}

	@SuppressWarnings("unchecked")
	public C createBlock(C parent) {
		C b = createBlanckCuboid();
		b.setParent(parent);
		return b;
	}

	public void importBlocks(List<C> blocks) {
		Collections.sort(blocks, (o1, o2) -> o1.getIndex().compareTo(o2.getIndex()));
		for (C block : blocks) {
			indexImportedBlock(block);
		}
	}

	public void importBlock(C block) {
		indexImportedBlock(block);
	}

	public boolean remove(C block) {
		return structure.remove(block);
	}

	public int remove(List<C> blocks) {
		int removed = 0;
		for (C block : blocks) {
			if (this.structure.remove(block))
				removed++;
		}
		return removed;
	}

	public void clearBlocks() {
		structure.clear();
		indexCount = 0;
	}

	protected void indexImportedBlock(C block) {
		if (block != null) {
			if (block.getIndex() == null || block.getIndex() <= indexCount) {
				indexBlock(block);
			} else {
				indexCount = block.getIndex();
				structure.add(block);
			}
		}
	}

	public S getStructure() { return structure; }

	public int getIndexCount() { return indexCount; }

	public String getDesignReport() {
		String report = "";
		for (int i = 0; i < structure.size(); i++) {
			report += structure.get(i) + "\n";
		}
		report += "Total blocks= " + structure.size();
		return report;
	}

	public void getIndexesMap() {
		Map<Integer, Integer> indexes = new HashMap<>();
		for (C block : structure) {
			Integer index = block.getIndex();
			if (index != null)
				indexes.put(index, index);
		}
	}

	/**
	 * Gets the {@link #defaultLengths}.
	 * 
	 * @return the {@link #defaultLengths}.
	 */
	public Lengths getDefaultLengths() { return defaultLengths; }

	/**
	 * Sets the {@link #defaultLengths}.
	 * 
	 * @param defaultLengths the {@link #defaultLengths} to set.
	 */
	public void setDefaultLengths(Lengths defaultLengths) { this.defaultLengths = defaultLengths; }

}
