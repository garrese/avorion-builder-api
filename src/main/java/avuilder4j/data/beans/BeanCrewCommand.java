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

		protected String commander;
		protected String commanded;

		public MapIndex(String commander, String commanded) {
			this.commander = commander;
			this.commanded = commanded;
		}

		public String getCommander() { return commander; }

		public String getCommanded() { return commanded; }

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[commander=");
			builder.append(commander);
			builder.append(", commanded=");
			builder.append(commanded);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((commanded == null) ? 0 : commanded.hashCode());
			result = prime * result + ((commander == null) ? 0 : commander.hashCode());
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
			if (commanded == null) {
				if (other.commanded != null)
					return false;
			} else if (!commanded.equals(other.commanded))
				return false;
			if (commander == null) {
				if (other.commander != null)
					return false;
			} else if (!commander.equals(other.commander))
				return false;
			return true;
		}
	}

}
