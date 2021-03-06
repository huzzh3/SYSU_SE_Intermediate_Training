package zBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class ZBugRunner {

	public static void main(String[] args) {
		// Create a new actorWorld to place the bug
		ActorWorld world = new ActorWorld();
		
		// Create new ZBug and set the color
		ZBug alice = new ZBug(4);
		alice.setColor(Color.ORANGE);
		
		// Add the bug to the world and show
		world.add(new Location(4, 1), alice);
		world.show();
	}
}
