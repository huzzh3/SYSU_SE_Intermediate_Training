package blusterCritter;

import java.awt.Color;

import chameleonKid.ChameleonKid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class BlusterCritterRunner {
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        
        world.add(new Location(5, 7), new Critter());
        world.add(new Location(4, 6), new Critter());
        world.add(new Location(6, 6), new Critter());
        world.add(new Location(4, 5), new Critter());
        world.add(new Location(4, 7), new Critter());
        world.add(new Location(6, 5), new Critter());
        world.add(new Location(6, 7), new Critter());
        world.add(new Location(4, 4), new Critter());
        world.add(new Location(5, 4), new Critter());
        world.add(new Location(6, 4), new Critter());
        
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(4, 3), new Rock());
        world.add(new Location(5, 3), new Rock());
        world.add(new Location(6, 3), new Rock());
        world.add(new Location(7, 3), new Rock());
        world.add(new Location(3, 8), new Rock());
        world.add(new Location(4, 8), new Rock());
        world.add(new Location(5, 8), new Rock());
        world.add(new Location(6, 8), new Rock());
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 4), new Rock());
        world.add(new Location(3, 5), new Rock());
        world.add(new Location(3, 6), new Rock());
        world.add(new Location(3, 7), new Rock());
        world.add(new Location(7, 4), new Rock());
        world.add(new Location(7, 5), new Rock());
        world.add(new Location(7, 6), new Rock());
        world.add(new Location(7, 7), new Rock());
        
        
        world.add(new Location(5, 5), new BlusterCritter(4));
        world.add(new Location(5, 6), new BlusterCritter(20));
        world.show();
    }
}
