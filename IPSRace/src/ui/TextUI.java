package ui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Controller;
import model.Player;
import model.addon.VehicleAddOn;
import model.track.Cell;
import model.track.Track;
import model.vehicle.VehicleType;

public class TextUI implements UI {
	private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
	
	private Random _random;
	private Track _track;
	private List<Player> _players;
	private Controller _controller;
	
	public TextUI(String trackFile, String[] players) throws IOException {
		_random = new Random(System.currentTimeMillis());
		_track = Track.build(trackFile);
		_players = new ArrayList<>();
		int lane = players.length < 4 ? 1 : 0;
		for (int i = 0; i < players.length; i++)
			_players.add(new Player(players[i], COLORS[i], _track.getCell(0, lane++)));
	}

	@Override
	public Player promptFirstPlayer() {
		Player first = _players.get(_random.nextInt(_players.size()));
		System.out.println("All players roll a dice...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		System.out.print(String.format("%1$s rolls highest and goes first. <Enter>", first.getName()));
		waitInput();
		return first;
	}

	@Override
	public void promptPlayerMissTurn(Player player) {
		System.out.print(String.format("%1$s misses a turn. <Enter>", player.getName()));
		waitInput();
	}
	
	@Override
	public void promptPlayerWouldCrash(Player player) {
		System.out.println("That move would cause a crash and is not allowed.");
		promptPlayerMissTurn(player);
	}

	@Override
	public String promptPlayerForMove(Player player) {
		String vehicle = player.getVehicle().getType().getType().getDescription();
		if (player.getVehicle().getAddOn() != null)
			vehicle += " (with " + player.getVehicle().getAddOn().getType().getDescription() + ")";
		System.out.print(String.format("%1$s's turn. Driving %2$s. Which direction? <F|B L|R> ", player.getName(), vehicle));
		return waitInput();
	}

	@Override
	public VehicleType promptPlayerForVehicleTypePickUp(Player player, List<VehicleType> types) {
		int i = 1;
		System.out.println(String.format("%1$s, choose from these vehicle types:", player.getName()));
		for (VehicleType type : types)
			System.out.println(String.format("   %1$d %2$s", i++, type.getType().getDescription()));
		System.out.print("Choose: ");
		i = Integer.parseInt(waitInput());
		return types.get(--i);
	}

	@Override
	public VehicleAddOn promptPlayerForVehicleAddOnPickUp(Player player, List<VehicleAddOn> addOns) {
		int i = 1;
		System.out.println(String.format("%1$s, choose from these vehicle add-ons:", player.getName()));
		for (VehicleAddOn addOn : addOns)
			System.out.println(String.format("   %1$d %2$s", i++, addOn.getType().getDescription()));
		System.out.print("Choose: ");
		i = Integer.parseInt(waitInput());
		return addOns.get(--i);
	}

	@Override
	public void promptPlayerWins(Player player) {
		System.out.print(String.format("%1$s has won the race! Congratuations! <Enter>", player.getName()));
		waitInput();
	}

	@Override
	public void play() {
		_controller = new Controller(this, _players, _track);
		_controller.init();
		_controller.play();
	}

	@Override
	public void redraw() {
		for (int i = 0; i < _track.getLength() * 3; i++)
			System.out.print("-");
		System.out.println();
		
		for (int l = 0; l < _track.getWidth(); l++) {
			for (int i = 0; i < _track.getLength(); i++) {
				Cell cell = _track.getCell(i, l);
				Player playerCell = null;
				for (Player p : _players)
					if (p.getVehicle().getPosition().equals(cell)) {
						playerCell = p;
						break;
					}
				if (playerCell != null)
					System.out.print("[" + playerCell.getName().charAt(0) + "]");
				else {
					if (cell.getPickUp() != null) {
						if (cell.getPickUp() instanceof VehicleType) {
							VehicleType t = (VehicleType) cell.getPickUp();
							System.out.print(t.getType().getDescription().substring(0, 3));
						} else {
							VehicleAddOn a = (VehicleAddOn) cell.getPickUp();
							System.out.print(a.getType().getDescription().substring(0, 3));
						}
					} else {
						char c;
						switch (cell.getTerrain()) {
						case BOULDER:
							c = 'B';
							break;
						case GRAVEL:
							c = 'G';
							break;
						case RIVER:
							c = 'R';
							break;
						default:
							c = ' ';
							break;
						}
						System.out.print("" + c + c + c);
					}
				}
			}
			System.out.println();
		}
		
		for (int i = 0; i < _track.getLength() * 3; i++)
			System.out.print("-");
		System.out.println();
	}

	private String waitInput() {
		return System.console().readLine();
	}
}
