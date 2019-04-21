package model.vehicle;

import model.track.Terrain;

public class FourWheelDrive extends VehicleType {
	public FourWheelDrive() {
		super(Type.FOUR_WHEEL_DRIVE);
		putTerrainTurns(Terrain.DIRT, 1);
		putTerrainTurns(Terrain.RIVER, 0);
		putTerrainTurns(Terrain.BRIDGE, 1);
		putTerrainTurns(Terrain.BOULDER, 1);
		putTerrainTurns(Terrain.GRAVEL, 1);
	}
}
