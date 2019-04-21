package model.addon;

import java.util.List;

import model.PickUpable;
import model.track.Cell;

public abstract class VehicleAddOn implements PickUpable {
	private AddOn _type;
	private int _duration;
	private int _energy;
	
	protected VehicleAddOn(AddOn type, int duration, int energy) {
		_type = type;
		_duration = duration;
		_energy = energy;
	}
	
	public AddOn getType() {
		return _type;
	}
	
	public int getDuration() {
		return _duration;
	}
	
	protected void decDuration() {
		_duration--;
	}
	
	public int getEnergy() {
		return _energy;
	}
	
	protected void decEnergy() {
		_energy--;
	}
	
	public abstract void preMove(Cell cell);
	
	public abstract List<PickUpable> postMove(Cell cell);
}
