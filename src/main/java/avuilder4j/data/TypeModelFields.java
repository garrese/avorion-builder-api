package avuilder4j.data;

public abstract class TypeModelFields {

	protected Integer index;
	protected String name;
	protected Double densityMod;
	protected Double durabilityMod;
	protected Double materialCostMod;
	protected Double mechanics;
	protected Double engineers;
	protected Double processingMod;
	protected Double volumeStatMod;

	public Integer getIndex() { return index; }

	public String getName() { return name; }

	public Double getDensityMod() { return densityMod; }

	public Double getDurabilityMod() { return durabilityMod; }

	public Double getMaterialCostMod() { return materialCostMod; }

	public Double getMechanics() { return mechanics; }

	public Double getEngineers() { return engineers; }

	public Double getProcessingMod() { return processingMod; }

	public Double getVolumeStatMod() { return volumeStatMod; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeModelFields [index=");
		builder.append(index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", densityMod=");
		builder.append(densityMod);
		builder.append(", durabilityMod=");
		builder.append(durabilityMod);
		builder.append(", materialCostMod=");
		builder.append(materialCostMod);
		builder.append(", mechanics=");
		builder.append(mechanics);
		builder.append(", engineers=");
		builder.append(engineers);
		builder.append(", processingMod=");
		builder.append(processingMod);
		builder.append(", volumeStatMod=");
		builder.append(volumeStatMod);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((densityMod == null) ? 0 : densityMod.hashCode());
		result = prime * result + ((durabilityMod == null) ? 0 : durabilityMod.hashCode());
		result = prime * result + ((engineers == null) ? 0 : engineers.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((materialCostMod == null) ? 0 : materialCostMod.hashCode());
		result = prime * result + ((mechanics == null) ? 0 : mechanics.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((processingMod == null) ? 0 : processingMod.hashCode());
		result = prime * result + ((volumeStatMod == null) ? 0 : volumeStatMod.hashCode());
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
		TypeModelFields other = (TypeModelFields) obj;
		if (densityMod == null) {
			if (other.densityMod != null)
				return false;
		} else if (!densityMod.equals(other.densityMod))
			return false;
		if (durabilityMod == null) {
			if (other.durabilityMod != null)
				return false;
		} else if (!durabilityMod.equals(other.durabilityMod))
			return false;
		if (engineers == null) {
			if (other.engineers != null)
				return false;
		} else if (!engineers.equals(other.engineers))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (materialCostMod == null) {
			if (other.materialCostMod != null)
				return false;
		} else if (!materialCostMod.equals(other.materialCostMod))
			return false;
		if (mechanics == null) {
			if (other.mechanics != null)
				return false;
		} else if (!mechanics.equals(other.mechanics))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (processingMod == null) {
			if (other.processingMod != null)
				return false;
		} else if (!processingMod.equals(other.processingMod))
			return false;
		if (volumeStatMod == null) {
			if (other.volumeStatMod != null)
				return false;
		} else if (!volumeStatMod.equals(other.volumeStatMod))
			return false;
		return true;
	}

}
