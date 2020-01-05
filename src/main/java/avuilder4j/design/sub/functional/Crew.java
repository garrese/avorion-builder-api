package avuilder4j.design.sub.functional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import avuilder4j.data.DataMaps;
import avuilder4j.data.beans.BeanCrew;
import avuilder4j.data.beans.BeanCrewCommand;
import avuilder4j.util.java.Nullable;

public class Crew extends AmountBy<Crew, Integer, BeanCrew> {
	private static final long serialVersionUID = 3107430597404840632L;

	public Crew() {
		super();
	}

	public Crew(Comparator<? super Integer> comparator) {
		super(comparator);
	}

	public Crew(Map<? extends Integer, ? extends Double> m) {
		super(m);
	}

	public Crew(SortedMap<Integer, ? extends Double> m) {
		super(m);
	}

	public Crew getReqCrewCopy() {
		Crew ac = new Crew();
		for (Map.Entry<Integer, Double> e : entrySet()) {
			if (e.getValue() != null)
				ac.add(e.getKey(), Math.floor(e.getValue()));
		}
		return ac;
	}

	public Crew getReqCrewWithCommandersCopy() {
		Crew copy = getReqCrewCopy();

		TreeMap<Integer, HashSet<Integer>> calculated = new TreeMap<>();
		Set<Integer> allCommanders = DataMaps.getCrewCommandMap().values().stream().map(v -> v.getIndex().getCommander()).collect(Collectors.toSet());
		for (Integer c : allCommanders) {
			calculated.put(c, new HashSet<>());
		}

		boolean doneSomething = false;

		do {
			doneSomething = false;
			for (Integer commander : allCommanders) {

				List<BeanCrewCommand> commandeds = DataMaps.getCrewCommandListByCommander(commander);
				if (!isCommandCalculated(commander, calculated)) {
					for (BeanCrewCommand c : commandeds) {
						Integer commanded = c.getIndex().getCommanded();

						boolean commandedIsCommander = allCommanders.contains(commanded);
						if (!commandedIsCommander || isCommandCalculated(commanded, calculated)) {
							Double toAdd = Nullable.m(() -> copy.get(commanded) / c.getCommandRatio());
							if (toAdd != null) {
								toAdd = Math.floor(toAdd);
								copy.add(commander, toAdd);
							}
							calculated.get(commander).add(commanded);
							doneSomething = true;
						}
					}
				}
			}
		} while (doneSomething);
		return copy;
	}

	protected boolean isCommandCalculated(Integer commander, TreeMap<Integer, HashSet<Integer>> calculated) {
		List<BeanCrewCommand> commandeds = DataMaps.getCrewCommandListByCommander(commander);
		for (BeanCrewCommand c : commandeds) {
			Integer commanded = c.getIndex().getCommanded();
			if (!calculated.get(commander).contains(commanded)) {
				return false;
			}
		}
		return true;
	}

	public double getTotalSalaryCost() {
		double total = 0;
		for (Map.Entry<Integer, Double> e : entrySet()) {
			Double salary = getSalaryCost(e.getKey());
			if (salary != null)
				total += salary;
		}
		return total;
	}

	public AmountByCrew getSalaryCosts() {
		AmountByCrew ac = new AmountByCrew();
		for (Map.Entry<Integer, Double> e : entrySet()) {
			Double salary = getSalaryCost(e.getKey());
			if (salary != null)
				ac.add(e.getKey(), salary);
		}
		return ac;
	}

	public Double getSalaryCost(Integer crewIndex) {
		return Nullable.m(() -> getMapSource().get(crewIndex).getSalary() * get(crewIndex));
	}

	@Override
	public Crew chain() {
		return this;
	}

	@Override
	public Crew getCopy() { return new Crew(this); }

	@Override
	public String getLabelByElement(BeanCrew element) {
		return Nullable.m(() -> element.getName());
	}

	@Override
	public Map<Integer, BeanCrew> getMapSource() { return DataMaps.getCrewMap(); }
}
