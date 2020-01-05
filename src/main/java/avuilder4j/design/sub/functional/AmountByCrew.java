package avuilder4j.design.sub.functional;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import avuilder4j.data.DataMaps;
import avuilder4j.data.beans.BeanCrew;
import avuilder4j.util.java.Nullable;

public class AmountByCrew extends AmountBy<AmountByCrew, Integer, BeanCrew> {
	private static final long serialVersionUID = -4590237918985288882L;

	public AmountByCrew() {
		super();
	}

	public AmountByCrew(Comparator<? super Integer> comparator) {
		super(comparator);
	}

	public AmountByCrew(Map<? extends Integer, ? extends Double> m) {
		super(m);
	}

	public AmountByCrew(SortedMap<Integer, ? extends Double> m) {
		super(m);
	}

	@Override
	public AmountByCrew chain() {
		return this;
	}

	@Override
	public AmountByCrew getCopy() { return new AmountByCrew(this); }

	@Override
	public String getLabelByElement(BeanCrew element) {
		return Nullable.m(() -> element.getName());
	}

	@Override
	public Map<Integer, BeanCrew> getMapSource() { return DataMaps.getCrewMap(); }

}
