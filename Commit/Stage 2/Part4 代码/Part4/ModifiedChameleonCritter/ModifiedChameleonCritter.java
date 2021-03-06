package modifiedChameleonCritter;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.awt.Color;
import java.util.ArrayList;

//In "ModifiedChameleonCritterRunner" the Critter would darken it's color
//when the list of actors to process is empty
//You just need to change a little from the ChameleonCritter
public class ModifiedChameleonCritter extends Critter {
	// You can change the factor to control the darken speed
	private static final double DARKEN_FACTOR = 0.9;
	
	// ProcessActors() would process the actors around
    public void processActors(ArrayList<Actor> actors)
    {
    	// Get the actors size
        int n = actors.size();
        if (n == 0) {
        	// If the actors is empty, you need to darken the color
        	darken();
        	// Then return to the call function
        	return;
        }
            
        // else you would to set your color
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
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
