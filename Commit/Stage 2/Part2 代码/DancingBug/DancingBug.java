package dancingBug;

import info.gridworld.actor.Bug;

public class DancingBug extends Bug {
	// There're some variables you need
	private int   steps;
    private int   sideLength;
    private int[] turnArray;	// turnArray imply the turn you need to take every time
    private int   arrayLength;  // You need to use arrayLength to travel the array
    private int   counter;		// You need to use counter to travel the array
    
    // You need to initialize the dancingBug first 
    public DancingBug(int length, int[] arrayInput, int arrayInputLength)
    {
        steps        = 0;
        sideLength   = length;
        turnArray    = arrayInput;  		// Get the turnArray from the input
        arrayLength  = arrayInputLength;	// Get the arrayLength
        counter      = 0;					// Initialize the couner
    }
    
    // Then you can override the act function
    public void act()
    {
    	// You needn't change a lot from the BoxBug
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	// You just need to set the turn number every time
        	for (int i = 0; i < turnArray[counter]; i++) {
        		turn();
        	}
        	
        	// Remember to modulo to avoid the overflow
        	counter = (counter + 1) % arrayLength;
        	// Then initialize the steps to move another time
            steps = 0;
        }
    }
}
