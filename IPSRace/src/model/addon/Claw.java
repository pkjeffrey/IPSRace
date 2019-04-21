package model.addon;

import java.util.ArrayList;
import java.util.List;

import model.PickUpable;
import model.track.Cell;

public class Claw extends VehicleAddOn {
	public Claw() {
		super(AddOn.CLAW, 3, 0);
	}

	@Override
	public void preMove(Cell cell) {
		// nothing
	}

	@Override
	public List<PickUpable> postMove(Cell cell) {
		List<PickUpable> items = new ArrayList<>();
		
		if (getDuration() > 0) {
			boolean used = false;
			
			for (Cell check : cell.getSurrounding()) {
				PickUpable pickUp = check.getPickUp();
				if (pickUp != null) {
					items.add(pickUp);
					used = true;
				}
			}
			
			if (used)
				decDuration();
		}
		
		return items;
	}

}
