package kingCrab;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import quickCrab.CrabCritter;

public class KingCrabRunner {
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        
        world.add(new Location(3, 5), new CrabCritter());
        world.add(new Location(3, 6), new Bug());
        world.add(new Location(3, 7), new Rock());
        world.add(new Location(4, 6), new KingCrab());
        
        world.add(new Location(0, 0), new CrabCritter());
        world.add(new Location(0, 1), new Bug());
        world.add(new Location(0, 2), new Rock());
        world.add(new Location(1, 1), new KingCrab());
        
        world.show();
    }
}
