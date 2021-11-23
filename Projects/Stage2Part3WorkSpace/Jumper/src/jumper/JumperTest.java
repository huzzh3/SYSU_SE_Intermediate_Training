package jumper;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

class JumperTest {
	
	@Test
	void testCanJumpOverFlower() {
		// Initialize
		ActorWorld world  = new ActorWorld();
		Flower     flower = new Flower();
		Jumper     alice  = new Jumper();
		
		// Add the Actor
		world.add(new Location(7,1), alice);
		world.add(new Location(6,1), flower);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		assertEquals(flower.getLocation().getRow() - 1, currentLocation.getRow());
		assertEquals(flower.getLocation().getCol(), currentLocation.getCol());
	}
	
	@Test
	void testCanJumpOverRock() {
		// Initialize
		ActorWorld world  = new ActorWorld();
		Rock       rock   = new Rock();
		Jumper     alice  = new Jumper();
		
		// Add the Actor
		world.add(new Location(7,1), alice);
		world.add(new Location(6,1), rock);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		assertEquals(rock.getLocation().getRow() - 1, currentLocation.getRow());
		assertEquals(rock.getLocation().getCol(), currentLocation.getCol());
	}

	@Test
	void testCanTurnFrontOfRock() {
		// If there're rock in front of the jumper (two away)
		// Test the jumper can turn
		
		// Initialize
		ActorWorld world  = new ActorWorld();
		Rock       rock   = new Rock();
		Jumper     alice  = new Jumper();
		
		// Add the Actor
		world.add(new Location(7,1), alice);
		world.add(new Location(5,1), rock);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		assertEquals(7, currentLocation.getRow());
		assertEquals(1, currentLocation.getCol());
		assertEquals(Location.HALF_RIGHT, alice.getDirection());
	}
	
	@Test
	void testCanOccupyFrontOfFlower() {
		// If there're flower in front of the jumper (two away)
		// Test the jumper can occupy
		
		// Initialize
		ActorWorld world  = new ActorWorld();
		Flower     flower = new Flower();
		Jumper     alice  = new Jumper();
		
		// Add the Actor
		world.add(new Location(7,1), alice);
		world.add(new Location(5,1), flower);
		
		// Get the flower location
		Location flowerLocation  = flower.getLocation();
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		// Test
		assertEquals(flowerLocation.getRow(), currentLocation.getRow());
		assertEquals(flowerLocation.getCol(), currentLocation.getCol());
	}
	
	@Test
	void testCanTurnAvoidOutsideGrid() {
		// If the target location is outside the grid
		// Then the jumper can turn to avoid jumping out
		
		// Initialize
		ActorWorld world  = new ActorWorld();
		Jumper     alice  = new Jumper();
		
		// Add the Actor
		world.add(new Location(1,1), alice);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		// Test
		assertEquals(1, currentLocation.getRow());
		assertEquals(1, currentLocation.getCol());
		assertEquals(Location.HALF_RIGHT, alice.getDirection());
	}
	
	@Test
	void testCanTurnFrontOfGridEdge() {
		// If the target location is outside the grid
		// Then the jumper can turn to avoid jumping out
		
		// Initialize
		ActorWorld world  = new ActorWorld();
		Jumper     alice  = new Jumper();
		
		// Add the Actor
		world.add(new Location(0,1), alice);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		// Test
		assertEquals(0, currentLocation.getRow());
		assertEquals(1, currentLocation.getCol());
		assertEquals(Location.HALF_RIGHT, alice.getDirection());
	}
	
	@Test
	void testCanTurnFrontOfActor() {
		// If the target location is outside the grid
		// Then the jumper can turn to avoid jumping out
		
		// Initialize
		ActorWorld world  = new ActorWorld();
		Jumper     alice  = new Jumper();
		Bug        bob    = new Bug();
		
		// Add the Actor
		world.add(new Location(7,1), alice);
		world.add(new Location(5,1), bob);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		// Test
		assertEquals(7, currentLocation.getRow());
		assertEquals(1, currentLocation.getCol());
		assertEquals(Location.HALF_RIGHT, alice.getDirection());
	}
	
	@Test
	void testCanTurnFrontOfJumper() {
		// If the target location is outside the grid
		// Then the jumper can turn to avoid jumping out
		
		// Initialize
		ActorWorld world  = new ActorWorld();
		Jumper     alice  = new Jumper();
		Jumper     bob    = new Jumper();
		
		// Add the Actor
		world.add(new Location(7,1), alice);
		world.add(new Location(5,1), bob);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		// Test
		assertEquals(7, currentLocation.getRow());
		assertEquals(1, currentLocation.getCol());
		assertEquals(Location.HALF_RIGHT, alice.getDirection());
	}
	
	@Test
	void testCanTurnFrontOfActorOneAway() {
		// If the target location is outside the grid
		// Then the jumper can turn to avoid jumping out
		
		// Initialize
		ActorWorld world  = new ActorWorld();
		Jumper     alice  = new Jumper();
		Jumper     bob    = new Jumper();
		
		// Add the Actor
		world.add(new Location(7,1), alice);
		world.add(new Location(6,1), bob);
		
		// Act
		alice.act();
		
		// Get the location
		Location currentLocation = alice.getLocation();
		
		// Test
		assertEquals(7, currentLocation.getRow());
		assertEquals(1, currentLocation.getCol());
		assertEquals(Location.HALF_RIGHT, alice.getDirection());
	}
}
