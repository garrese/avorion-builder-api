package avuilder4j.design.sub.functional;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import avuilder4j.data.DataMaps;
import avuilder4j.data.beans.BeanMaterial;
import avuilder4j.util.java.Nullable;

public class AmountsMats extends Amounts<AmountsMats> {
	private static final long serialVersionUID = 1232491739089084616L;

	public AmountsMats() {
		super();
	}

	public AmountsMats(Comparator<? super Integer> comparator) {
		super(comparator);
	}

	public AmountsMats(Map<? extends Integer, ? extends Double> m) {
		super(m);
	}

	public AmountsMats(SortedMap<Integer, ? extends Double> m) {
		super(m);
	}

	@Override
	public AmountsMats chain() {
		return this;
	}

	@Override
	public AmountsMats getCopy() { return new AmountsMats(this); }

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("AmountsMats [{");
		int c = 0;
		int last = this.entrySet().size() - 1;
		for (Map.Entry<Integer, Double> e : this.entrySet()) {
			String name = Nullable.m(() -> DataMaps.getMaterial(e.getKey()).getName());
			b.append(name != null ? name : e.getKey()).append("=").append(e.getValue());
			if (c != last)
				b.append(", ");
			c++;
		}
		b.append("}]");
		return b.toString();
	}

	public AmountsMats addForAll(Double ammount) {
		Collection<BeanMaterial> c = Nullable.m(() -> DataMaps.getMaterialsMap().values());
		if (c != null) {
			for (BeanMaterial b : c) {
				add(b.getIndex(), ammount);
			}
		}
		return chain();
	}

	public AmountsMats setAll(Double ammount) {
		Collection<BeanMaterial> c = Nullable.m(() -> DataMaps.getMaterialsMap().values());
		if (c != null) {
			for (BeanMaterial b : c) {
				set(b.getIndex(), ammount);
			}
		}
		return chain();
	}

}
