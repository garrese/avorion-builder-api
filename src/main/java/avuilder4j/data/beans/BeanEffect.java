package avuilder4j.data.beans;

import java.util.Objects;

public class BeanEffect {

	protected MapIndex index;
	protected Double value;

	public BeanEffect(MapIndex index, Double value) {
		this.index = index;
		this.value = value;
	}

	public MapIndex getIndex() { return index; }

	public Double getValue() { return value; }

	@Override
	public int hashCode() {
		return Objects.hash(index, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanEffect))
			return false;
		BeanEffect other = (BeanEffect) obj;
		return Objects.equals(index, other.index) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Effect [index=");
		builder.append(index);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	public static class MapIndex {

		protected Integer typeModelIndex;
		protected Integer materialIndex;
		protected Integer n;

		public MapIndex(Integer typeModelIndex, Integer materialIndex, Integer n) {
			this.typeModelIndex = typeModelIndex;
			this.materialIndex = materialIndex;
			this.n = n;
		}

		public Integer getTypeModelIndex() { return typeModelIndex; }

		public Integer getMaterialIndex() { return materialIndex; }

		public Integer getN() { return n; }

		@Override
		public int hashCode() {
			return Objects.hash(materialIndex, n, typeModelIndex);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof MapIndex))
				return false;
			MapIndex other = (MapIndex) obj;
			return Objects.equals(materialIndex, other.materialIndex) && Objects.equals(n, other.n)
					&& Objects.equals(typeModelIndex, other.typeModelIndex);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[typeModelIndex=");
			builder.append(typeModelIndex);
			builder.append(", materialIndex=");
			builder.append(materialIndex);
			builder.append(", n=");
			builder.append(n);
			builder.append("]");
			return builder.toString();
		}

	}

}
