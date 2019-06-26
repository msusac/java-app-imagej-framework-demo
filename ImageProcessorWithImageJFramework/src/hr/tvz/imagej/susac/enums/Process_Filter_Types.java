package hr.tvz.imagej.susac.enums;

public enum Process_Filter_Types {

	MEAN("Mean", 0), 
	MIN("Min", 1), 
	MAX("Max", 2),
	VARIANCE("Variance", 3),
	MEDIAN("Median", 4);
	
	private final String displayFilterName;
	private final Integer displayFilterValue;
	
	Process_Filter_Types(String displayFilterName, Integer displayFilterValue) {
		this.displayFilterName = displayFilterName;
		this.displayFilterValue = displayFilterValue;
	}

	public String getDisplayFilterName() {
		return displayFilterName;
	}

	public Integer getDisplayFilterValue() {
		return displayFilterValue;
	}
}
