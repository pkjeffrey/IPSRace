package model.addon;

import java.util.ArrayList;
import java.util.List;

import model.PickUpable;
import model.track.Cell;

public class Nitrous extends VehicleAddOn {
	public Nitrous() {
		super(AddOn.NITROUS, 3, 1);
	}
	
	@Override
	public int getEnergy() {
		if (getDuration() > 0 && super.getEnergy() > 0)
			decDuration();
		else
			decEnergy();
		
		return super.getEnergy();
	}

	@Override
	public void preMove(Cell cell) {
		// nothing
	}

	@Override
	public List<PickUpable> postMove(Cell cell) {
		return new ArrayList<>();
	}

}
