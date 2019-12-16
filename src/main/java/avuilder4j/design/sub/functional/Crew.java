package avuilder4j.design.sub.functional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import avuilder4j.data.beans.BeanCrewCommand;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.NullSafe;

public class Crew extends TreeMap<String, Double> implements Chainable<Crew> {
	private static final long serialVersionUID = 8783727114370701807L;

	public Crew addCrew(String crew, Double ammount) {
		if (ammount != null && crew != null) {
			if (containsKey(crew)) {
				put(crew, get(crew) + ammount);
			} else {
				put(crew, ammount);
			}
		}
		return chain();
	}

	public Crew addCrew(Crew crew) {
		crew.entrySet().stream().forEach(e -> addCrew(e.getKey(), e.getValue()));
		return chain();
	}

	public Crew setCrew(String crew, Double ammount) {
		if (ammount != null && crew != null) {
			put(crew, ammount);
		}
		return chain();
	}

	public Crew setCrew(Crew crew) {
		crew.entrySet().stream().forEach(e -> setCrew(e.getKey(), e.getValue()));
		return chain();
	}

	private Crew calculateCommandersReq(boolean finalStats) { // TODO
		Crew copy = new Crew().add(this);
		Set<String> calculated = new HashSet<>();
		Set<String> allCommanders = null;// DataMaps.getCrewCommandMap().values().stream().map(v ->
											// v.getIndex().getCommander())
//				.collect(Collectors.toSet());
		boolean operated = false;

		do {
			operated = false;
			for (String e : allCommanders) {
				String actual = e;
				if (!calculated.contains(actual)) {
					List<BeanCrewCommand> comands = null;// DataMaps.getCrewCommandListByCommander(actual);
//					Double toAdd;
//					boolean operatedSome = false;
					for (BeanCrewCommand c : comands) {
						String commanded = null; // c.getIndex().getCommanded();
						if (!allCommanders.contains(commanded) || calculated.contains(commanded)) {
							Double toAdd = NullSafe.run(() -> copy.get(commanded) / c.getCommandRatio());
							copy.addCrew(actual, toAdd);
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

	public Map<String, Integer> getCrewReq() {
		return entrySet().stream().collect(Collectors
				.toMap(Map.Entry::getKey, (x) -> x.getValue().intValue(), (a, b) -> a, TreeMap::new));
	}

	private Map<String, Double> getCrewCost(boolean finalStats) {// TODO
//		return entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (x) -> NullSafe.run(() -> {
//			Double req = finalStats ? x.getValue().intValue() : x.getValue();
//			return req * DataMaps.getCrew(x.getKey()).getSalary();
//		}), (a, b) -> a, TreeMap::new));
		return null;
	}

	public Crew add(Crew crewReq) {
		for (Map.Entry<String, Double> entry : crewReq.entrySet()) {
			addCrew(entry.getKey(), entry.getValue());
		}
		return chain();
	}

	@Override
	public Crew chain() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CrewReq [");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
