package circleBug;

import info.gridworld.actor.Bug;

public class CircleBug extends Bug {
	private int steps;
	private int sideLength;
	
	// You need to initial the CircleBug
	public CircleBug(int length) {
		// First the steps need to be 0
		// sideLength is the length you want to move once
		steps = 0;
		sideLength = length;
	}
	
	// You need to Override the act the function to achieve the target
	public void act()
    {
		// You needn't to change much from the BoxBug
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	// You just need to delete one turn
            turn();
            steps = 0;
        }
    }
}
