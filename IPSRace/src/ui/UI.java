package ui;

import java.util.List;

import model.Player;
import model.addon.VehicleAddOn;
import model.vehicle.VehicleType;

public interface UI {
	public Player promptFirstPlayer();
	public void promptPlayerMissTurn(Player player);
	public void promptPlayerWouldCrash(Player player);
	public String promptPlayerForMove(Player player);
	public VehicleType promptPlayerForVehicleTypePickUp(Player player, List<VehicleType> types);
	public VehicleAddOn promptPlayerForVehicleAddOnPickUp(Player player, List<VehicleAddOn> addOns);
	public void promptPlayerWins(Player player);
	public void play();
	public void redraw();
}
