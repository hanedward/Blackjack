/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package view;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static String BUNDLE_NAME = "view.messages"; //$NON-NLS-1$

	private static ResourceBundle RESOURCE_BUNDLE;
	
	private static String currentLanguage = "English";

	private Messages() {
	}

	public static String getString(String key) {
		try {
			Locale currentLocale = Locale.getDefault();
			
			// *** QUERYING OS FOR LOCALE ***
			if (currentLocale.getCountry() != "US")
				BUNDLE_NAME = "view.messages_ko";
			
//			BUNDLE_NAME = "view.messages_ko";
			
			RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
			
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static void changeLocalization(String language) {
		currentLanguage = language;
		
		if (language.equalsIgnoreCase("English")) {
			BUNDLE_NAME = "view.messages";
		}
		else if (language.equalsIgnoreCase("Korean")) {
			BUNDLE_NAME = "view.messages_ko";
		}
		else if (language.equalsIgnoreCase("Japanese")) {
			BUNDLE_NAME = "view.messages_jp";
		}
	}
	
	public static String getCurrentLanguage() {
		return currentLanguage;
	}
	
	public static String getBundleName() {
		return BUNDLE_NAME;
	}
}
