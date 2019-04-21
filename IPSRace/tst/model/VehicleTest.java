package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.track.Terrain;
import model.track.Track;

class VehicleTest {

	@Test
	void testMakeMoveBuggyToDirt() {
		Track track = new Track(3, 3);
		Vehicle v = new Vehicle(track.getCell(0, 1));
		
		List<PickUpable> p = v.makeMove("F");
		assertEquals(track.getCell(1, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
		
		p = v.makeMove("FL");
		assertEquals(track.getCell(2, 0), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
		
		p = v.makeMove("R");
		assertEquals(track.getCell(2, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
		
		p = v.makeMove("BL");
		assertEquals(track.getCell(1, 0), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
		
		p = v.makeMove("BR");
		assertEquals(track.getCell(0, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
		
		p = v.makeMove("L");
		assertEquals(track.getCell(0, 0), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
		
		p = v.makeMove("FR");
		assertEquals(track.getCell(1, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
		
		p = v.makeMove("B");
		assertEquals(track.getCell(0, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
	}

	@Test
	void testMakeMoveBuggyToBoulder() {
		Track track = new Track(3, 3);
		track.getCell(1, 1).setTerrain(Terrain.BOULDER);
		Vehicle v = new Vehicle(track.getCell(0, 1));
		List<PickUpable> p = v.makeMove("F");
		assertEquals(track.getCell(0, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
	}

	@Test
	void testMakeMoveBuggyToRiver() {
		Track track = new Track(3, 3);
		track.getCell(1, 1).setTerrain(Terrain.RIVER);
		Vehicle v = new Vehicle(track.getCell(0, 1));
		List<PickUpable> p = v.makeMove("F");
		assertEquals(track.getCell(0, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
	}

	@Test
	void testMakeMoveBuggyToBridge() {
		Track track = new Track(3, 3);
		track.getCell(1, 1).setTerrain(Terrain.BRIDGE);
		Vehicle v = new Vehicle(track.getCell(0, 1));
		List<PickUpable> p = v.makeMove("F");
		assertEquals(track.getCell(1, 1), v.getPosition());
		assertEquals(0, v.getEnergy());
		assertTrue(p.isEmpty());
	}

	@Test
	void testMakeMoveBuggyToGravel() {
		Track track = new Track(3, 3);
		track.getCell(1, 1).setTerrain(Terrain.GRAVEL);
		Vehicle v = new Vehicle(track.getCell(0, 1));
		List<PickUpable> p = v.makeMove("F");
		assertEquals(track.getCell(1, 1), v.getPosition());
		assertEquals(-1, v.getEnergy());
		assertTrue(p.isEmpty());
	}

}
