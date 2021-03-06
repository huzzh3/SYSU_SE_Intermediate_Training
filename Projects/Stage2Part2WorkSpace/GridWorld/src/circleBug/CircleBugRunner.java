package circleBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class CircleBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		
		CircleBug alice = new CircleBug(2);
		alice.setColor(Color.ORANGE);
		
		world.add(new Location(7, 1), alice);
		world.show();
	}
}
