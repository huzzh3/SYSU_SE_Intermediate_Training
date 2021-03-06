package spiralBug;

import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
	private int steps;
	private int sideLength;
	
	public SpiralBug() {
		steps = 0;
		sideLength = 3;
	}
	
	public void act() {
		if (steps < sideLength && canMove()) {
            move();
            steps++;
        }
		else {
			turn();
			turn();
			
			steps = 0;
			
			sideLength = sideLength + 1;
		}
	}
}
