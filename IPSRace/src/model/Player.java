package model;

import java.awt.Color;

import model.track.Cell;

public class Player {
	private String _name;
	private Color _color;
	private Vehicle _vehicle;
	
	public Player(String name, Color color, Cell position) {
		_name = name;
		_color = color;
		_vehicle = new Vehicle(position);
	}

	public String getName() {
		return _name;
	}

	public Color getColor() {
		return _color;
	}

	public Vehicle getVehicle() {
		return _vehicle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_color == null) ? 0 : _color.hashCode());
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (_color == null) {
			if (other._color != null)
				return false;
		} else if (!_color.equals(other._color))
			return false;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		return true;
	}
}
