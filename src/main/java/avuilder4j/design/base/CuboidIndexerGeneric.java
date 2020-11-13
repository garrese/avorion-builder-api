package avuilder4j.design.base;

import java.util.Collections;
import java.util.List;

import avuilder4j.design.Mirror;
import avuilder4j.design.sub.dimensional.Lengths;

@SuppressWarnings("rawtypes")
public abstract class CuboidIndexerGeneric<B extends CuboidGeneric, S extends CuboidStructureGeneric<B, S>, I extends CuboidIndexerGeneric> {

	protected Lengths defaultLengths = new Lengths(2.0, 2.0, 2.0);
	protected int indexCount;

	protected S structure;

	protected Mirror mirror = new Mirror();

	public CuboidIndexerGeneric() {
		setStructure(getStructureInstance());
	}

	public abstract I chain();

	public I clearBlocks() {
		structure.clear();
		indexCount = 0;
		return chain();
	}

	public B createBlock() {
		B cuboid = createBlockBlanck();
		cuboid.setLengths(new Lengths(getDefaultLengths()));
		return cuboid;
	}

	@SuppressWarnings("unchecked")
	public B createBlock(B parent) {
		B b = createBlockBlanck();
		b.setParent(parent);
		return b;
	}

	public B createBlock(String tags) {
		B b = createBlock();
		b.getTagator().addTags(tags);
		return b;
	}

	public B createBlockBlanck() {
		B b = getBlockInstance();
		indexBlock(b);
		return b;
	}

	protected abstract B getBlockInstance();

	public Lengths getDefaultLengths() { return defaultLengths; }

	public String getDesignReport() {
		String report = "";
		for (int i = 0; i < structure.size(); i++) {
			report += structure.get(i) + "\n";
		}
		report += "Total blocks= " + structure.size();
		return report;
	}

	public int getIndexCount() { return indexCount; }

	public B getLast() { return getStructure().getLast(); }

	public B getPenultimate() { return getStructure().getPenultimate(); }

	public S getStructure() { return structure; }

	public Mirror getMirror() { return mirror; }

	public void setMirror(Mirror mirror) { this.mirror = mirror; }

	protected abstract S getStructureInstance();

	public B importBlock(B block) {
		indexImportedBlock(block);
		return block;
	}

	public I importBlocks(List<B> blocks) {
		Collections.sort(blocks, (o1, o2) -> o1.getIndex().compareTo(o2.getIndex()));
		for (B block : blocks) {
			indexImportedBlock(block);
		}
		return chain();
	}

	protected I indexBlock(B block) {
		indexCount++;
		block.setIndex(indexCount);
		structure.add(block);
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

	public I setDefaultLengths(Double x, Double y, Double z) {
		setDefaultLengths(new Lengths(x, y, z));
		return chain();
	}

	public I setDefaultLengths(Lengths defaultLengths) {
		this.defaultLengths = defaultLengths;
		return chain();
	}

	protected I setStructure(S structure) {
		this.structure = structure;
		return chain();
	}

//	public B reflect(B block) {
//		return reflect(block, true);
//	}
//
//	public B reflect(B block, boolean findParent) {
//		B b = null;
//		if (findParent)
//			b = getMirror().<B>reflect(block, getStructure());
//		else
//			b = getMirror().<B>reflect(block);
//
//		indexBlock(b);
//		return b;
//	}

}
