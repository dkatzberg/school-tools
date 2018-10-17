/**
 * 
 */
package de.dkatzberg.schooltools.grades.latex;

import java.util.List;

import de.dkatzberg.schooltools.common.config.I18nConfiguration;
import de.dkatzberg.schooltools.grades.domain.Grade;

/**
 * @author Daniel Katzberg
 */
public class GradeLatexWriter {

	public String writeLatexCodeBasedOnGrades(List<Grade> grades) {

		// Initialize all String Buffer
		StringBuffer latexGradeCode = new StringBuffer();
		StringBuffer latexTableDefinition = new StringBuffer();
		StringBuffer latexGradeList = new StringBuffer();
		StringBuffer latexPointsList = new StringBuffer();

		// TODO Switch Mode between normal grade and a-level grade
		// Build all latex table lines which depends on the grade elements in one
		// forEach
		grades.forEach(grade -> {
			latexTableDefinition.append(" X |");
			// last element needs no more & sign in latex, no more new column
			if (grade.getGradeALevel() == 0) {
				latexGradeList.append(grade.getGradeGeneral() + " ");
				latexPointsList.append(grade.getGradePoints().getFirstTupelElement() + " - "
						+ grade.getGradePoints().getSecondTupelElement() + " ");
				// standard case: one more new column
			} else {
				latexGradeList.append(grade.getGradeGeneral() + " & ");
				latexPointsList.append(grade.getGradePoints().getFirstTupelElement() + " - "
						+ grade.getGradePoints().getSecondTupelElement() + " & ");
			}

		});

		// Create the Latex Code
		latexGradeCode.append("\\begin{center}\r\n");
		latexGradeCode.append("	\\begin{tabularx}{\\textwidth}{ |" + latexTableDefinition.toString() + " }\r\n");
		latexGradeCode.append("		\\hline\r\n");
		latexGradeCode.append("		\\multicolumn{" + grades.size() + "}{|c|}{\\textbf{"
				+ I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.basisPercentage")
				+ "}} \\\\\r\n");
		latexGradeCode.append("		\\hline\r\n");
		latexGradeCode.append("		" + latexGradeList.toString() + "\\\\\r\n");
		latexGradeCode.append("		\\hline\r\n");
		latexGradeCode.append("		\\hline\r\n");
		latexGradeCode.append("		" + latexPointsList + "\\\\\r\n");
		latexGradeCode.append("		\\hline\r\n");
		latexGradeCode.append("	\\end{tabularx}\r\n");
		latexGradeCode.append("\\end{center}");

		// Return the complete Latex Code
		return latexGradeCode.toString();
	}

}
