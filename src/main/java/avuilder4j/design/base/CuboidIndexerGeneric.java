package avuilder4j.design.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder4j.design.sub.dimensional.Lengths;
import avuilder4j.util.java.Nullable;

@SuppressWarnings("rawtypes")
public abstract class CuboidIndexerGeneric<B extends CuboidGeneric, S extends CuboidStructureGeneric<B, S>> {

	protected S structure;
	protected int indexCount;

	protected Lengths defaultLengths = new Lengths(2.0, 2.0, 2.0);

	public CuboidIndexerGeneric() {
		setStructure(getStructureInstance());
	}

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

	public B createBlock(String tags) {
		B b = createBlock();
		b.getTagator().addTags(tags);
		return b;
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

	protected void setStructure(S structure) { this.structure = structure; }

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

	public Lengths getDefaultLengths() { return Nullable.run(() -> defaultLengths.getCopy()); }

	public void setDefaultLengths(Lengths defaultLengths) { this.defaultLengths = defaultLengths; }

}
