package unboundedGrid2;

import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import sparseGrid.SparseGridNode;

// We define this UnBoundedGrid to be a square
public class UnboundedGrid2<E> extends AbstractGrid<E> {
	// We use occupantArray to store item
	private Object[][] occupantArray;
	// The dimension of the occupantArray
	private int dim;
	
	// Constructor
	public UnboundedGrid2() {
		// Initialize the gird to be 16 x 16
		dim = 16;
		occupantArray = new Object[dim][dim];
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public E get(Location loc) {
		// check the loc
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		}
		
		// Return null if a location is valid, but not in the array
		if (loc.getRow() >= dim || loc.getCol() >= dim) {
			return null;
		}
		
		return (E) occupantArray[loc.getRow()][loc.getCol()]; 
	}

	// For the grid is unbounded, so we can't get the numcols and the numrows
	@Override
	public int getNumCols() {
		return -1;
	}
	@Override
	public int getNumRows() {
		return -1;
	}

	@Override
	public ArrayList<Location> getOccupiedLocations() {
		// We can reuse the method from the SparseBoundedGrid
		
		ArrayList<Location> theLocations = new ArrayList<Location>();
		
		// Use the 'for' to travel the array
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				// If there's an object at this location, put it in
				Location loc = new Location(row, col);
				if (get(loc) != null) {
					theLocations.add(loc);
				}
			}
		}
		
		return theLocations;
	}

	// For the grid is unbounded, so we just need to ensure the row and the col are greater than 0
	@Override
	public boolean isValid(Location loc) {
		return loc.getRow() >= 0 && loc.getCol() >= 0;
	}

	@Override
	public E put(Location loc, E obj) {
		// Check the location
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		if (obj == null) {
			throw new NullPointerException("obj == null");
		}
		
		// If new location is out of the array, resize the array
		if (loc.getRow() >= dim || loc.getCol() >= dim) {
			resize(loc);
		}
		
		// Add the object to the grid
		E oldOccupant = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = obj;
		
		return oldOccupant;
	}

	@Override
	public E remove(Location loc) {
		// Check the location
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		}
		
		// If the location is valid and not in array, return null
		if (loc.getRow() >= dim || loc.getCol() >= dim) {
			return null;
		}
		
		// Remove the object from the grid
		E removedObject = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = null;
		return removedObject;
	}
	
	private void resize(Location loc) {
		// double the size until it's greater than needed
		int size = dim;
		while (loc.getRow() >= size || loc.getCol() >= size) {
			size = size * 2;
		}
		
		// Create  a new array
		Object[][] newArray = new Object[size][size];
		
		// Copy over the old contents
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				newArray[row][col] = occupantArray[row][col];
			}
		}
		
		// update
		occupantArray = newArray;
		dim           = size; 
	}

}
