package de.dkatzberg.schooltools.common.config;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the I18nConfiguration Class
 * @author Daniel Katzberg
 *
 */
public class I18nConfigurationTests {
	
	private I18nConfiguration i18nConfiguration;
	
	@BeforeEach
	public void init() {
		this.i18nConfiguration = I18nConfiguration.getInstance();
	}
	

	@Test
	public void getLanguageElementTest() {
		//Arrange
				
		//Act
		String guiTitleString = this.i18nConfiguration.getStrings().getString("gui.title");
		
		//Assert
		assertArrayEquals("School Tools".toCharArray(), 
				guiTitleString.toCharArray());
	}
	
	@Test
	public void changeLanguage() {
		//Arrange
		
		//Act
		String guiTitleGermanString = this.i18nConfiguration.getStrings().getString("gui.grade.text.percentagePerGrade");
		this.i18nConfiguration.updateI18n("en", "EN");
		String guiTitleEnglishString = this.i18nConfiguration.getStrings().getString("gui.grade.text.percentagePerGrade");
		
		//Assert
		assertArrayEquals("Prozentspanne pro Note".toCharArray(), 
				guiTitleGermanString.toCharArray());
		assertArrayEquals("Percentage area per Grade".toCharArray(), 
				guiTitleEnglishString.toCharArray());
	}
	
}
