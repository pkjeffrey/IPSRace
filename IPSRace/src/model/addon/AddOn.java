package model.addon;

public enum AddOn {
	GUN     ("Gun"),
	NITROUS ("Nitrous"),
	CLAW    ("Grappling Claw");
	
	private String _description;
	
	AddOn(String description) {
		_description = description;
	}
	
	public String getDescription() {
		return _description;
	}
}
