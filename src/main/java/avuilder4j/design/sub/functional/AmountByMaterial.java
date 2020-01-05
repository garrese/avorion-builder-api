package avuilder4j.design.sub.functional;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import avuilder4j.data.DataMaps;
import avuilder4j.data.beans.BeanMaterial;
import avuilder4j.util.java.Nullable;

public class AmountByMaterial extends AmountBy<AmountByMaterial, Integer, BeanMaterial> {
	private static final long serialVersionUID = 1232491739089084616L;

	public AmountByMaterial() {
		super();
	}

	public AmountByMaterial(Comparator<? super Integer> comparator) {
		super(comparator);
	}

	public AmountByMaterial(Map<? extends Integer, ? extends Double> m) {
		super(m);
	}

	public AmountByMaterial(SortedMap<Integer, ? extends Double> m) {
		super(m);
	}

	@Override
	public AmountByMaterial chain() {
		return this;
	}

	@Override
	public AmountByMaterial getCopy() { return new AmountByMaterial(this); }

	@Override
	public String getLabelByElement(BeanMaterial element) {
		return Nullable.m(() -> element.getName());
	}

	@Override
	public Map<Integer, BeanMaterial> getMapSource() { return DataMaps.getMaterialsMap(); }

}
