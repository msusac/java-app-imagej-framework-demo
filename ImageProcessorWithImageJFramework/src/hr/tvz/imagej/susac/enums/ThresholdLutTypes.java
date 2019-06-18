package hr.tvz.imagej.susac.enums;

public enum ThresholdLutTypes {

	RED_LUT("Red Lut", 0), 
	BLACK_AND_WHITE_LUTI("Black and White Luti", 1), 
	OVER_UNDER_LUT("Over under Lut", 2),
	NO_LUT_UPDATE("No Lut update", 3);
	
	private final String displayLutName;
	private final Integer displayLutValue;
	
	ThresholdLutTypes(String displayLutName, Integer displayLutValue) {
		this.displayLutName = displayLutName;
		this.displayLutValue = displayLutValue;
	}

	public String getDisplayLutName() {
		return displayLutName;
	}

	public Integer getDisplayLutValue() {
		return displayLutValue;
	}

	
}
