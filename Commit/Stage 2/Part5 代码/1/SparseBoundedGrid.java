package sparseGrid;
import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
	// The array storing the grid elements
	private SparseGridNode[] occupantArray;
	// The columnNum and rowNum storing the attributes of the grid
	private int numCols;
	private int numRows;
	
	// The constructor
	// You can use the constructor from the origin bounded grid
	public SparseBoundedGrid(int rows, int cols) {
		if (rows < 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols < 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		
		// Assign the attribute of the grid
		numRows = rows;
		numCols = cols;
		
		// allocate the area for the array
		occupantArray = new SparseGridNode[rows];
	}
	
	// You need to create the interface to get the attribute of the grid
	public int getNumRows() {
		return numRows;
	}
	public int getNumCols() {
		return numCols;
	}
	
	// You need to create the isValid method to check if the location is valid
	// You can reuse the method from the origin bounded grid
	public boolean isValid(Location location) {
		return 0 <= location.getRow() && location.getRow() < getNumRows() &&
			   0 <= location.getCol() && location.getCol() < getNumCols();
 	}
	
	// The getOccupiedLocations method
	// You can reuse and change a little from the origin bounded grid method
	public ArrayList<Location> getOccupiedLocations() {
		ArrayList<Location> theLocations = new ArrayList<Location>();
		
		// Use the 'for' to travel the array
		for (int row = 0; row < getNumRows(); row++) {
			SparseGridNode tempPtr = occupantArray[row];
			
			// Search the link list
			while (tempPtr != null) {
				Location tempLocation = new Location(row, tempPtr.getColumn());
				
				// Add the valid location to array
			    theLocations.add(tempLocation);
			    tempPtr = tempPtr.getNext();
			}
		}
		
		return theLocations;
	}
	
	// The get method to get correspondent location
	// You can reuse the origin bounded grid method
	@SuppressWarnings("unchecked")
	public E get(Location loc) {
		// check the loc
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid.");
		}
		
		// search the array
		SparseGridNode tempPtr = occupantArray[loc.getRow()];
		while (tempPtr != null) {
			// traverse to find the item at the location loc
			if (loc.getCol() == tempPtr.getColumn()) {
				return (E)tempPtr.getOccupant();
			}
			tempPtr = tempPtr.getNext();
		}
		
		return null;
	}
	
	// The put method to put item on the location
	public E put(Location loc, E obj) {
		// check the loc
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid.");
		}
		
		if (obj == null) {
			throw new NullPointerException("obj == null");
		}
		
		// Before you put the object, you need to remove the correspondent object on the location
		E oldOccupant = remove(loc);
		
		// Then add the new object
		
		// Get the row for the new occupant
		SparseGridNode tempPtr = occupantArray[loc.getRow()];
		
		// Put the new occupant on the front of the list
		occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), tempPtr);
		
		return oldOccupant;
	}
	
	// The remove method to remove the item on the location
	public E remove(Location loc) {
		// check the loc
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid.");
		}
		
		// Remove the object from the grid
		
		E obj = get(loc);
		
		// If not found, return null
		if (obj == null) {
			return null;
		}
		
		// Search the grid
		SparseGridNode tempPtr = occupantArray[loc.getRow()];
		
		if (tempPtr != null) {
			if (tempPtr.getColumn() == loc.getCol()) {
				// Check the first node
				occupantArray[loc.getRow()] = tempPtr.getNext();
			}
			else {
				SparseGridNode tempPtrNext = tempPtr.getNext();
				
				// Search the location
				while (tempPtrNext != null && tempPtrNext.getColumn() != loc.getCol()) {
					tempPtrNext = tempPtrNext.getNext();
					tempPtr     = tempPtr.getNext();
				}
				
				// If found , remove the occupant
				if (tempPtrNext != null) {
					tempPtr.setNext(tempPtrNext.getNext());
				}
			}
		}
		
		// Return the object you remove
		return obj;
	}

}
