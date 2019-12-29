package avuilder4j.design.sub.functional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import avuilder4j.data.beans.BeanCrewCommand;
import avuilder4j.util.java.Nullable;

public class AmountsCrew extends Amounts<AmountsCrew> {

	public AmountsCrew() {
		super();
	}

	public AmountsCrew(Comparator<? super Integer> comparator) {
		super(comparator);
	}

	public AmountsCrew(Map<? extends Integer, ? extends Double> m) {
		super(m);
	}

	public AmountsCrew(SortedMap<Integer, ? extends Double> m) {
		super(m);
	}

	private AmountsCrew calculateCommandersReq(boolean finalStats) { // TODO
		AmountsCrew copy = new AmountsCrew().add(this);
		Set<Integer> calculated = new HashSet<>();
		Set<Integer> allCommanders = null;// DataMaps.getCrewCommandMap().values().stream().map(v ->
											// v.getIndex().getCommander())
//				.collect(Collectors.toSet());
		boolean operated = false;

		do {
			operated = false;
			for (Integer e : allCommanders) {
				Integer actual = e;
				if (!calculated.contains(actual)) {
					List<BeanCrewCommand> comands = null;// DataMaps.getCrewCommandListByCommander(actual);
//					Double toAdd;
//					boolean operatedSome = false;
					for (BeanCrewCommand c : comands) {
						String commanded = null; // c.getIndex().getCommanded();
						if (!allCommanders.contains(commanded) || calculated.contains(commanded)) {
							Double toAdd = Nullable.m(() -> copy.get(commanded) / c.getCommandRatio());
							copy.add(actual, toAdd);
							calculated.add(actual);
//							operatedSome = true;
							operated = true;
						}
					}
				}
			}
		} while (operated);

//		List<BeanCrewCommand> cs = DataMaps.getCrewCommandListByCommanded(crew);
//		for (BeanCrewCommand c : cs) {
//			NullSafe.run(() -> addCrew(c.getIndex().getCommander(), get(crew) / c.getCommandRatio()));
//		}
		return chain();
	}

	private Map<Integer, Integer> getCrewReq() { return entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (x) -> x.getValue().intValue(), (a, b) -> a, TreeMap::new)); }

	private Map<String, Double> getCrewCost(boolean finalStats) {// TODO
//		return entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (x) -> NullSafe.run(() -> {
//			Double req = finalStats ? x.getValue().intValue() : x.getValue();
//			return req * DataMaps.getCrew(x.getKey()).getSalary();
//		}), (a, b) -> a, TreeMap::new));
		return null;
	}

	@Override
	public AmountsCrew chain() {
		return this;
	}

	@Override
	public AmountsCrew getCopy() { return new AmountsCrew(this); }
}
