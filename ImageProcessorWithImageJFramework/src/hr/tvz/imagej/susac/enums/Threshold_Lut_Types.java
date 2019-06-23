package hr.tvz.imagej.susac.enums;

public enum Threshold_Lut_Types {

	RED_LUT("Red Lut", 0), 
	BLACK_AND_WHITE_LUTI("Black and White Luti", 1), 
	NO_LUT_UPDATE("No Lut update", 2),
	OVER_UNDER_LUT("Over under Lut", 3);
	
	private final String displayLutName;
	private final Integer displayLutValue;
	
	Threshold_Lut_Types(String displayLutName, Integer displayLutValue) {
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
