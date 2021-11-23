package dancingBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class DancingBugRunner {
	public static void main(String[] args) {
		// Create a new actorWorld to place the bug
		ActorWorld world = new ActorWorld();
		
		// Initialize the turnArray
		int[] turnArrayInput = new int[5];
		turnArrayInput[0] = 1;
		turnArrayInput[1] = 2;
		turnArrayInput[2] = 3;
		turnArrayInput[3] = 4;
		turnArrayInput[4] = 5;
		
		// Create new circlebug and set the color
		DancingBug alice = new DancingBug(4, turnArrayInput, 5);
		
		// Add the bug to the world and show
		world.add(new Location(7, 1), alice);
		world.show();
	}
}
