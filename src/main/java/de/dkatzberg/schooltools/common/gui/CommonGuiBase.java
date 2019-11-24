package de.dkatzberg.schooltools.common.gui;

import de.dkatzberg.schooltools.common.config.I18nConfiguration;
import de.dkatzberg.schooltools.common.gui.tabs.GradeTab;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represent the base common GUI. This class will be first initialize
 * before all other GUI classes.
 * 
 * @author Daniel Katzberg
 *
 */
public class CommonGuiBase {

	/**
	 * This method builds the graphical user interface based on JavaFX 8.
	 * 
	 * @param stage The stage is the base layer of the JavaFX GUI.
	 */
	public void build(Stage stage) {

		// Set Title
		stage.setTitle(I18nConfiguration.getInstance().getStrings().getString("gui.title"));

		VBox boxForScene = new VBox(this.createMenuBar(), createsTabPane());

		// (Pane, X-Size, Y-Size)
		stage.setScene(new Scene(boxForScene, 480, 380));
		stage.setX(0);
		stage.setY(0);
		stage.show();
	}

	/**
	 * Creates the tab pane for the application school tools.
	 * 
	 * @return The TabPane for the application school tools.
	 */
	private TabPane createsTabPane() {
		// Create Tab for Grades
		Tab tabGrade = new Tab(I18nConfiguration.getInstance().getStrings().getString("gui.tab.grade.title"));
		tabGrade.setContent(new GradeTab().createTab());

		// Create Tab Pane
		TabPane tabPane = new TabPane();
		tabPane.getTabs().add(tabGrade);
		
		return tabPane;
	}

	/**
	 * Creates the menubar for the application.
	 * 
	 * @return Menubar of the application school tools.
	 */
	private MenuBar createMenuBar() {

		
		//Create Menu 'File' with given MenuItems		
		MenuItem menuFileExit = new MenuItem(
				I18nConfiguration.getInstance().getStrings().getString("gui.menubar.menu.file.item.exit"));
		menuFileExit.setOnAction(e -> {
			System.exit(0);
		});
		
		//Pack Menu File
		Menu menuFile = new Menu(
				I18nConfiguration.getInstance().getStrings().getString("gui.menubar.menu.file"));	
		menuFile.getItems().add(menuFileExit);
		
		// Create MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menuFile);

		return menuBar;
	}
}
