package model;

public enum Direction {
	FORWARD  ("F"),
	BACKWARD ("B"),
	LEFT     ("L"),
	RIGHT    ("R");
	
	private String _char;
	
	Direction(String character) {
		_char = character;
	}
	
	public String getChar() {
		return _char;
	}
}
