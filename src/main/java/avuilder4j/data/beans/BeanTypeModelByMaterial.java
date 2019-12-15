package avuilder4j.data.beans;

import java.io.Serializable;
import java.util.Objects;

public class BeanTypeModelByMaterial implements Serializable {
	private static final long serialVersionUID = 4213877716842924589L;

	protected MapIndex index;
	protected Double creditCostMod;

	public BeanTypeModelByMaterial(MapIndex Index, Double creditCostMod) {
		this.index = Index;
		this.creditCostMod = creditCostMod;
	}

	public Double getCreditCostMod() { return creditCostMod; }

	public MapIndex getIndex() { return index; }

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
	public int hashCode() {
		return Objects.hash(creditCostMod, index);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanTypeModelByMaterial))
			return false;
		BeanTypeModelByMaterial other = (BeanTypeModelByMaterial) obj;
		return Objects.equals(creditCostMod, other.creditCostMod) && Objects.equals(index, other.index);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeModelByMaterial [index=");
		builder.append(index);
		builder.append(", creditCostMod=");
		builder.append(creditCostMod);
		builder.append("]");
		return builder.toString();
	}

}
