package ui;

import java.util.List;

import model.Player;
import model.addon.VehicleAddOn;
import model.vehicle.VehicleType;

public class GraphicsUI implements UI {
	
	public GraphicsUI(String trackFile, String[] players) {
		
	}

	@Override
	public Player promptFirstPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void promptPlayerMissTurn(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void promptPlayerWouldCrash(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public String promptPlayerForMove(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleType promptPlayerForVehicleTypePickUp(Player player, List<VehicleType> types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleAddOn promptPlayerForVehicleAddOnPickUp(Player player, List<VehicleAddOn> addOns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void promptPlayerWins(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redraw() {
		// TODO Auto-generated method stub

	}

}
