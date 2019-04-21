package model.vehicle;

import java.util.HashMap;
import java.util.Map;

import model.PickUpable;
import model.track.Terrain;

public abstract class VehicleType implements PickUpable {
	private Type _type;
	private Map<Terrain, Integer> _terrainTurns;
	
	protected VehicleType(Type type) {
		_type = type;
		_terrainTurns = new HashMap<>(10);
	}
	
	public Type getType() {
		return _type;
	}
	
	public int getNumTurnsTerrain(Terrain terrain) {
		return _terrainTurns.get(terrain);
	}
	
	protected void putTerrainTurns(Terrain terrain, int turns) {
		_terrainTurns.put(terrain, turns);
	}
}
