package spiralBug;

import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
	private int steps;
	private int sideLength;
	
	// Initial the spiralBug, set the sideLength to 3 
	public SpiralBug() {
		steps = 0;
		sideLength = 3;
	}
	
	// Override the act function, you needn't to change a lot 
	public void act() {
		if (steps < sideLength && canMove()) {
            move();
            steps++;
        }
		else {
			turn();
			turn();
			
			steps = 0;
			
			// You just need to inc the sideLength everytime
			sideLength = sideLength + 1;
		}
	}
}
