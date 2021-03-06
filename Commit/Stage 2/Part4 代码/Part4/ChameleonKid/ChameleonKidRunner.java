package chameleonKid;

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class ChameleonKidRunner {
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock(Color.GREEN));
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(5, 7), new Rock(Color.GREEN));
        world.add(new Location(4, 6), new Rock(Color.BLUE));
        world.add(new Location(6, 6), new Rock(Color.RED));
        world.add(new Location(4, 5), new Rock(Color.BLACK));
        world.add(new Location(4, 7), new Rock(Color.WHITE));
        world.add(new Location(6, 5), new Rock(Color.CYAN));
        world.add(new Location(6, 7), new Rock(Color.GRAY));
        
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(7, 4), new ChameleonKid());
        world.add(new Location(5, 6), new ChameleonKid());
        world.show();
    }
}
