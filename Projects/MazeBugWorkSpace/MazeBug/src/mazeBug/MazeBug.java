package mazeBug;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	boolean started  = false;
	
	// Used to predict direction selection
	private int NumberOfDirectionChoices[] = {1, 1, 1, 1};

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	
	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		// Determine if the game is over
		boolean willMove = canMove();
		        isEnd    = isReachRedRock();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		}
		else if (started == false) {
			// At this point, the bug has not moved, initializing the direction prediction
			initialDirectionPrediction();
			
			// Initialize the tree
			ArrayList<Location> firstNode = new ArrayList<Location>();
			Location      currentLocation = getLocation();
			firstNode.add(currentLocation);
			crossLocation.add(firstNode);
			
			started = true;
			
			act();
		}
		else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		}
		else {
			getBack();
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return null;
		}
		
		ArrayList<Location> validLocations = new ArrayList<Location>();
		Location currentLocation           = getLocation();
		
		int direction = Location.NORTH;
		
		for (int i = 0; i < 4; i++) {
			Location targetLocation = currentLocation.getAdjacentLocation(direction);
			
			if (gr.isValid(targetLocation) && gr.get(targetLocation) == null) {
				validLocations.add(targetLocation);
			}
			
			direction += Location.RIGHT;
			
		}
		
		return validLocations;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> locs = getValid(getLocation());
		
		if (locs.isEmpty()) {
			return false;
		}
		
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		ArrayList<Location> validLocations = null;
		
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
			
		Location loc = getLocation();
		
		// Get all the locations you can move to
		validLocations = getValid(loc);
		
		// Set next by function
		findMostLikelyLocation(validLocations);
		
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} 
		else {
			removeSelfFromGrid();
		}
			
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	/**
	 * 		Judge whether the bug has reached the end
	 */
	public boolean isReachRedRock() {
		// Necessary Variable
		Grid<Actor> gr                                = null;
		Location currentLocation                      = null;
		ArrayList<Location> occupiedAdjacentLocations = new ArrayList<Location>();
		
		// Get the grid
		gr = getGrid();
		
		// Get current Location
		currentLocation = getLocation();
		
		// Get occupied adjacent locations
		int direction = Location.NORTH;
		for (int i = 0; i < 4; i++) {
			Location targetLocation = currentLocation.getAdjacentLocation(direction);
			
			if (gr.isValid(targetLocation) && gr.get(targetLocation) != null) {
				occupiedAdjacentLocations.add(targetLocation);
			}
			
			direction += Location.RIGHT;
		}
		
		// Determine whether the occupied adjacent position is the end point
		for (Location loc:occupiedAdjacentLocations) {
			Actor actor = gr.get(loc);
			
			if ((actor instanceof Rock) && (actor.getColor().equals(Color.RED))) {
				// Change the direction of the bug so that it faces the end point
				setDirection(currentLocation.getDirectionToward(loc));
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 *  	Initial direction prediction
	 */
	public void initialDirectionPrediction() {
		Grid<Actor>         gr    = null;
		ArrayList<Location> locs  = null;
		Location endPointLocation = null;
		Location currentLocation  = null;
		int      rowDifference    = 0;
		int      colDifference    = 0;
		
		// Get the grid
		gr = getGrid();
		
		// Get all the occupied location of the end point
		locs = gr.getOccupiedLocations();
		
		// Get the location of end point
		for (Location loc:locs) {
			Actor actor = gr.get(loc);
			
			if ((actor instanceof Rock) && (actor.getColor().equals(Color.RED))) {
				endPointLocation = loc;
			}
		}
		
		// Get the current Location
		currentLocation = getLocation();
		
		// Make direction predictions
		rowDifference = endPointLocation.getRow() - currentLocation.getRow();
		colDifference = endPointLocation.getCol() - currentLocation.getCol();
		if (rowDifference < 0) {
			NumberOfDirectionChoices[0] -= rowDifference;
		}
		else if (rowDifference >= 0) {
			NumberOfDirectionChoices[2] += rowDifference;
		}
		
		if (colDifference > 0) {
			NumberOfDirectionChoices[1] += colDifference;
		}
		else if (colDifference <= 0) {
			NumberOfDirectionChoices[3] -= colDifference;
		}
    }
	
	/**
	 * 		Find the most likely location
	 */
	public void findMostLikelyLocation(ArrayList<Location> locs) {
		Location currentLocation = null;
		int      direction       = 0;
		int      total           = 0;
		int      randomNumber    = 0;
		
		// Get current Location
		currentLocation = getLocation();
		
		// Check the number of available locations
		if (locs.size() == 1) {
			next = locs.get(0);
			
			direction = currentLocation.getDirectionToward(next);
			
			NumberOfDirectionChoices[direction / 90]++;
			
			// Update tree
			ArrayList<Location> currentNode = crossLocation.pop();
			currentNode.add(next);
			crossLocation.push(currentNode);
		}
		else {
			// At this time, there are multiple locations to choose from
			
			// Calculate total DirectionChoices
			total = 0;
			for (int i = 0; i < locs.size(); i++) {
				direction = currentLocation.getDirectionToward(locs.get(i));
				
				total += NumberOfDirectionChoices[direction / 90];
			}
			
			
			// Get random number
			Random random = new Random();
		    randomNumber  = random.nextInt(total) + 1;
		    
		    // Choose next location
		    int temp = 1;
			for (int i = 0; i < locs.size(); i++) {
				direction = currentLocation.getDirectionToward(locs.get(i));
				
				if (temp <= randomNumber && randomNumber < temp + NumberOfDirectionChoices[direction / 90]) {
					next = locs.get(i);
					break;
				}
				else {
					temp += NumberOfDirectionChoices[direction / 90];
				}
			}
			
			// Update tree
			ArrayList<Location> newNode = new ArrayList<Location>();
			newNode.add(currentLocation);
			newNode.add(next);
			crossLocation.push(newNode);
		}
	}
	
	public void getBack() {
		ArrayList<Location> currentNode = crossLocation.pop();
		int numberOfLocation            = currentNode.size();
		Location currentLocation        = null;
		Location previousLocation       = null;
		int      backDirection          = 0;
		int      reversedDirection      = 0;
		
		if (numberOfLocation > 1) {
			// Get current location and previous location from current node
		    currentLocation        = currentNode.get(numberOfLocation - 1);
			previousLocation       = currentNode.get(numberOfLocation - 2);
			
			// Move to the previous location
			backDirection = getLocation().getDirectionToward(previousLocation);
			setDirection(backDirection);
			moveTo(previousLocation);
			
			// Leave flower behind
			Flower flower = new Flower(getColor());
			flower.putSelfInGrid(getGrid(), currentLocation);
			
			// Remove current location
			currentNode.remove(numberOfLocation - 1);
			
			// Push back
			crossLocation.push(currentNode);
			
			// Update NumberOfDirectionChoices
			NumberOfDirectionChoices[backDirection / 90]++;
			reversedDirection = (backDirection + 180) % 360;
			if (NumberOfDirectionChoices[reversedDirection / 90] > 1) {
				NumberOfDirectionChoices[reversedDirection / 90]--;
			}
			
		}
	}
}
