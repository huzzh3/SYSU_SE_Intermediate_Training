//package info.gridworld.grid;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.LinkedList;
//import OccupantlnCol;
/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private int rowValue;
    private int colValue;
    //private LinkedList<OccupantCol> occupantList; //the list storing the grid elments in every row
    private ArrayList<LinkedList<OccupantlnCol>> occupantArray;    //the array storing all the list
    //private Object[][] occupantArray; // the array storing the grid elements

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        rowValue = rows;
        colValue = cols;

        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");

        occupantArray = new ArrayList<LinkedList<OccupantlnCol>>();   
        LinkedList<OccupantlnCol> list;
        for (int i = 0; i < rows; i++) {
            list = new LinkedList<OccupantlnCol>();
            occupantArray.add(list);
        }
    }

    public int getNumRows()
    {
        return rowValue;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        //return occupantArray[0].length;
        return colValue;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (OccupantlnCol occupantlnCol : occupantArray.get(r))
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, occupantlnCol.getCol());
                //if (get(loc) != null) {
                    theLocations.add(loc);
                //}
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        for(OccupantlnCol occupantlnCol:occupantArray.get(loc.getRow())) {
            if (occupantlnCol.getCol() == loc.getCol()) {
                return (E) occupantlnCol.getOccupantlnCol();
            }
        }
        Object obj = null;
        return (E) obj;
        //return (E) occupantArray[loc.getRow()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        int c = 0;
        LinkedList<OccupantlnCol> list = occupantArray.get(loc.getRow());
        OccupantlnCol occupantlnCol = new OccupantlnCol(obj, loc.getCol());
        for (OccupantlnCol a : list) {
            if (a.getCol() == loc.getCol()) {
                a.setObj(obj);
                c = 1;
                break;
            }
        }
        if (c == 0) {
            occupantArray.get(loc.getRow()).add(occupantlnCol);
        }
        //occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);
        LinkedList<OccupantlnCol> list = occupantArray.get(loc.getRow());
        int count = 0;
        for(OccupantlnCol o : list) {
            if (o.getCol() == loc.getCol()) {
                list.remove(count);
                break;
            }
            count++;
        }
        //OccupantlnCol occupantlnCol = new OccupantlnCol(obj, loc.getCol());
        //occupantArray.get(loc.getRow()).add(occupantlnCol);      
        return r;
    }
}
