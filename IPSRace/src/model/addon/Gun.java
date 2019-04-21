package model.addon;

import java.util.ArrayList;
import java.util.List;

import model.PickUpable;
import model.track.Cell;
import model.track.Terrain;

public class Gun extends VehicleAddOn {
	public Gun() {
		super(AddOn.GUN, 3, 0);
	}

	@Override
	public void preMove(Cell cell) {
		if (getDuration() > 0 && cell.getTerrain() == Terrain.BOULDER) {
			decDuration();
			cell.setTerrain(Terrain.DIRT);
		}
	}

	@Override
	public List<PickUpable> postMove(Cell cell) {
		return new ArrayList<>();
	}

}
