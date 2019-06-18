package hr.tvz.imagej.susac.enums;

public enum ThresholdMethodTypes {

	DEFAULT("Default"), 
	HUANG("Huang"),
	INTERMODES("Intermodes"),
	ISODATA("IsoData"), 
	IJ_ISODATA("IJ_IsoData"), 
	LI("Li"), 
	MAX_ENTROPY("MaxEntropy"), 
	MEAN("Mean"), 
	MIN_ERROR("MinError"), 
	MINIMUM("Minimum"),
	MOMENTS("Moments"),
	OTSU("Otsu"),
	PERCENTILE("Percentile"),
	RENYI_ENTROPY("RenyiEntropy"),
	SHANBHAG("Shanbhag"),
	TRIANGLE("Triangle"),
	YEN("Yen");
	
	private final String displayMethodName;
	
	ThresholdMethodTypes(String displayMethodName){
		this.displayMethodName = displayMethodName;
	}
	
	public String getDisplayMethodName() {
		return displayMethodName;
	}
}
