package de.dkatzberg.schooltools.grades;

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
		String gradeGeneral = "1+";

		// First Grade
		Grade onePlus = new Grade(gradeGeneral, 15, null);
		if (minPercantage >= 100) {
			onePlus.setGradePercentageArea(new Tupel<Integer>(100, 100));
			minPercantage = 100;
		} else {
			onePlus.setGradePercentageArea(new Tupel<Integer>(minPercantage, 100));
		}
		onePlus.setGradePoints(this.calculatesGradePoints(onePlus.getGradePercentageArea(), maxPoints));
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
			grades.add(new Grade(gradeGeneral, aLevelGrade, percentageArea,
					this.calculatesGradePoints(percentageArea, maxPoints)));
		}

		// last grade
		maxPercentage = minPercantage - 1;
		Tupel<Integer> percentageArea = new Tupel<Integer>(0, maxPercentage);
		grades.add(new Grade("6", 0, percentageArea, this.calculatesGradePoints(percentageArea, maxPoints)));

		return grades;
	}

	/**
	 * This method calculates by a given percentage area the minimum and maximum of
	 * points that are needed for a specific grade. For example: A pupil needs for a
	 * 2+ grade between 80% and 84% of all points. The maximum points of an exam are
	 * 50. So 80% are minimum 40 points and maximum of 84% are 42 points. The return
	 * value is a tuple, where the first element is 40 and the second element is 42.
	 * 
	 * @param percantageArea The percentage area for an grade.
	 * @param maxPoints      The maximum points of an exam / test or what ever.
	 * @return A tuple with minimum and maximum points for a grade.
	 */
	private Tupel<Double> calculatesGradePoints(Tupel<Integer> percantageArea, double maxPoints) {
		return new Tupel<Double>((percantageArea.getFirstTupelElement() * maxPoints) / 100.0,
				(percantageArea.getSecondTupelElement() * maxPoints) / 100.0);
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
