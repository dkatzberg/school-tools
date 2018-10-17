package main.java.de.dkatzberg.schooltools.start;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.de.dkatzberg.schooltools.common.gui.CommonGuiBase;

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
		//The Language will be initialized
		//TODO I18nConfiguration.getInstance();
		
		// JavaFX GUI will be initialized
		CommonGuiBase commonGuiBase = new CommonGuiBase();
		commonGuiBase.build(stage);
	}

}
