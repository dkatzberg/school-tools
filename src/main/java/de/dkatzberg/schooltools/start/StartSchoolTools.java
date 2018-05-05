package main.java.de.dkatzberg.schooltools.start;

import java.util.List;

import main.java.de.dkatzberg.schooltools.grades.GradeCalculator;
import main.java.de.dkatzberg.schooltools.grades.domain.Grade;

/**
 * The main method, which starts the whole application.
 * 
 * @author Daniel Katzberg
 *
 */
public class StartSchoolTools {

	/**
	 * Main method, which starts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//TODO No defensive progamming.
		//TODO Comments
		int basePercantage = Integer.parseInt(args[0]);
		int percentagePerGrade = Integer.parseInt(args[1]);
		double maxPoints = Double.parseDouble(args[2]);
		
		GradeCalculator gradeCalculator = new GradeCalculator();
		List<Grade> grades = gradeCalculator.calculateGrades(basePercantage, percentagePerGrade, maxPoints);
		
		grades.forEach((grade) -> {
			System.out.println(
					grade.getGradeGeneral() + 
					" (" +grade.getGradeALevel() + "): " +
					grade.getGradePoints().getFirstTupelElement() + " - " + 
					grade.getGradePoints().getSecondTupelElement() + " (" +		
					grade.getGradePercentageArea().getFirstTupelElement() + "% - " + 
					grade.getGradePercentageArea().getSecondTupelElement() + "%)");
		});
	}

}
