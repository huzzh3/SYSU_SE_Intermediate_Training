package helloWorld;

import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.jupiter.api.*;

public class HelloWorldTest {

	@Test
	public void testIsGreater() {
		System.out.println("Test");
		HelloWorld helloworld = new HelloWorld();
		assertTrue("Num 1 is greater than Num 2", helloworld.isGreater(4, 3));
	}

}