package avuilder4j.design.base;

import java.io.Serializable;
import java.util.Objects;

public class Tags implements Serializable {
	private static final long serialVersionUID = 6484808937248013851L;
	protected String tags = "";

	public Tags() {}

	public Tags(String tags) {
		this.tags = tags;
	}

	public Tags(Tags tags) {
		this.tags = tags.getTags();
	}

	public boolean hasTags(Tags tags) {
		boolean noThisTags = this.tags == null || this.tags.trim().isEmpty();
		boolean noArgTags = tags.getTags() == null || tags.getTags().trim().isEmpty();
		if (noThisTags || noArgTags) {
			return false;
		}

		String[] argTagsArray = tags.getTags().trim().split(" ");
		String[] thisTagsArray = this.tags.trim().split(" ");
		for (String argTag : argTagsArray) {
			boolean hasTag = false;
			for (String thisTag : thisTagsArray) {
				if (argTag.equals(thisTag)) {
					hasTag = true;
					break;
				}
			}
			if (!hasTag) {
				return false;
			}
		}
		return true;
	}

	public void setTags(String tags) { this.tags = tags; }

	public String getTags() { return tags; }

	public void addTag(String tag) {
		if (tags == null || tags.trim().isEmpty()) {
			tags = tag;
		} else {
			tags += " " + tag;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(tags);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tags))
			return false;
		Tags other = (Tags) obj;
		return Objects.equals(tags, other.tags);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tags [");
		builder.append(tags);
		builder.append("]");
		return builder.toString();
	}

}
