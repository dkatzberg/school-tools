/**
 * 
 */
package de.dkatzberg.schooltools.grades.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.dkatzberg.schooltools.common.domain.Tupel;

/**
 * Tests the {@link Grade} domain class.
 * 
 * @author Daniel Katzberg
 *
 */
public class GradeTests {
	
	@Test
	public void gradesWithThreeParametersTest() {
		//Arrange
		Grade grade = new Grade("1-", 13, new Tupel<Integer>(80, 84));
		
		//Act
		
		//Assert
		assertEquals(13, grade.getGradeALevel());
		assertArrayEquals("1-".toCharArray(), 
				grade.getGradeGeneral().toCharArray());
		assertEquals(80, grade.getGradePercentageArea().getFirstTupelElement());
		assertEquals(84, grade.getGradePercentageArea().getSecondTupelElement());
	}
	
	@Test
	public void gradesWithFourParametersTest() {
		//Arrange
		Grade grade = new Grade(
				"2-", 
				10, 
				new Tupel<Integer>(70, 74), 
				new Tupel<Long>((long)153,(long)178)
				);
		
		//Act
		
		//Assert
		assertEquals(10, grade.getGradeALevel());
		assertArrayEquals("2-".toCharArray(), 
				grade.getGradeGeneral().toCharArray());
		assertEquals(70, grade.getGradePercentageArea().getFirstTupelElement());
		assertEquals(74, grade.getGradePercentageArea().getSecondTupelElement());
		assertEquals((long)153, grade.getGradePoints().getFirstTupelElement());
		assertEquals((long)178, grade.getGradePoints().getSecondTupelElement());
	}

}
