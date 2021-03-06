package sparseGridMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

// The Map-implementation
public class SparseBoundedGridMap<E> extends AbstractGrid<E> {

	// We use the map to store the element
	private Map<Location, E> occupantMap;
	// Some attributes of the grid
	private int numRows;
	private int numCols;
	
	// Constructor
	public SparseBoundedGridMap(int rows, int cols) {
		// Allocate a new area
		occupantMap = new HashMap<Location, E>();
		// Initialize the attributes
		numRows = rows;
		numCols = cols;
	}
	
	// Get the object from the location
	@Override
	public E get(Location loc) {
		// Check the loc
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		
		// loc is the key for hashtable
		return occupantMap.get(loc);
	}

	@Override
	public int getNumCols() {
		return numCols;
	}

	@Override
	public int getNumRows() {
		return numRows;
	}

	@Override
	public ArrayList<Location> getOccupiedLocations() {
		// You can reuse the getOccupiedLocations from the Unbounded Grid
		ArrayList<Location> a = new ArrayList<Location>();
		
		for (Location loc : occupantMap.keySet()) {
			a.add(loc);
		}
		
		return a;
	}

	@Override
	public boolean isValid(Location location) {
		return 0 <= location.getRow() && location.getRow() < getNumRows() &&
			   0 <= location.getCol() && location.getCol() < getNumCols();
	}

	// Put the object to the location
	@Override
	public E put(Location loc, E obj) {
		// Check the loc
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		
		// Check the obj
		if (obj == null) {
			throw new NullPointerException("obj == null");
		}
		
		// Use hashtable to put obj
		return occupantMap.put(loc, obj);
	}

	@Override
	public E remove(Location loc) {
		// Check the loc
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		
		// Use hashtable to remove
		return occupantMap.remove(loc);
	}
	
}
