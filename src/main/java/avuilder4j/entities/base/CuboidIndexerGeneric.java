package avuilder4j.entities.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder4j.dtos.Lengths;

@SuppressWarnings("rawtypes")
public abstract class CuboidIndexerGeneric<B extends CuboidGeneric, S extends CuboidStructureGeneric<B>> {

	protected S structure = getStructureInstance();
	protected int indexCount;

	protected Lengths defaultLengths = new Lengths(2, 2, 2);

	public void indexBlock(B block) {
		indexCount++;
		block.setIndex(indexCount);
		structure.add(block);
	}

	public B createBlanckBlock() {
		B b = getBlockInstance();
		indexBlock(b);
		return b;
	}

	protected abstract B getBlockInstance();

	protected abstract S getStructureInstance();

	public B createBlock() {
		B cuboid = createBlanckBlock();
		cuboid.setLengths(getDefaultLengths());
		return cuboid;
	}

	@SuppressWarnings("unchecked")
	public B createBlock(B parent) {
		B b = createBlanckBlock();
		b.setParent(parent);
		return b;
	}

	public void importBlocks(List<B> blocks) {
		Collections.sort(blocks, (o1, o2) -> o1.getIndex().compareTo(o2.getIndex()));
		for (B block : blocks) {
			indexImportedBlock(block);
		}
	}

	public void importBlock(B block) {
		indexImportedBlock(block);
	}

	public boolean remove(B block) {
		return structure.remove(block);
	}

	public int remove(List<B> blocks) {
		int removed = 0;
		for (B block : blocks) {
			if (this.structure.remove(block))
				removed++;
		}
		return removed;
	}

	public void clearBlocks() {
		structure.clear();
		indexCount = 0;
	}

	protected void indexImportedBlock(B block) {
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
		for (B block : structure) {
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
