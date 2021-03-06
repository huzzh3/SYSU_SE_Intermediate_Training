package rockHound;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class RockHound extends Critter {
	
	// RockHound would remove the rocks around
	public void processActors(ArrayList<Actor> actors) {
		
		// Process the actor in the actors
		for (Actor actor : actors) {
			if (actor instanceof Rock) {
				// Remove the rock
				actor.removeSelfFromGrid();
			}
		}
	}   
}
