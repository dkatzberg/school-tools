/**
 * 
 */
package de.dkatzberg.schooltools.common.gui.tabs;

import java.util.List;

import de.dkatzberg.schooltools.common.config.I18nConfiguration;
import de.dkatzberg.schooltools.grades.calculate.GradeCalculator;
import de.dkatzberg.schooltools.grades.domain.Grade;
import de.dkatzberg.schooltools.grades.latex.GradeLatexWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * The GradeTab is a class with build and handles the Tab for Grade Calculation.
 * 
 * @author DKatzberg
 *
 */
//TODO Add Text Area for latex outputs and co. no longer output on System.out.println
public class GradeTab implements SchoolToolTab {

	private int percentagePerGrade = 5;
	private int maxPoints = 18;

	private CheckBox checkBoxOnePlus;
	private ToggleGroup gradeToggle;

	@Override
	public GridPane createTab() {
		// Creating a GridPane container
		GridPane gridPane = new GridPane();
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Defining the Percentage per Grade Field
		Text percentagePerGradeText = new Text(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.percentagePerGrade"));
		GridPane.setConstraints(percentagePerGradeText, 0, 1);
		gridPane.getChildren().add(percentagePerGradeText);
		TextField percentagePerGradeField = new TextField();
		percentagePerGradeField.setPromptText(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.percentagePerGrade"));
		percentagePerGradeField.setText(percentagePerGrade + "");
		GridPane.setConstraints(percentagePerGradeField, 1, 1);
		gridPane.getChildren().add(percentagePerGradeField);

		// Defining the max points field
		Text maxPointsText = new Text(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.maxPoints"));
		GridPane.setConstraints(maxPointsText, 0, 2);
		gridPane.getChildren().add(maxPointsText);
		TextField maxPointsField = new TextField();
		maxPointsField.setPrefColumnCount(15);
		maxPointsField
				.setPromptText(I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.maxPoints"));
		maxPointsField.setText(maxPoints + "");
		GridPane.setConstraints(maxPointsField, 1, 2);
		gridPane.getChildren().add(maxPointsField);

		// Headline for Radiobuttons
		Text gradeToggleHeadline = new Text(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.gradeToggle.headline"));
		GridPane.setConstraints(gradeToggleHeadline, 0, 3);
		gridPane.getChildren().add(gradeToggleHeadline);

		// Radiobuttons for toggle the grades
		this.gradeToggle = new ToggleGroup();
		RadioButton normalGradeRadioButton = new RadioButton(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.gradeToggle.normal"));
		normalGradeRadioButton.setToggleGroup(this.gradeToggle);
		GridPane.setConstraints(normalGradeRadioButton, 0, 4);
		gridPane.getChildren().add(normalGradeRadioButton);
		RadioButton aLevelGradeRadioButton = new RadioButton(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.gradeToggle.aLevel"));
		aLevelGradeRadioButton.setToggleGroup(this.gradeToggle);
		GridPane.setConstraints(aLevelGradeRadioButton, 1, 4);
		gridPane.getChildren().add(aLevelGradeRadioButton);
		this.gradeToggle.selectToggle(normalGradeRadioButton);

		// Checkbox for an 1+
		this.checkBoxOnePlus = new CheckBox(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.checkbox.onePlusAvailable"));
		GridPane.setConstraints(this.checkBoxOnePlus, 0, 5);
		gridPane.getChildren().add(checkBoxOnePlus);

		// Defining the Submit button
		Button calculateButton = new Button(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.button.calculate"));
		GridPane.setConstraints(calculateButton, 1, 6);
		this.defineActionHandling(calculateButton, 
				Integer.parseInt(percentagePerGradeField.getText()),
				Double.parseDouble(maxPointsField.getText()));
		gridPane.getChildren().add(calculateButton);

		return gridPane;
	}

	private void defineActionHandling(Button calculateButton, int percentagePerGrade, double maxPoints) {
		// Setting an action for the Submit button
		calculateButton.setOnAction(new EventHandler<ActionEvent>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			@Override
			public void handle(ActionEvent e) {
				if (maxPoints >= 15) {
					GradeCalculator gradeCalculator = new GradeCalculator();
					List<Grade> grades = gradeCalculator.calculateGrades(
							percentagePerGrade, maxPoints, 
							GradeTab.this.checkBoxOnePlus.isSelected());

					// TODO Remove: Print old list
					grades.forEach((grade) -> {
						System.out.println(grade.getGradeGeneral() + " (" + grade.getGradeALevel() + "): "
								+ grade.getGradePoints().getFirstTupelElement() + " - "
								+ grade.getGradePoints().getSecondTupelElement() + " ("
								+ grade.getGradePercentageArea().getFirstTupelElement() + "% - "
								+ grade.getGradePercentageArea().getSecondTupelElement() + "%)");
					});

					// Print output as latex code
					System.out.println("\n \n");
					GradeLatexWriter gradeLatexWriter = new GradeLatexWriter();
					System.out.println(
							gradeLatexWriter.writeLatexCodeBasedOnGrades(grades, GradeTab.this.isALevelModeChosen()));
				} else {
					System.out.println("Error: This method makes no sense with less 15.");
				}
			}
		});
	}

	/**
	 * This method checks for the RadioButtons for the Grade mode.
	 * 
	 * @return This method returns true, if A-Level Grade mode is chosen. It returns
	 *         false, if normal mode is chosen.
	 */
	private boolean isALevelModeChosen() {
		RadioButton selectedRadioButton = (RadioButton) this.gradeToggle.getSelectedToggle();
		return selectedRadioButton.getText()
				.equals(I18nConfiguration.getInstance().getStrings().getString("gui.grade.gradeToggle.aLevel"));
	}
}
