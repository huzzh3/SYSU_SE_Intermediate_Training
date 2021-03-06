package spiralBug;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public class SpiralBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new UnboundedGrid<Actor>());
		
		SpiralBug alice = new SpiralBug();
		alice.setColor(Color.ORANGE);
		
		world.add(new Location(15, 15), alice);
		
		world.show();
	}
}
