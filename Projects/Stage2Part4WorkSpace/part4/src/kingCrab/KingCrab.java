package kingCrab;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class KingCrab extends CrabCritter {
	
	// Initialize the king crab's color (pink)
	public KingCrab() {
		setColor(Color.PINK);
	}
	
	// You need to override the processActors function
	public void processActors(ArrayList<Actor> actors) {
		for (Actor actor : actors) {
			if (!moveAway(actor)) {
				actor.removeSelfFromGrid();
			}
		}
	}
	
	// The moveAway function can make crab to move
	public boolean moveAway(Actor actor) {
		// Get the empty locations
		ArrayList<Location> locations = getGrid().getEmptyAdjacentLocations(actor.getLocation());
		
		// Get the current location
		Location currentLocation = getLocation();
		
		// Find the empty space to moveTO
		for (Location location: locations) {
			if (distance(currentLocation, location) > 1) {
				// move the actor
				actor.moveTo(location);
				return true;
			}
		}
		
		// There isn't empty space
		return false;
	}

	public double distance(Location currentLocation, Location location) {
		// Get the attribute
		int currentRow = currentLocation.getRow();
		int currentCol = currentLocation.getCol();
		int targetRow  = location.getRow();
		int targetCol  = location.getCol();
		
		// Calculate the distance
		
		double distance = Math.sqrt((currentRow - targetRow) * (currentRow - targetRow) + (currentCol - targetCol) * (currentCol - targetCol));
		
		return distance;
	}
}
