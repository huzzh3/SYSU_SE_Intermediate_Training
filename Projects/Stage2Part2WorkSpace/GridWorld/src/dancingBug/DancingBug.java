package dancingBug;

import info.gridworld.actor.Bug;

public class DancingBug extends Bug {
	private int   steps;
    private int   sideLength;
    private int[] turnArray;
    private int   arrayLength;
    private int   counter;
    
    public DancingBug(int length, int[] arrayInput, int arrayInputLength)
    {
        steps        = 0;
        sideLength   = length;
        turnArray    = arrayInput;
        arrayLength  = arrayInputLength;
        counter      = 0;
    }
    
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	for (int i = 0; i < turnArray[counter]; i++) {
        		turn();
        	}
        	
        	counter = (counter + 1) % arrayLength;
            steps = 0;
        }
    }
}
