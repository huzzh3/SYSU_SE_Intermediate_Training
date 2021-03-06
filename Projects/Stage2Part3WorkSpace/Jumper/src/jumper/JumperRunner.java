package jumper;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperRunner {
	// Set the runner for jumper
	public static void main(String[] args) {
		
		// Initialize the world
		ActorWorld world = new ActorWorld();
		
		// Declare the actors
		Jumper alice  = new Jumper();
		Jumper peter  = new Jumper();
		Rock   rock   = new Rock();
		Flower flower = new Flower();
		Bug    bob    = new Bug();
		
		// Add the actors
		world.add(new Location(7,1), alice);
		world.add(new Location(6,1), bob);
		
		// Show the world
		world.show();
	}
}
