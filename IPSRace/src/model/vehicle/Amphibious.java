package model.vehicle;

import model.track.Terrain;

public class Amphibious extends VehicleType {
	public Amphibious() {
		super(Type.AMPHIBIOUS);
		putTerrainTurns(Terrain.DIRT, 1);
		putTerrainTurns(Terrain.RIVER, 1);
		putTerrainTurns(Terrain.BRIDGE, 1);
		putTerrainTurns(Terrain.BOULDER, 0);
		putTerrainTurns(Terrain.GRAVEL, 2);
	}
}
