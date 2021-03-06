package zBug;

import info.gridworld.actor.Bug;

public class ZBug extends Bug {
	private int steps;
	private int sideLength;
	private int counter;	   // You can use the counter to stop the bug
	
	// Initialize the ZBug
	public ZBug(int length) {
		steps      = 0;
		sideLength = length;
		counter    = 0;		   // Remember to initialize the counter to 0 
		
		this.setDirection(90); // Set the direction to the EAST
	}
	
	// You need to override the act function
	public void act() {
		// If you needn't to turn
		if (steps < sideLength && canMove() && counter <= 2) {
            move();
            steps++;
        }
		else if (steps == sideLength && canMove() && counter <= 2) {
			// If you need to turn first time
			if (counter == 0) {
				this.setDirection(225);
			}
			else if (counter == 1) {
				// If you need to turn second time
				this.setDirection(90);
			}
			
			steps = 0;
			
			counter = counter + 1;
		}
		else if (!canMove() || counter > 2) {
			// empty
			// You needn't do anything
		}
	}
	
}
