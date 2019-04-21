package model.vehicle;

import model.track.Terrain;

public class Buggy extends VehicleType {
	public Buggy() {
		super(Type.BUGGY);
		putTerrainTurns(Terrain.DIRT, 1);
		putTerrainTurns(Terrain.RIVER, 0);
		putTerrainTurns(Terrain.BRIDGE, 1);
		putTerrainTurns(Terrain.BOULDER, 0);
		putTerrainTurns(Terrain.GRAVEL, 2);
	}
}
