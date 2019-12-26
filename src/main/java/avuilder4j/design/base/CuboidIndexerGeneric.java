package avuilder4j.design.base;

import java.util.Collections;
import java.util.List;

import avuilder4j.design.sub.dimensional.Lengths;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Nullable;

@SuppressWarnings("rawtypes")
public abstract class CuboidIndexerGeneric<B extends CuboidGeneric, S extends CuboidStructureGeneric<B, S>, I extends CuboidIndexerGeneric>
		implements Chainable<I> {

	protected S structure;
	protected int indexCount;

	protected Lengths defaultLengths = new Lengths(2.0, 2.0, 2.0);

	public CuboidIndexerGeneric() {
		setStructure(getStructureInstance());
	}

	protected I indexBlock(B block) {
		indexCount++;
		block.setIndex(indexCount);
		structure.add(block);
		return chain();
	}

	@Override
	public abstract I chain();

	public B createBlanckBlock() {
		B b = getBlockInstance();
		indexBlock(b);
		return b;
	}

	protected abstract B getBlockInstance();

	protected abstract S getStructureInstance();

	public B createBlock() {
		B cuboid = createBlanckBlock();
		cuboid.setLengths(Nullable.run(() -> getDefaultLengths().getCopy()));
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

	public I importBlocks(List<B> blocks) {
		Collections.sort(blocks, (o1, o2) -> o1.getIndex().compareTo(o2.getIndex()));
		for (B block : blocks) {
			indexImportedBlock(block);
		}
		return chain();
	}

	public B importBlock(B block) {
		indexImportedBlock(block);
		return block;
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

	public I clearBlocks() {
		structure.clear();
		indexCount = 0;
		return chain();
	}

	protected I indexImportedBlock(B block) {
		if (block != null) {
			if (block.getIndex() == null || block.getIndex() <= indexCount) {
				indexBlock(block);
			} else {
				indexCount = block.getIndex();
				structure.add(block);
			}
		}
		return chain();
	}

	public S getStructure() { return structure; }

	protected I setStructure(S structure) {
		this.structure = structure;
		return chain();
	}

	public int getIndexCount() { return indexCount; }

	public String getDesignReport() {
		String report = "";
		for (int i = 0; i < structure.size(); i++) {
			report += structure.get(i) + "\n";
		}
		report += "Total blocks= " + structure.size();
		return report;
	}

	public Lengths getDefaultLengths() { return defaultLengths; }

	public I setDefaultLengths(Double x, Double y, Double z) {
		setDefaultLengths(new Lengths(x, y, z));
		return chain();
	}

	public I setDefaultLengths(Lengths defaultLengths) {
		this.defaultLengths = defaultLengths;
		return chain();
	}

}
