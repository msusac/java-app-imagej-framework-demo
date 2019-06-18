package hr.tvz.imagej.susac.enums;

public enum ThresholdBackgroundTypes {

	TRUE(true),
	FALSE(false);
	
	private final Boolean displayBackgroundName;
	
	ThresholdBackgroundTypes(Boolean displayBackgroundName){
		this.displayBackgroundName = displayBackgroundName;
	}
	
	public Boolean getDisplayBackgroundName() {
		return displayBackgroundName;
	}
}
