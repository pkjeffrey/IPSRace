package model;

import java.util.ArrayList;
import java.util.List;

import model.addon.VehicleAddOn;
import model.track.Cell;
import model.vehicle.Buggy;
import model.vehicle.VehicleType;

public class Vehicle {
	private Cell _position;
	private int _energy;
	private VehicleType _type;
	private VehicleAddOn _addOn;
	
	public Vehicle(Cell position) {
		_position = position;
		_energy = 0;
		_type = new Buggy();
		_addOn = null;
	}
	
	public Cell getPosition() {
		return _position;
	}
	
	public int getEnergy() {
		return _energy;
	}
	
	public void incEnergy() {
		_energy++;
	}
	
	public VehicleAddOn getAddOn() {
		return _addOn;
	}
	
	public void setAddOn(VehicleAddOn addOn) {
		_addOn = addOn;
	}
	
	public VehicleType getType() {
		return _type;
	}
	
	public void setType(VehicleType type) {
		_type = type;
	}
	
	public List<PickUpable> makeMove(String direction) {
		preMove(direction);
		move(direction);
		return postMove();
	}
	
	private void preMove(String direction) {
		if (_addOn != null) {
			Cell moveTo = _position.getCell(direction);
			_addOn.preMove(moveTo);
		}
	}
	
	private void move(String direction) {
		Cell moveTo = _position.getCell(direction);
		int turnsToMove = _type.getNumTurnsTerrain(moveTo.getTerrain());
		if (turnsToMove > 0) {
			if (_energy == 0)
				_energy += 1 + (_addOn == null ? 0 : _addOn.getEnergy());
			_position = moveTo;
			_energy -= turnsToMove;
		}
	}
	
	private List<PickUpable> postMove() {
		List<PickUpable> items = null;
		
		if (_addOn != null) {
			items = _addOn.postMove(_position);
			if (_addOn.getDuration() <= 0)
				_addOn = null;
		} else
			items = new ArrayList<>();
		
		if (_position.getPickUp() != null) {
			items.add(_position.getPickUp());
			_position.setPickUp(null);
		}
		
		return items;
	}
}
