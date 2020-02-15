package avuilder4j.design.sub.functional;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import avuilder4j.data.DataMaps;
import avuilder4j.data.beans.BeanMaterial;
import avuilder4j.util.java.Nullable;

public class Materials extends AmountByGeneric<Materials, Integer, BeanMaterial> {
	private static final long serialVersionUID = 1232491739089084616L;

	public Materials() {
		super();
	}

	public Materials(Comparator<? super Integer> comparator) {
		super(comparator);
	}

	public Materials(Map<? extends Integer, ? extends Double> m) {
		super(m);
	}

	public Materials(SortedMap<Integer, ? extends Double> m) {
		super(m);
	}

	@Override
	public Materials chain() {
		return this;
	}

	@Override
	public Materials getCopy() { return new Materials(this); }

	@Override
	public String getLabelByElement(BeanMaterial element) {
		return Nullable.m(() -> element.getName());
	}

	@Override
	public Map<Integer, BeanMaterial> getMapSource() { return DataMaps.getMaterialsMap(); }

}
