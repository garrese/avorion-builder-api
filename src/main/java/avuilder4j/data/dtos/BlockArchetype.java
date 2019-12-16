package avuilder4j.data.dtos;

public class BlockArchetype extends BlockArchetypeFields {
	private static final long serialVersionUID = -4304846556756622481L;

	public BlockArchetype(BlockArchetypeParams params) {
		this.materialIndex = params.getMaterialIndex();
		this.materialName = params.getMaterialName();

		this.typeIndex = params.getTypeIndex();
		this.typeModelIndex = params.getTypeModelIndex();
		this.shapeIdx = params.getShapeIndex();

		this.collisionReduction = params.getCollisionReduction();
		this.typeModelName = params.getTypeModelName();
		this.mechanics = params.getMechanics();
		this.engineers = params.getEngineers();
		this.process = params.getProcess();
		this.hasVolume = params.getHasVolume();

		this.shapeName = params.getShapeName();
		this.cuboidFilledIn = params.getCuboidFilledIn();
		this.symmetricShape = params.getSymmetricShapeIndex();

		this.density = params.getDensity();
		this.durability = params.getDurability();
		this.materialCost = params.getMaterialCost();
		this.creditCost = params.getCreditCost();
		this.effects = params.getEffects();
	}

	public static class MapIndex {

		protected Integer typeIndex;
		protected Integer materialIndex;

		public MapIndex(Integer typeModelIndex, Integer materialIndex) {
			this.typeIndex = typeModelIndex;
			this.materialIndex = materialIndex;
		}

		public Integer getTypeIndex() { return typeIndex; }

		public Integer getMaterialIndex() { return materialIndex; }

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[typeIndex=");
			builder.append(typeIndex);
			builder.append(", materialIndex=");
			builder.append(materialIndex);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((materialIndex == null) ? 0 : materialIndex.hashCode());
			result = prime * result + ((typeIndex == null) ? 0 : typeIndex.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MapIndex other = (MapIndex) obj;
			if (materialIndex == null) {
				if (other.materialIndex != null)
					return false;
			} else if (!materialIndex.equals(other.materialIndex))
				return false;
			if (typeIndex == null) {
				if (other.typeIndex != null)
					return false;
			} else if (!typeIndex.equals(other.typeIndex))
				return false;
			return true;
		}
	}

}
