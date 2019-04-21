package game;

import java.util.ArrayList;
import java.util.List;

import model.PickUpable;
import model.Player;
import model.addon.VehicleAddOn;
import model.track.Cell;
import model.track.Track;
import model.vehicle.VehicleType;
import ui.UI;

public class Controller {
	private boolean _gameEnded;
	private UI _ui;
	private List<Player> _players;
	private int _currentPlayer;
	private Track _track;
	
	public Controller(UI ui, List<Player> players, Track track) {
		_gameEnded = false;
		_ui = ui;
		_players = players;
		_currentPlayer = 0;
		_track = track;
	}
	
	public void init() {
		_gameEnded = false;
		_ui.redraw();
	}
	
	public void play() {
		_currentPlayer = _players.indexOf(_ui.promptFirstPlayer());
		
		while (!_gameEnded) {
			Player player = _players.get(_currentPlayer);
			boolean win = takeTurn(player);
			_ui.redraw();
			
			if (win) {
				_ui.promptPlayerWins(player);
				_gameEnded = true;
				
			} else {
				if (player.getVehicle().getEnergy() <= 0)
					if (++_currentPlayer == _players.size())
						_currentPlayer = 0;
			}
		}
	}
	
	private boolean takeTurn(Player player) {
		boolean win = false;
		
		if (player.getVehicle().getEnergy() < 0) {
			_ui.promptPlayerMissTurn(player);
			player.getVehicle().incEnergy();
			
		} else {
			String direction = _ui.promptPlayerForMove(player);
			if (wouldCrashIntoAnotherPlayer(player, direction))
				_ui.promptPlayerWouldCrash(player);
			else {
				List<PickUpable> pickUps = player.getVehicle().makeMove(direction);
				
				if (player.getVehicle().getPosition().getStep() == _track.getLength() - 1)
					win = true;
				
				else {
					if (!pickUps.isEmpty()) {
						VehicleType chosenType = null;
						VehicleAddOn chosenAddOn = null;
						List<VehicleType> types = getVehicleTypes(pickUps);
						List<VehicleAddOn> addOns = getVehicleAddOns(pickUps);
						
						if (types.size() == 1)
							chosenType = types.get(0);
						else if (types.size() > 1)
							chosenType = _ui.promptPlayerForVehicleTypePickUp(player, types);
						if (chosenType != null)
							player.getVehicle().setType(chosenType);
						
						if (addOns.size() == 1)
							chosenAddOn = addOns.get(0);
						else if (addOns.size() > 1)
							chosenAddOn = _ui.promptPlayerForVehicleAddOnPickUp(player, addOns);
						if (chosenAddOn != null)
							player.getVehicle().setAddOn(chosenAddOn);
					}
				}
			}
		}
		
		return win;
	}
	
	private boolean wouldCrashIntoAnotherPlayer(Player player, String direction) {
		boolean crash = false;
		
		Cell moveTo = player.getVehicle().getPosition().getCell(direction);
		for (Player p : _players)
			if (p.getVehicle().getPosition().equals(moveTo)) {
				crash = true;
				break;
			}
		
		return crash;
	}
	
	private List<VehicleType> getVehicleTypes(List<PickUpable> pickUps) {
		List<VehicleType> types = new ArrayList<>();
		
		for (PickUpable pickUp : pickUps)
			if (pickUp instanceof VehicleType)
				types.add((VehicleType) pickUp);
		
		return types;
	}
	
	private List<VehicleAddOn> getVehicleAddOns(List<PickUpable> pickUps) {
		List<VehicleAddOn> addOns = new ArrayList<>();
		
		for (PickUpable pickUp : pickUps)
			if (pickUp instanceof VehicleAddOn)
				addOns.add((VehicleAddOn) pickUp);
		
		return addOns;
	}
}
