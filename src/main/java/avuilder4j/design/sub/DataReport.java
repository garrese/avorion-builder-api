package avuilder4j.design.sub;

public class DataReport extends DataReportGeneric<Double> {
	private static final long serialVersionUID = 1706387074557189999L;

	public DataReport() {}

	public DataReport(String tags) {
		setTags(tags);
	}

	public DataReport(Double result) {
		this.result = result;
	}

	public void addToResult(Double v) {
		if (v == null) {
			addNull();
		} else {
			if (getResult() == null)
				result = 0d;
			result += v;
		}
	}
}
