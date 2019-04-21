package model.track;

import java.util.ArrayList;
import java.util.List;

import model.Direction;

public class Track {	
	private Cell[][] _cells;
	
	public Track(int width, int length) {
		_cells = new Cell[length][width];
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
}
