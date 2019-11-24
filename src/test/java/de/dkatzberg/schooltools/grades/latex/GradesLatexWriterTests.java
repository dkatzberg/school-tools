/**
 * 
 */
package de.dkatzberg.schooltools.grades.latex;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.dkatzberg.schooltools.grades.calculate.GradeCalculator;
import de.dkatzberg.schooltools.grades.domain.Grade;

/**
 * Test class for the class {@link GradesLatexWriter}.
 * 
 * @author Daniel Katzberg
 *
 */
public class GradesLatexWriterTests {

	@Test
	public void writeLatexCodeBasedOnGradesNormalModeTest() {
		//Arrange
		GradeCalculator gradeCalculator = new GradeCalculator();
		GradesLatexWriter gradesLatexWriter = new GradesLatexWriter();
		boolean aLevelMode = false;
		
		//Act
		List<Grade> grades = gradeCalculator.calculateGrades(5, 100, aLevelMode);
		String latexResult = gradesLatexWriter.writeLatexCodeBasedOnGrades(grades, aLevelMode);
		
		//Assert
		assertArrayEquals("{\\textwidth}{ | X | X | X | X | X | X |".toCharArray(), 
				latexResult.substring(33, 72).toCharArray());
		assertArrayEquals("\\multicolumn{15}".toCharArray(), 
				latexResult.substring(124, 140).toCharArray());
	}
	
	
	@Test
	public void writeLatexCodeBasedOnGradesALevelModeTest() {
		//Arrange
		GradeCalculator gradeCalculator = new GradeCalculator();
		GradesLatexWriter gradesLatexWriter = new GradesLatexWriter();
		boolean aLevelMode = true;
		
		//Act
		List<Grade> grades = gradeCalculator.calculateGrades(5, 100, aLevelMode);
		String latexResult = gradesLatexWriter.writeLatexCodeBasedOnGrades(grades, aLevelMode);
		
		System.out.println(latexResult.substring(128, 144));
		
		//Assert
		assertArrayEquals("{\\textwidth}{ | X | X | X | X | X | X |".toCharArray(), 
				latexResult.substring(33, 72).toCharArray());
		assertArrayEquals("\\multicolumn{16}".toCharArray(), 
				latexResult.substring(128, 144).toCharArray());
	}
	
}
