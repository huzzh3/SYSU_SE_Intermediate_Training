package circleBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class CircleBugRunner {
	public static void main(String[] args) {
		// Create a new actorWorld to place the bug
		ActorWorld world = new ActorWorld();
		
		// Create new circlebug and set the color
		CircleBug alice = new CircleBug(2);
		alice.setColor(Color.ORANGE);
		
		// Add the bug to the world and show
		world.add(new Location(7, 1), alice);
		world.show();
	}
}
