package main.java.de.dkatzberg.schooltools.grades.domain;

import main.java.de.dkatzberg.schooltools.common.domain.Tupel;

/**
 * This domain class represent a grade. 
 * General representation like 2+;
 * A-Level-Grade representation like 14
 * And the percentage Level which is needed to reach a grade. 
 * 
 * @author Daniel Katzberg
 *
 */
public class Grade {
	
	private String gradeGeneral;
	
	private int gradeALevel;
	
	private Tupel<Integer> gradePercentageArea;	
	
	private Tupel<Double> gradePoints;
	
	/**
	 * Constructor with all the describing elements for a grade.
	 * 
	 * @param gradeGeneral The string representation of a grade like 2+
	 * @param gradeALevel The A Level representation of a grade like 13
	 * @param gradePercentageArea The percentage Area where a grade is given.
	 */
	public Grade(String gradeGeneral, int gradeALevel, Tupel<Integer> gradePercentageArea) {
		this.gradeGeneral = gradeGeneral;
		this.gradeALevel = gradeALevel;
		this.gradePercentageArea = gradePercentageArea;
	}
	
	/**
	 * Constructor with all the describing elements for a grade.
	 * 
	 * @param gradeGeneral The string representation of a grade like 2+
	 * @param gradeALevel The A Level representation of a grade like 13
	 * @param gradePercentageArea The percentage Area where a grade is given.
	 * @param gradePoints The points area where a grade is given
	 */
	public Grade(String gradeGeneral, int gradeALevel, Tupel<Integer> gradePercentageArea, Tupel<Double> gradePoints) {
		this.gradeGeneral = gradeGeneral;
		this.gradeALevel = gradeALevel;
		this.gradePercentageArea = gradePercentageArea;
		this.gradePoints = gradePoints;
	}

	/* GETTER / SETTER */
	public String getGradeGeneral() {
		return gradeGeneral;
	}

	public void setGradeGeneral(String gradeGeneral) {
		this.gradeGeneral = gradeGeneral;
	}

	public int getGradeALevel() {
		return gradeALevel;
	}

	public void setGradeALevel(int gradeALevel) {
		this.gradeALevel = gradeALevel;
	}

	public Tupel<Integer> getGradePercentageArea() {
		return gradePercentageArea;
	}

	public void setGradePercentageArea(Tupel<Integer> gradePercentageArea) {
		this.gradePercentageArea = gradePercentageArea;
	}

	public Tupel<Double> getGradePoints() {
		return gradePoints;
	}

	public void setGradePoints(Tupel<Double> gradePoints) {
		this.gradePoints = gradePoints;
	}		
}
