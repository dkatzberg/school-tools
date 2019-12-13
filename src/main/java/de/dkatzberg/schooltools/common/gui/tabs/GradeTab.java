/**
 * 
 */
package de.dkatzberg.schooltools.common.gui.tabs;

import java.util.List;

import de.dkatzberg.schooltools.common.config.I18nConfiguration;
import de.dkatzberg.schooltools.grades.calculate.GradeCalculator;
import de.dkatzberg.schooltools.grades.domain.Grade;
import de.dkatzberg.schooltools.grades.latex.GradesLatexWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
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
public class GradeTab implements SchoolToolTab {
	
	private static int DEFAULT_PERCENTAGE_PER_GRADE = 5;
	
	private TextField maxPointsField;
	
	private TextArea textArea;

	private CheckBox checkBoxOnePlus;
	private ToggleGroup gradeToggle;
	
	/**
	 * Default Constructor of the Grade Tab.
	 */
	public GradeTab() {}

	
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
		percentagePerGradeField.setText(DEFAULT_PERCENTAGE_PER_GRADE + "");
		percentagePerGradeField.setEditable(false);
		percentagePerGradeField.setDisable(true);
		GridPane.setConstraints(percentagePerGradeField, 1, 1);
		gridPane.getChildren().add(percentagePerGradeField);

		// Defining the max points field
		Text maxPointsText = new Text(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.maxPoints"));
		GridPane.setConstraints(maxPointsText, 0, 2);
		gridPane.getChildren().add(maxPointsText);
		
		this.maxPointsField = new TextField();
		this.maxPointsField.setPrefColumnCount(15);
		this.maxPointsField
				.setPromptText(I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.maxPoints"));
		this.maxPointsField.setText(18 + "");//Default Value
		GridPane.setConstraints(this.maxPointsField, 1, 2);
		gridPane.getChildren().add(this.maxPointsField);

		// Headline for Radiobuttons
		Text gradeToggleHeadline = new Text(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.gradeToggle.headline"));
		GridPane.setConstraints(gradeToggleHeadline, 0, 3);
		gridPane.getChildren().add(gradeToggleHeadline);

		// Radiobuttons for toggle the grades
		this.setGradeToggle(new ToggleGroup());
		RadioButton normalGradeRadioButton = new RadioButton(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.gradeToggle.normal"));
		normalGradeRadioButton.setToggleGroup(this.getGradeToggle());
		GridPane.setConstraints(normalGradeRadioButton, 0, 4);
		gridPane.getChildren().add(normalGradeRadioButton);
		RadioButton aLevelGradeRadioButton = new RadioButton(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.gradeToggle.aLevel"));
		aLevelGradeRadioButton.setToggleGroup(this.getGradeToggle());
		GridPane.setConstraints(aLevelGradeRadioButton, 1, 4);
		gridPane.getChildren().add(aLevelGradeRadioButton);
		this.getGradeToggle().selectToggle(normalGradeRadioButton);

		// Checkbox for an 1+
		this.setCheckBoxOnePlus(new CheckBox(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.checkbox.onePlusAvailable")));
		GridPane.setConstraints(this.getCheckBoxOnePlus(), 0, 5);
		gridPane.getChildren().add(this.getCheckBoxOnePlus());

		// Defining the Submit button
		Button calculateButton = new Button(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.button.calculate"));
		GridPane.setConstraints(calculateButton, 1, 6);
		this.defineActionHandling(calculateButton);
		gridPane.getChildren().add(calculateButton);
		
		// Add Textarea for the result
		Text resultTextAreaText = new Text(
				I18nConfiguration.getInstance().getStrings().getString("gui.grade.text.resultTextArea"));
		GridPane.setConstraints(resultTextAreaText, 0, 7);
		gridPane.getChildren().add(resultTextAreaText);
		this.setTextArea(new TextArea());
		this.getTextArea().setEditable(false);
		GridPane.setConstraints(this.getTextArea(), 0, 9, 2, 1);
		gridPane.getChildren().add(this.getTextArea());
		

		return gridPane;
	}

	/**
	 * This method defined the action of the calculation button.
	 * 
	 * @param calculateButton The Calculation button which get the action handling.
	 */
	private void defineActionHandling(Button calculateButton) {
		// Setting an action for the Submit button
		calculateButton.setOnAction(new EventHandler<ActionEvent>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			@Override
			public void handle(ActionEvent e) {
				if (GradeTab.this.readMaxPoint() >= 15) {
					GradeCalculator gradeCalculator = new GradeCalculator();
					List<Grade> grades = gradeCalculator.calculateGrades(
							GradeTab.DEFAULT_PERCENTAGE_PER_GRADE, GradeTab.this.readMaxPoint(), 
							GradeTab.this.getCheckBoxOnePlus().isSelected());

					// Print output as latex code
					GradesLatexWriter gradeLatexWriter = new GradesLatexWriter();
					GradeTab.this.getTextArea().setText(
							gradeLatexWriter.writeLatexCodeBasedOnGrades(
									grades, 
									GradeTab.this.isALevelModeChosen()));
							
				} else {
					System.out.println("Error: This method makes no sense with less 15.");
				}
			}
		});
	}
	
	private double readMaxPoint() {
		return Double.parseDouble(this.maxPointsField.getText());
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

	/* GETTER / SETTER */
	private TextArea getTextArea() {
		return textArea;
	}

	private CheckBox getCheckBoxOnePlus() {
		return checkBoxOnePlus;
	}

	private void setCheckBoxOnePlus(CheckBox checkBoxOnePlus) {
		this.checkBoxOnePlus = checkBoxOnePlus;
	}

	private ToggleGroup getGradeToggle() {
		return gradeToggle;
	}

	private void setGradeToggle(ToggleGroup gradeToggle) {
		this.gradeToggle = gradeToggle;
	}

	private void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}
}
