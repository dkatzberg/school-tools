package de.dkatzberg.schooltools.start;

import de.dkatzberg.schooltools.common.config.I18nConfiguration;
import de.dkatzberg.schooltools.common.gui.CommonGuiBase;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main method, which starts the whole application.
 * 
 * @author Daniel Katzberg
 *
 */
public class StartSchoolTools extends Application {

	/**
	 * Main method, which starts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StartSchoolTools.launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// The Language will be initialized
		I18nConfiguration.getInstance();
		
		// JavaFX GUI will be initialized
		CommonGuiBase commonGuiBase = new CommonGuiBase();
		commonGuiBase.build(stage);
	}

}
