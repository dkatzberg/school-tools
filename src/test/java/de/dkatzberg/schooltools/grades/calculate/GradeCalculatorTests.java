/**
 * 
 */
package de.dkatzberg.schooltools.grades.calculate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.dkatzberg.schooltools.grades.domain.Grade;

/**
 * Test class for the class {@link GradeCalculator}.
 * 
 * @author Daniel Katzberg
 *
 */
public class GradeCalculatorTests {
	
	private GradeCalculator gradeCalculator;
	
	@BeforeEach
	public void init() {
		this.gradeCalculator = new GradeCalculator();
	}
	
	@AfterEach
	public void handleObjectsAfterTests() {
		this.gradeCalculator = null;
	}

	@Test
	public void calculateGradesWithoutOnePlusTest() {
		//Arrange
		
		//Act
		List<Grade> grades = 
				this.gradeCalculator.calculateGrades(5, 178, false);
		
		//Assert
		assertEquals(8, grades.get(6).getGradeALevel());
		assertArrayEquals("3-".toCharArray(), 
				grades.get(7).getGradeGeneral().toCharArray());
		assertEquals(56, grades.get(8).getGradePercentageArea().getFirstTupelElement());
		assertEquals(55, grades.get(9).getGradePercentageArea().getSecondTupelElement());
		assertEquals((long)162, grades.get(1).getGradePoints().getFirstTupelElement());
		assertEquals((long)161, grades.get(2).getGradePoints().getSecondTupelElement());
	}
	
	@Test
	public void calculateGradesWithOnePlusTest() {
		//Arrange
		
		//Act
		List<Grade> grades = 
				this.gradeCalculator.calculateGrades(5, 273, true);
		
		//Assert
		assertEquals(9, grades.get(6).getGradeALevel());
		assertArrayEquals("3".toCharArray(), 
				grades.get(7).getGradeGeneral().toCharArray());
		assertEquals(56, grades.get(8).getGradePercentageArea().getFirstTupelElement());
		assertEquals(55, grades.get(9).getGradePercentageArea().getSecondTupelElement());
		assertEquals((long)248, grades.get(1).getGradePoints().getFirstTupelElement());
		assertEquals((long)247, grades.get(2).getGradePoints().getSecondTupelElement());
	}
}
