package quickCrab;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class QuickCrab extends CrabCritter {
	
	// Constructor, initialize the color to blue
	public QuickCrab() {
		setColor(Color.BLUE);
	}
	
    /**
     * @return list of empty locations immediately two steps away to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
    	// Initialize the locations to be returned
        ArrayList<Location> locations = new ArrayList<Location>();
        
        // Get the grid
        Grid<Actor> grid = getGrid();
        
        // Get current location
        Location currentLocation = getLocation();
        
        // Get the left temp location
        Location tempLocation = currentLocation.getAdjacentLocation(getDirection() + Location.LEFT);
        
        // Check the location
        if (grid.isValid(tempLocation) && grid.get(tempLocation) == null) {
        	// Get the next temp location
        	Location tempLocationTwoStepsAway = tempLocation.getAdjacentLocation(getDirection() + Location.LEFT);
        	
        	// Check that location
        	if (grid.isValid(tempLocationTwoStepsAway) && grid.get(tempLocationTwoStepsAway) == null) {
        		locations.add(tempLocationTwoStepsAway);
        	}
        }
        
        // Get the right temp location
        tempLocation = currentLocation.getAdjacentLocation(getDirection() + Location.RIGHT);
        
        // Check the location
        if (grid.isValid(tempLocation) && grid.get(tempLocation) == null) {
        	// Get the next temp location
        	Location tempLocationTwoStepsAway = tempLocation.getAdjacentLocation(getDirection() + Location.RIGHT);
        	
        	// Check that location
        	if (grid.isValid(tempLocationTwoStepsAway) && grid.get(tempLocationTwoStepsAway) == null) {
        		locations.add(tempLocationTwoStepsAway);
        	}
        }
        
        // Check the locations
        if (locations.isEmpty()) {
        	return super.getMoveLocations();
        }
        else {
        	return locations;
        }
    }
}
