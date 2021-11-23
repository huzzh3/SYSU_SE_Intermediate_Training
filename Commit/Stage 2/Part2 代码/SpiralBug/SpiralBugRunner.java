package spiralBug;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public class SpiralBugRunner {
	public static void main(String[] args) {
		// Create a new actorWorld to place the bug
		ActorWorld world = new ActorWorld(new UnboundedGrid<Actor>());
		
		// Create new SpiralBug and set the color
		SpiralBug alice = new SpiralBug();
		alice.setColor(Color.ORANGE);
		
		// Add the bug to the world and show
		world.add(new Location(15, 15), alice);
		world.show();
	}
}
