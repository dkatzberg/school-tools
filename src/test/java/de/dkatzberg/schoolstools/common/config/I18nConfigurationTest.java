package de.dkatzberg.schoolstools.common.config;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import de.dkatzberg.schooltools.common.config.I18nConfiguration;

/**
 * Test the I18nConfiguration Class
 * @author Daniel Katzberg
 *
 */
public class I18nConfigurationTest {

	@Test
	public void getLanguageElement() {
		//Arrange
		I18nConfiguration i18nConfiguration = I18nConfiguration.getInstance();
		
		//Act
		String guiTitleString = i18nConfiguration.getStrings().getString("gui.title");
		
		//Assert
		assertArrayEquals("School Tools".toCharArray(), 
				guiTitleString.toCharArray());
	}
	
	@Test
	public void changeLanguage() {
		//Arrange
		I18nConfiguration i18nConfiguration = I18nConfiguration.getInstance();
		
		//Act
		String guiTitleGermanString = i18nConfiguration.getStrings().getString("gui.grade.text.percentagePerGrade");
		i18nConfiguration.updateI18n("en", "EN");
		String guiTitleEnglishString = i18nConfiguration.getStrings().getString("gui.grade.text.percentagePerGrade");
		
		//Assert
		assertArrayEquals("Prozentspanne pro Note".toCharArray(), 
				guiTitleGermanString.toCharArray());
		assertArrayEquals("Percentage area per Grade".toCharArray(), 
				guiTitleEnglishString.toCharArray());
	}
	
}
