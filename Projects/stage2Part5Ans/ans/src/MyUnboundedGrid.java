/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class MyUnboundedGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;
    private int init = 16;
    public MyUnboundedGrid() {
        occupantArray = new Object[init][init];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return loc.getRow()>=0 && loc.getCol() >= 0; 
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (int r = 0; r < init; r++) {
            for (int c = 0; c < init; c++) {
                if(occupantArray[r][c] != null) {
                    Location loc = new Location(r,c);
                    a.add(loc);
                }
            }
        }
        return a;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new NullPointerException("loc == null");
        }
        if (loc.getRow()>init || loc.getCol()>init) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new NullPointerException("loc == null");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        if (loc.getRow() >init || loc.getCol() > init) {
            int initSize = init;
            while(loc.getRow()>initSize || loc.getCol()>initSize) {
                initSize = 2*initSize;
            }
            //init = initSize;
            Object [][]temp = new Object[init][init];
            for (int r = 0; r < init; r++) {
                for (int c = 0; c < init; c++) {
                    temp[r][c] = occupantArray[r][c];
                }
            }
            occupantArray = new Object[initSize][initSize];
            for (int i = 0; i < initSize; i++) {
                for (int j = 0; j < initSize; j++) {
                    occupantArray[i][j] = null;
                }
            }
            for (int i = 0; i < init; i++) {
                for (int j = 0; j < init; j++) {
                    occupantArray[i][j] = temp[i][j];
                }
            }
            //occupantArray = temp;
            init = initSize;          
        }
        E oldOccupant = get(loc);
        
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        //System.out.println(occupantArray[loc.getRow()][loc.getCol()]);
        //System.out.println(init);
        //System.out.println(getOccupiedLocations().size());
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new NullPointerException("loc == null");
        }
        if (loc.getRow()>=init || loc.getCol()>=init) {
            return null;
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        //System.out.println(getOccupiedLocations().size());
        return r;
    }
}
