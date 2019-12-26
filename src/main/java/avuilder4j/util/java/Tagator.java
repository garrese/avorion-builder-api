package avuilder4j.util.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tagator implements Serializable, Copyable<Tagator> {
	private static final long serialVersionUID = 6484808937248013851L;

	protected List<String> tagList = new ArrayList<>();

	public Tagator() {}

	public Tagator(List<String> tags) {
		addTags(tags);
	}

	public static Tagator copy(Tagator copyFrom, Tagator copyTo) {
		if (copyFrom != null && copyTo != null) {
			copyTo.setTagList(copyFrom.getTagList());
		}
		return copyTo;
	}

	public Tagator(String tags) {
		addTags(tags);
	}

	public static <T extends Tagable> T findFirstInList(List<T> list, String tags) {
		return list.stream().filter(t -> Nullable.run(() -> t.getTagator().hasTags(tags), false)).findFirst()
				.orElse(null);
	}

	public static <T extends Tagable> T findFirstInListByRegex(List<T> list, String regex) {
		return list.stream().filter(t -> Nullable.run(() -> t.getTagator().hasRegex(regex), false)).findFirst()
				.orElse(null);
	}

	public static <T extends Tagable> List<T> findInList(List<T> list, List<String> tags) {
		return list.stream().filter(t -> Nullable.run(() -> t.getTagator().hasTags(tags), false))
				.collect(Collectors.toList());
	}

	public static <T extends Tagable> List<T> findInList(List<T> list, String tags) {
		return findInList(list, mapTagsStringToTagList(tags));
	}

	public static <T extends Tagable> List<T> findInListByRegex(List<T> list, String regex) {
		return list.stream().filter(t -> Nullable.run(() -> t.getTagator().hasRegex(regex), false))
				.collect(Collectors.toList());
	}

	public static String mapTagListToTagsString(List<String> tagList) {
		StringBuilder tags = new StringBuilder();
		for (int i = 0; i < tagList.size(); i++) {
			if (tagList.get(i) != null) {
				tags.append(tagList.get(i));
				if (i != tagList.size() - 1)
					tags.append(" ");
			}
		}
		return tags.toString();
	}

	public static List<String> mapTagsStringToTagList(String tags) {
		if (tags != null)
			return Arrays.asList(tags.trim().split(" "));
		else
			return new ArrayList<String>();
	}

	public void addTags(List<String> tags) {
		if (tags != null)
			for (String tag : tags)
				if (tag != null)
					addTags(tag);
	}

	public void addTags(String tags) {
		if (tags != null)
			for (String tag : mapTagsStringToTagList(tags))
				if (tag != null)
					tagList.add(tag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tagator))
			return false;
		Tagator other = (Tagator) obj;
		return Objects.equals(tagList, other.tagList);
	}

	public String getFirstTagByRegex(String regex) {
		return getTagList().stream().filter(t -> Nullable.run(() -> t.matches(regex), false)).findFirst().orElse(null);
	}

	public List<String> getTagList() { return tagList; }

	public List<String> getTagListByRegex(String regex) {
		return getTagList().stream().filter(t -> Nullable.run(() -> t.matches(regex), false))
				.collect(Collectors.toList());
	}

	public String getTagsString() { return mapTagListToTagsString(tagList); }

	@Override
	public int hashCode() {
		return Objects.hash(tagList);
	}

	public boolean hasRegex(String regex) {
		for (String thisTag : tagList) {
			if (Nullable.run(() -> thisTag.matches(regex), false)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasTags(List<String> tags) {
		for (String tag : tags) {
			for (String thisTag : tagList) {
				if (Nullable.run(() -> thisTag.equals(tag), false)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasTags(String tags) {
		return hasTags(mapTagsStringToTagList(tags));
	}

	public void setTagList(List<String> tagList) { this.tagList = tagList; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tagator [tagList=");
		builder.append(tagList);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Tagator getCopy() { return Tagator.copy(this, new Tagator()); }

}
