package model.track;

import java.util.List;

import model.PickUpable;

public class Cell {
	private int _step;
	private int _lane;
	private Terrain _terrain;
	private Track _track;
	private PickUpable _pickUp;

	public Cell(int step, int lane, Terrain terrain, Track track) {
		_step = step;
		_lane = lane;
		_terrain = terrain;
		_track = track;
	}

	public int getStep() {
		return _step;
	}

	public int getLane() {
		return _lane;
	}

	public Terrain getTerrain() {
		return _terrain;
	}

	public void setTerrain(Terrain terrain) {
		_terrain = terrain;
	}

	public PickUpable getPickUp() {
		return _pickUp;
	}

	public void setPickUp(PickUpable pickUp) {
		_pickUp = pickUp;
	}

	public Cell getCell(String direction) {
		return _track.getCell(this, direction);
	}

	public List<Cell> getSurrounding() {
		return _track.getSurrounding(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _lane;
		result = prime * result + _step;
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
		Cell other = (Cell) obj;
		if (_lane != other._lane)
			return false;
		if (_step != other._step)
			return false;
		return true;
	}

}
