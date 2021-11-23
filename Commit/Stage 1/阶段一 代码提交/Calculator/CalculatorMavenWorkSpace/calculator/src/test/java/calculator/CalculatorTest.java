package calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testMain() {
		Calculator calculator = new Calculator();
		
		calculator.operator = Calculator.PLUS;
		assertEquals(100, (int)calculator.calculate(40, 60));
		System.out.println("plus function is OK");
		
		calculator.operator = Calculator.MINUS;
		assertEquals(70, (int)calculator.calculate(100, 30));
		System.out.println("minus function is OK");
		
		calculator.operator = Calculator.MUL;
		assertEquals(500, (int)calculator.calculate(25, 20));
		System.out.println("multiple function is OK");
		
		calculator.operator = Calculator.DIV;
		assertEquals(10, (int)calculator.calculate(300, 30));
		System.out.println("divide function is OK");
		
	}

}