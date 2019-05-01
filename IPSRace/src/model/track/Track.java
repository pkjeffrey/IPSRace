package model.track;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Direction;
import model.addon.AddOn;
import model.addon.Claw;
import model.addon.Gun;
import model.addon.Nitrous;
import model.vehicle.Amphibious;
import model.vehicle.Buggy;
import model.vehicle.FourWheelDrive;
import model.vehicle.Type;

public class Track {	
	private Cell[][] _cells;
	
	protected Track(int width, int length) {
		_cells = new Cell[length][width];
		for (int step = 0; step < length; step++)
			for (int lane = 0; lane < width; lane++)
				_cells[step][lane] = new Cell(step, lane, Terrain.DIRT, this);
	}
	
	public int getLength() {
		return _cells.length;
	}
	
	public int getWidth() {
		return _cells[0].length;
	}
	
	public Cell getCell(int step, int lane) {
		return _cells[step][lane];
	}
	
	public Cell getCell(Cell cell, String direction) {
		int step = cell.getStep();
		int lane = cell.getLane();
		if (direction.contains(Direction.FORWARD.getChar()) && step < getLength() - 1) step++;
		if (direction.contains(Direction.BACKWARD.getChar()) && step > 0) step--;
		if (direction.contains(Direction.LEFT.getChar()) && lane > 0) lane--;
		if (direction.contains(Direction.RIGHT.getChar()) && lane < getWidth() - 1) lane++;
		
		return _cells[step][lane];
	}
	
	public List<Cell> getSurrounding(Cell cell) {
		List<Cell> cells = new ArrayList<>();
		
		int step = cell.getStep();
		int lane = cell.getLane();
		if (lane > 0) cells.add(_cells[step][lane - 1]);
		if (lane < getWidth() - 1) cells.add(_cells[step][lane + 1]);
		if (step > 0) {
			cells.add(_cells[step - 1][lane]);
			if (lane > 0) cells.add(_cells[step - 1][lane - 1]);
			if (lane < getWidth() - 1) cells.add(_cells[step - 1][lane + 1]);
		}
		if (step < getLength() - 1) {
			cells.add(_cells[step + 1][lane]);
			if (lane > 0) cells.add(_cells[step = 1][lane - 1]);
			if (lane < getWidth() - 1) cells.add(_cells[step + 1][lane + 1]);
		}
		
		return cells;
	}
	
	public static Track build(String trackFile) throws IOException {
		Track track = null;
		String line;
		String[] parts;
		BufferedReader r = null;
		
		r = new BufferedReader(new FileReader(trackFile));
		
		line = r.readLine();
		parts = line.split(",");
		track = new Track(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		
		while ((line = r.readLine()) != null) {
			if ("".equals(line))
				break;
			parts = line.split(",");
			Terrain terrain = Terrain.valueOf(parts[2]);
			track.getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).setTerrain(terrain);
		}
		
		while ((line = r.readLine()) != null) {
			if ("".equals(line))
				break;
			parts = line.split(",");
			Type type = Type.valueOf(parts[2]);
			switch (type) {
			case BUGGY:
				track.getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).setPickUp(new Buggy());
				break;
			case FOUR_WHEEL_DRIVE:
				track.getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).setPickUp(new FourWheelDrive());
				break;
			case AMPHIBIOUS:
				track.getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).setPickUp(new Amphibious());
				break;
			}
		}
		
		while ((line = r.readLine()) != null) {
			parts = line.split(",");
			AddOn addOn = AddOn.valueOf(parts[2]);
			switch (addOn) {
			case CLAW:
				track.getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).setPickUp(new Claw());
				break;
			case GUN:
				track.getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).setPickUp(new Gun());
				break;
			case NITROUS:
				track.getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).setPickUp(new Nitrous());
				break;
			}
		}
		
		if (r != null)
			r.close();
		
		return track;
	}
}
