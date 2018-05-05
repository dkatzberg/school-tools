package main.java.de.dkatzberg.schooltools.grades;

import java.util.ArrayList;
import java.util.List;

import main.java.de.dkatzberg.schooltools.common.domain.Tupel;
import main.java.de.dkatzberg.schooltools.grades.domain.Grade;

/**
 * This package calculates the grades for a test.
 * 
 * @author Daniel Katzberg
 *
 */
public class GradeCalculator {
		
	//TODO Comments
	public List<Grade> calculateGrades(int basePercantage, int percentagePerGrade, double maxPoints) {
		
		List<Grade> grades = new ArrayList<>();
		int minPercantage = basePercantage + (11 * (percentagePerGrade));
		int maxPercentage = 100;
		String gradeGeneral = "1+";
		
		//First Grade
		Grade onePlus = new Grade(gradeGeneral, 15, null);
		if (minPercantage >= 100) {
			onePlus.setGradePercentageArea(new Tupel<Integer>(100, 100));
			minPercantage = 100;
		} else {
			onePlus.setGradePercentageArea(new Tupel<Integer>(minPercantage, 100));
		}
		onePlus.setGradePoints(this.calculatesGradePoints(onePlus.getGradePercentageArea(), maxPoints));
		grades.add(onePlus);
		
		//the most grades
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
		
		//last grade
		maxPercentage = minPercantage - 1;
		Tupel<Integer> percentageArea = new Tupel<Integer>(0, maxPercentage);
		grades.add(new Grade("6", 0, percentageArea, this.calculatesGradePoints(percentageArea, maxPoints)));		
		
		return grades;
	}
	
	//TODO Comment
	private Tupel<Double> calculatesGradePoints(Tupel<Integer> percantageArea, double maxPoints) {
		return new Tupel<Double>(
				(percantageArea.getFirstTupelElement() * maxPoints) / 100.0, 
				(percantageArea.getSecondTupelElement() * maxPoints) / 100.0 );
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
