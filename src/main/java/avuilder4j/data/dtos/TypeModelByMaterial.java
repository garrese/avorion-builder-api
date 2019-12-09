package avuilder4j.data.dtos;

import java.io.Serializable;
import java.util.Objects;

public class TypeModelByMaterial implements Serializable {
	private static final long serialVersionUID = 4213877716842924589L;

	protected MapIndex mapIndex;
	protected Double creditCostMod;
	protected Double effect;
	protected Double effects;

	public TypeModelByMaterial(MapIndex mapIndex, Double creditCostMod, Double effect, Double effect2) {
		this.mapIndex = mapIndex;
		this.creditCostMod = creditCostMod;
		this.effect = effect;
		this.effects = effect2;
	}

	public Double getCreditCostMod() { return creditCostMod; }

	public MapIndex getMapIndex() { return mapIndex; }

	public Double getEffect() { return effect; }

	public Double getEffect2() { return effects; }

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
		builder.append(", effect=");
		builder.append(effect);
		builder.append(", effectSecond=");
		builder.append(effects);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditCostMod, effect, effects, mapIndex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TypeModelByMaterial))
			return false;
		TypeModelByMaterial other = (TypeModelByMaterial) obj;
		return Objects.equals(creditCostMod, other.creditCostMod) && Objects.equals(effect, other.effect)
				&& Objects.equals(effects, other.effects) && Objects.equals(mapIndex, other.mapIndex);
	}

}
