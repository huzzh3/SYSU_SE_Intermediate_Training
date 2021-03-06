package zBug;

import info.gridworld.actor.Bug;

public class ZBug extends Bug {
	private int steps;
	private int sideLength;
	private int counter;
	
	public ZBug(int length) {
		steps      = 0;
		sideLength = length;
		counter    = 0;
		
		this.setDirection(90);
	}
	
	public void act() {
		if (steps < sideLength && canMove() && counter <= 2) {
            move();
            steps++;
        }
		else if (steps == sideLength && canMove() && counter <= 2) {
			
			if (counter == 0) {
				this.setDirection(225);
			}
			else if (counter == 1) {
				this.setDirection(90);
			}
			
			steps = 0;
			
			counter = counter + 1;
		}
		else if (!canMove() || counter > 2) {
			// empty
		}
	}
	
}
