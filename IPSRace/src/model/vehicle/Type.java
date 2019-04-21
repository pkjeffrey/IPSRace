package model.vehicle;

public enum Type {
	BUGGY            ("Buggy"),
	AMPHIBIOUS       ("Ampibious"),
	FOUR_WHEEL_DRIVE ("4WD");
	
	private String _description;
	
	Type(String description) {
		_description = description;
	}
	
	public String getDescription() {
		return _description;
	}
}
