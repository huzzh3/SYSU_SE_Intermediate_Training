package chameleonKid;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import modifiedChameleonCritter.ModifiedChameleonCritter;

public class ChameleonKid extends ModifiedChameleonCritter {
    /**
     * A ChameleonKid gets the actors in the three locations immediately in front, to its behind
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
    	// You need to return actors
        ArrayList<Actor> actors = new ArrayList<Actor>();
        
        // Initialize the directions for getLocationsInDirections
        int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
        
        // Get the actors
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        // Return
        return actors;
    }
    
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
    	// Initialize locs for return
        ArrayList<Location> locs = new ArrayList<Location>();
        
        // Get the grid
        Grid<Actor> gr = getGrid();
        
        // Get the current location
        Location loc = getLocation();
    
        // Get the correspondent location
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        
        // Return
        return locs;
    } 
}
