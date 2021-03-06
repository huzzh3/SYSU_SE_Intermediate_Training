package blusterCritter;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter {
	// The courageFactor would be set in constructor
	private int courageFactor;
	// You can change the factor to control the darken speed
	private static final double DARKEN_FACTOR = 0.9;
	// You can change the factor to control the lighten speed
	private static final double LIGHTEN_FACTOR = 1.1;
	
	public BlusterCritter(int courageFactorInput) {
		// Get the constructor from the parent class
		super();
		
		// Set the courageFactor
		courageFactor = courageFactorInput;
		
		// Set the color
		setColor(Color.GRAY);
	}
	
	// Get the actors two steps away
    public ArrayList<Actor> getActors()
    {
    	// You need to return actors
        ArrayList<Actor> actors = new ArrayList<Actor>();
        
        // Get the current location
        Location currentLocation = getLocation();
        
        // Get the actors
        for (int row = currentLocation.getRow() - 2; row <= currentLocation.getRow() + 2; row++) {
        	for (int col = currentLocation.getCol() - 2; col <= currentLocation.getCol() + 2; col++) {
        		Location tempLocation = new Location(row, col);
        		
        		// Check the validity of the location
        		if (getGrid().isValid(tempLocation)) {
        			Actor actor = getGrid().get(tempLocation);
        			
        			// Check if there's actor and it can't be self
        			if (actor != null && actor != this) {
        				actors.add(actor);
        			}
        		}
        	}
        }
        
        // Return
        return actors;
    }
    
	// ProcessActors() would process the actors around
    public void processActors(ArrayList<Actor> actors)
    {
    	// Count used to count the number of critters
    	int count = 0;
    	
    	// Get the number of critters
    	for (Actor actor: actors) {
    		if (actor instanceof Critter) {
    			count++;
    		}
    	}
    	
    	// Check the count to determine to lighten or darken
    	if (count < courageFactor) {
    		lighten();
    	}
    	else {
    		darken();
    	}
    }
    
    // You can edit darken() to get lighten()
	public void lighten() {
		// Get self color
		Color color = getColor();
		
		// Get RGB and change
		int red   = (int) (color.getRed() * LIGHTEN_FACTOR);
		int green = (int) (color.getGreen() * LIGHTEN_FACTOR);
		int blue  = (int) (color.getBlue() * LIGHTEN_FACTOR);
		if (red >= 255) {
			red = 254;
		}
		if (green >= 255) {
			green = 254;
		}
		if (blue >= 255) {
			blue = 254;
		}
		
		// Set the color
		setColor(new Color(red, green, blue));
	}
    
    // You can get darken() from flower.class
	public void darken() {
		// Get self color
		Color color = getColor();
		
		// Get RGB and change
		int red   = (int) (color.getRed() * DARKEN_FACTOR);
		int green = (int) (color.getGreen() * DARKEN_FACTOR);
		int blue  = (int) (color.getBlue() * DARKEN_FACTOR);
		
		// Set the color
		setColor(new Color(red, green, blue));
	}
}
