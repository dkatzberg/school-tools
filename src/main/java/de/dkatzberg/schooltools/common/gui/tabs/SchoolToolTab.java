package de.dkatzberg.schooltools.common.gui.tabs;

import javafx.scene.layout.GridPane;

/**
 * Interface definition for different tabs.
 * 
 * @author Daniel Katzberg
 *
 */
public interface SchoolToolTab {
	
	
	/**
	 * This method creates a tab for the school tools gui basement.
	 * 
	 * @return GridPane with all fields for the tab declaration.
	 */
	public GridPane createTab();

}
