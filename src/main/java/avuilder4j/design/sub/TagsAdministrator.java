package avuilder4j.design.sub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TagsAdministrator implements Serializable {
	private static final long serialVersionUID = 6484808937248013851L;

	protected List<String> tagList = new ArrayList<>();

	public static <T extends Tagable> List<T> findInList(List<T> list, List<String> tags) {
		List<T> r = new ArrayList<>();
		for (T t : list) {
			if (t.getTagsAdministrator().hasTags(tags)) {
				r.add(t);
			}
		}
		return r;
	}

	public static <T extends Tagable> List<T> findInList(List<T> list, String tags) {
		return findInList(list, tagsToTagsList(tags));
	}

	public static <T extends Tagable> T findFirstInList(List<T> list, String tags) {
		for (T t : list) {
			if (t.getTagsAdministrator().hasTags(tags))
				return t;
		}
		return null;
	}

	public static List<String> tagsToTagsList(String tags) {
		return Arrays.asList(tags.trim().split(" "));
	}

	public void addTags(List<String> tags) {
		if (tags != null)
			for (String tag : tags) {
				addTags(tag);
			}
	}

	public void addTags(String tags) {
		if (tags != null)
			for (String tag : tagsToTagsList(tags)) {
				tagList.add(tag);
			}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TagsAdministrator))
			return false;
		TagsAdministrator other = (TagsAdministrator) obj;
		return Objects.equals(tagList, other.tagList);
	}

	public List<String> getTagsList() { return tagList; }

	public String getTags() {
		StringBuilder tags = new StringBuilder();
		for (int i = 0; i < tagList.size(); i++) {
			tags.append(tagList.get(i));
			if (i != tagList.size() - 1)
				tags.append(" ");
		}
		return tags.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(tagList);
	}

	public boolean hasTags(List<String> tags) {
		boolean noThisTags = tagList == null || tagList.size() == 0;
		boolean noArgTags = tags == null || tags.size() == 0;
		if (noThisTags || noArgTags) {
			return false;
		}

		for (String argTag : tags) {
			boolean hasTag = false;
			for (String thisTag : tagList) {
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

	public boolean hasTags(String tags) {
		return hasTags(tagsToTagsList(tags));
	}

	public void setTagList(List<String> tagList) { this.tagList = tagList; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tags [tagList=");
		builder.append(tagList);
		builder.append("]");
		return builder.toString();
	}

}
