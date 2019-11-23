package avuilder4j.data;

public class TypeModelByMaterial {

	protected MapIndex mapIndex;
	protected Double creditCostMod;

	public TypeModelByMaterial(MapIndex mapIndex, Double creditCostMod) {
		this.mapIndex = mapIndex;
		this.creditCostMod = creditCostMod;
	}

	public Double getCreditCostMod() { return creditCostMod; }

	public MapIndex getMapIndex() { return mapIndex; }

	public static class MapIndex {

		protected Integer typeModelIndex;
		protected Integer materialIndex;

		public MapIndex(Integer typeModelIndex, Integer materialIndex) {
			this.typeModelIndex = typeModelIndex;
			this.materialIndex = materialIndex;
		}

		public Integer getTypeModelIndex() { return typeModelIndex; }

		public Integer getMaterialIndex() { return materialIndex; }

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[typeModelIndex=");
			builder.append(typeModelIndex);
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
			result = prime * result + ((typeModelIndex == null) ? 0 : typeModelIndex.hashCode());
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
			if (typeModelIndex == null) {
				if (other.typeModelIndex != null)
					return false;
			} else if (!typeModelIndex.equals(other.typeModelIndex))
				return false;
			return true;
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeModelByMaterial [mapIndex=");
		builder.append(mapIndex);
		builder.append(", creditCostMod=");
		builder.append(creditCostMod);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCostMod == null) ? 0 : creditCostMod.hashCode());
		result = prime * result + ((mapIndex == null) ? 0 : mapIndex.hashCode());
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
		TypeModelByMaterial other = (TypeModelByMaterial) obj;
		if (creditCostMod == null) {
			if (other.creditCostMod != null)
				return false;
		} else if (!creditCostMod.equals(other.creditCostMod))
			return false;
		if (mapIndex == null) {
			if (other.mapIndex != null)
				return false;
		} else if (!mapIndex.equals(other.mapIndex))
			return false;
		return true;
	}

}
