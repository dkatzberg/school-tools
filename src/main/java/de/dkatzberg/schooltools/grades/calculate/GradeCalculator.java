package de.dkatzberg.schooltools.grades.calculate;

import java.util.ArrayList;
import java.util.List;

import de.dkatzberg.schooltools.common.domain.Tupel;
import de.dkatzberg.schooltools.grades.domain.Grade;

/**
 * This package calculates the grades for a test.
 * 
 * @author Daniel Katzberg
 *
 */
//TODO Round the grades to full points and check that the following has + 1 Points
public class GradeCalculator {

	/**
	 * The first called and only calculation grade method. This method calculated
	 * all grades with depending percentage areas and needed points. The method
	 * returns a list of {@link Grade}s.
	 * 
	 * @param basePercantage     The base percentage is the first grade where the
	 *                           percentage per grade begin. This is very often (and
	 *                           not always) a 4 (general grade, not a German
	 *                           a-level grade).
	 * @param percentagePerGrade The allowed percentage for a grade.
	 * @param maxPoints          The maximum points for an test / exam or what ever.
	 * @return It returns a list of all calculated Grades with the given percentage
	 *         and points information.
	 */
	public List<Grade> calculateGrades(int basePercantage, int percentagePerGrade, double maxPoints) {

		List<Grade> grades = new ArrayList<>();
		// TODO The 11 should not hard coded, but an parameter
		int minPercantage = basePercantage + (11 * (percentagePerGrade));
		int maxPercentage = 100;

		// TODO 1+ is not in every case the best grade. It is an 1 in some classes.
		String gradeGeneral = "1+";

		// First Grade
		Grade onePlus = new Grade(gradeGeneral, 15, null);
		if (minPercantage >= 100) {
			onePlus.setGradePercentageArea(new Tupel<Integer>(100, 100));
			minPercantage = 100;
		} else {
			onePlus.setGradePercentageArea(new Tupel<Integer>(minPercantage, 100));
		}
		// -1 means the default value. There is no better grade.
		onePlus.setGradePoints(this.calculatesGradePoints(onePlus.getGradePercentageArea(), maxPoints, -1));
		grades.add(onePlus);

		// the most grades
		for (int aLevelGrade = 14; aLevelGrade >= 1; aLevelGrade--) {
			maxPercentage = minPercantage - 1;
			if (aLevelGrade <= 2) {
				minPercantage -= (percentagePerGrade * 2);
			} else {
				minPercantage -= percentagePerGrade;
			}
			gradeGeneral = this.reduceGradeGeneral(gradeGeneral);
			Tupel<Integer> percentageArea = new Tupel<Integer>(minPercantage, maxPercentage);
			grades.add(new Grade(gradeGeneral, aLevelGrade, percentageArea, this.calculatesGradePoints(percentageArea,
					maxPoints, grades.get(grades.size() - 1).getGradePoints().getFirstTupelElement())));
		}

		// last grade
		maxPercentage = minPercantage - 1;
		Tupel<Integer> percentageArea = new Tupel<Integer>(0, maxPercentage);
		grades.add(new Grade("6", 0, percentageArea, this.calculatesGradePoints(percentageArea, maxPoints,
				grades.get(grades.size() - 1).getGradePoints().getFirstTupelElement())));

		return grades;
	}

	/**
	 * This method calculates by a given percentage area the minimum and maximum of
	 * points that are needed for a specific grade. For example: A pupil needs for a
	 * 2+ grade between 80% and 84% of all points. The maximum points of an exam are
	 * 50. So 80% are minimum 40 points and maximum of 84% are 42 points. The return
	 * value is a tuple, where the first element is 40 and the second element is 42.
	 * 
	 * @param percantageArea     The percentage area for an grade.
	 * @param maxPoints          The maximum points of an exam / test or what ever.
	 * @param betterGradeMinimum The minimum point of the better grade
	 * @return A tuple with minimum and maximum points for a grade.
	 */
	private Tupel<Long> calculatesGradePoints(Tupel<Integer> percantageArea, double maxPoints,
			long betterGradeMinimum) {

		// Calculate minimum and maximum points for a grade
		long maximumPointsOfGrade = Math.round((percantageArea.getSecondTupelElement() * maxPoints) / 100.0);
		long minimumPointsOfGrade = Math.round((percantageArea.getFirstTupelElement() * maxPoints) / 100.0);

		//Check for special cases
		if (betterGradeMinimum != -1) {
			// Check for round mistakes with high points
			if ((betterGradeMinimum - maximumPointsOfGrade) >= 2) {
				maximumPointsOfGrade = betterGradeMinimum - 1;
			}

			// Check for round mistakes with low points
			if (maximumPointsOfGrade >= betterGradeMinimum) {
				maximumPointsOfGrade = betterGradeMinimum - 1;
			}

			// Check for round mistakes with low points
			if (minimumPointsOfGrade > maximumPointsOfGrade) {
				minimumPointsOfGrade = maximumPointsOfGrade;
			}
		}

		// Return Tupel
		return new Tupel<Long>(minimumPointsOfGrade, maximumPointsOfGrade);
	}

	/**
	 * Change the String of the general grade.
	 * 
	 * @param gradeGeneral The general grade.
	 * @return The new general grade representation.
	 */
	private String reduceGradeGeneral(String gradeGeneral) {
		if (gradeGeneral.contains("+")) {
			return gradeGeneral.replace("+", "");
		}

		if (!gradeGeneral.contains("+") && !gradeGeneral.contains("-")) {
			return gradeGeneral + "-";
		}

		if (gradeGeneral.contains("-")) {
			gradeGeneral = gradeGeneral.replace("-", "");
			return (Integer.parseInt(gradeGeneral) + 1) + "+";
		}

		return gradeGeneral;
	}

}
