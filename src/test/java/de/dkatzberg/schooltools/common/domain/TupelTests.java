/**
 * 
 */
package de.dkatzberg.schooltools.common.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Tupel class.
 * 
 * @author Daniel Katzberg
 *
 */
public class TupelTests {
	
	
	@Test
	public void stringTupelTest() {
		
		//Arrange
		Tupel<String> tupel = new Tupel<>("First String", "Second String");
		
		//Act
		
		//Assert
		assertArrayEquals("First String".toCharArray(), 
				tupel.getFirstTupelElement().toCharArray());
		assertArrayEquals("Second String".toCharArray(), 
				tupel.getSecondTupelElement().toCharArray());
		
	}
	
	@Test
	public void doubleTupelTest() {
		
		//Arrange
		Tupel<Double> tupel = new Tupel<>(123.45, 678.90);
		
		//Act
		
		//Assert
		assertEquals(123.45, tupel.getFirstTupelElement());
		assertEquals(678.90, tupel.getSecondTupelElement());		
	}

}
