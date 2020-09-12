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
		
		//TODO Write from this some tests...
		//-----------------------------------------------------
		/*MongoConnector mongoConnector = MongoConnector.getInstance();

		Document documentPart = new Document();
		documentPart.append("SuperDuper", "Megatoll");

		Document document = new Document();
		document.append("_id", "1")
				.append("A Key", 12345)
				.append("Another Key", "<coool>")
				.append("A Part", documentPart);
		
		mongoConnector.getMongoCollection("test", "schooltools").insertOne(document);
		mongoConnector.getMongoCollection("test", "schooltools").deleteOne(Filters.eq("_id", "1"));
		
		*/
		//DROP FOR AFTERALL in the junit test...
//		if (db.collectionExists("myCollection")) {
//		    DBCollection myCollection = db.getCollection("myCollection");
//		    myCollection.drop();
//		}
		//-----------------------------------------------------
		
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
