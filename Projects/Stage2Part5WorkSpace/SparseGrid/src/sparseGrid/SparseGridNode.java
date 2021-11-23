package sparseGrid;
// The node store the information of the occupant object
public class SparseGridNode {
	// You can find the node definition from the matrix or github
	// occupant ------ the object in that location
	// col      ------ correspondent column
	// next     ------ next node
	private Object occupant;
	private int col;
	private SparseGridNode next;
	
	// Constructor
	public SparseGridNode(Object occupantInput, int colInput, SparseGridNode nextInput) {
		occupant = occupantInput;
		col      = colInput;
		next     = nextInput;
	}
	
	// The interface to get the value of variable
	public Object getOccupant() {
		return occupant;
	}
	public int getColumn() {
		return col;
	}
	public SparseGridNode getNext() {
		return next;
	}
	
	// The interface to set the value of variable (You can't set the column)
	public void setOccupant(Object occupantInput) {
		occupant = occupantInput;
	}
	public void setNext(SparseGridNode nextInput) {
		next = nextInput;
	}
}
