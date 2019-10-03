/**
 * 
 */
package de.dkatzberg.schooltools.common.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class handles the I18n Information
 * 
 * @author Daniel Katzberg
 *
 */
public class I18nConfiguration {

	/* Includes all strings, depending by a language. */
	private ResourceBundle strings;

	private volatile static I18nConfiguration i18nConfiguration;

	/**
	 * It is a private constructor, because of the singleton implementation. This
	 * installs the I18n ResourceBundle.
	 * 
	 * @param language A given language like "de" or "en".
	 * @param country  A given country like "DE" or "US".
	 */
	private I18nConfiguration(String language, String country) {
		this.updateI18n(language, country);
	}

	/**
	 * This method changes the set language.
	 * 
	 * @param language A given language like "de" or "en".
	 * @param country  A given country like "DE" or "US".
	 * @return
	 */
	public void updateI18n(String language, String country) {
		this.strings = ResourceBundle.getBundle("MessagesBundle", new Locale(language, country));
	}

	/* GETTER / SETTER */
	public ResourceBundle getStrings() {
		return strings;
	}

	/**
	 * Singleton implementation for the I18n Configuration.
	 * 
	 * @return The singleton of the I18nConfiguration.
	 */
	public static I18nConfiguration getInstance() {
		if (i18nConfiguration == null) {
			synchronized (I18nConfiguration.class) {
				if (i18nConfiguration == null) {
					i18nConfiguration = new I18nConfiguration("de", "DE");
				}
			}
		}

		return i18nConfiguration;
	}
}
