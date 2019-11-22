package de.dkatzberg.schooltools.common.gui;

import de.dkatzberg.schooltools.common.config.I18nConfiguration;
import de.dkatzberg.schooltools.common.gui.tabs.GradeTab;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * This class represent the base common GUI. This class will be first initialize
 * before all other GUI classes.
 * 
 * @author Daniel Katzberg
 *
 */
//TODO Add an Tab for Options...
//TODO Add Menubar for closing the application
public class CommonGuiBase {
	
	/**
	 * This method builds the graphical user interface based on JavaFX 8.
	 * 
	 * @param stage The stage is the base layer of the JavaFX GUI.
	 */
	public void build(Stage stage) {

		// Set Title
		stage.setTitle(I18nConfiguration.getInstance().getStrings().getString("gui.title"));

		//Create Tab for Grades
        Tab tabGrade = new Tab(I18nConfiguration.getInstance().
        		getStrings().getString("gui.tab.grade.title"));
        tabGrade.setContent(new GradeTab().createTab());

		// Create Tab Pane		
		TabPane tabPane = new TabPane();
        tabPane.getTabs().add(tabGrade);
		
		//(Pane, X-Size, Y-Size)
		stage.setScene(new Scene(tabPane, 480, 380));
		stage.setX(0);
		stage.setY(0);
		stage.show();
	}
}
