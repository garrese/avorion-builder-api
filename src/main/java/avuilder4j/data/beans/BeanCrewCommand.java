package avuilder4j.data.beans;

import java.util.Objects;

public class BeanCrewCommand {

	protected BeanCrewCommand.MapIndex index;
	protected Double commandRatio;

	public BeanCrewCommand(MapIndex index, Double commandRatio) {
		this.index = index;
		this.commandRatio = commandRatio;
	}

	public BeanCrewCommand.MapIndex getIndex() { return index; }

	public Double getCommandRatio() { return commandRatio; }

	@Override
	public int hashCode() {
		return Objects.hash(commandRatio, index);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanCrewCommand))
			return false;
		BeanCrewCommand other = (BeanCrewCommand) obj;
		return Objects.equals(commandRatio, other.commandRatio) && Objects.equals(index, other.index);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BeanCrewCommand [index=");
		builder.append(index);
		builder.append(", commandRatio=");
		builder.append(commandRatio);
		builder.append("]");
		return builder.toString();
	}

	public static class MapIndex {

		protected Integer commander;
		protected Integer commanded;

		public MapIndex(Integer commander, Integer commanded) {
			this.commander = commander;
			this.commanded = commanded;
		}

		public Integer getCommander() { return commander; }

		public Integer getCommanded() { return commanded; }

		@Override
		public int hashCode() {
			return Objects.hash(commanded, commander);
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
			return Objects.equals(commanded, other.commanded) && Objects.equals(commander, other.commander);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MapIndex [");
			builder.append("commander=");
			builder.append(commander);
			builder.append(", commanded=");
			builder.append(commanded);
			builder.append("]");
			return builder.toString();
		}

	}

}
