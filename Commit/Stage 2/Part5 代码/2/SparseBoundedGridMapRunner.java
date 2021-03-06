package sparseGridMap;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class SparseBoundedGridMapRunner {
	public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("sparseGridMap.SparseBoundedGridMap");
        world.add(new Location(2, 2), new Critter());
        world.add(new Location(6, 5), new Bug());
        world.add(new Location(6, 2), new Actor());
        world.add(new Location(4, 1), new Flower());
        world.add(new Location(5, 5), new Rock());
        world.show();
    }
}
