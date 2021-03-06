package jumper;

import java.awt.Color;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

// Jumper can jump over the Rocks and the Flowers
// Jumper move two cells one time
public class Jumper extends Actor {
	
	// Constructor (without parameter)
	public Jumper() {
		// Default color is blue
		setColor(Color.GREEN);
	}
	
	// Constructor (with one parameter)
	public Jumper(Color jumperColor) {
		setColor(jumperColor);
	}
	
	// actor function (override from actor)
	public void act() {
		if (canJump()) {
			jump();
		}
		else {
			turn();
		}
	}

	public boolean canJump() {
		// Get the grid where Jumper come from
		// <Actor> means there're only actors in the grid
		Grid<Actor> grid = getGrid();
		
		// Check the grid
		if (grid == null) {
			return false;
		}
		
		// Get the current location
		Location currentLocation = getLocation();
				
		// Get the next location
		Location nextLocation = currentLocation.getAdjacentLocation(getDirection());
		
		// Check the next location
		if (!grid.isValid(nextLocation)) {
			return false;
		}
		
		// Get the neighbor actor
		Actor neighborActor = grid.get(nextLocation);
		
		// Check the neighbor actor, jumper only can jump over the rock or flower
		if (neighborActor != null && !(neighborActor instanceof Rock) && !(neighborActor instanceof Flower)) {
			return false;
		}
		
		// Get the next two location
		Location twoNextLocation = nextLocation.getAdjacentLocation(getDirection());
		
		// Check the next two location
		if (!grid.isValid(twoNextLocation)) {
			return false;
		}
		
		// Get the two next neighbor (the neighbor of the neighbor)
		Actor twoNextNeighborActor = grid.get(twoNextLocation);
		
		// Check the two next neighbor (the jumper can jump on null or flower)
		if (twoNextNeighborActor == null || (twoNextNeighborActor instanceof Flower)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void jump() {
		// Get the gird first
		Grid<Actor> grid = getGrid();
		
		// Check the grid
		if (grid == null) {
			return;
		}
		
		// Get the current location
		Location currentLocation = getLocation();
		
		// Get the next location
		Location nextLocation = currentLocation.getAdjacentLocation(getDirection());
		
		// Get the two away location
		Location twoNextLocation = nextLocation.getAdjacentLocation(getDirection());
		
		// Move to the location or remove itself
		if (grid.isValid(twoNextLocation)) {
			moveTo(twoNextLocation);
		}
		else {
			removeSelfFromGrid();
		}
	}
	
	// Turn 45 degree
	public void turn() {
		setDirection(getDirection() + Location.HALF_RIGHT);
	}
}
