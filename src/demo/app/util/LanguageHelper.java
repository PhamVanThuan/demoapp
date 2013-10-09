package demo.app.util;

import demo.app.constants.Constants;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Provides methods to get internationalized message strings.
 */
public class LanguageHelper {

    private static ResourceBundle s_LanguageBundle;
    private static String s_Locale;

    static {
        String resourceFile = ConfigHelper.getString(Constants.AppConfig.LANGUAGE_RESOURCE_NAME);
        String resourcePath = ConfigHelper.getString(Constants.AppConfig.LANGUAGE_RESOURCE_PATH);
        s_Locale = ConfigHelper.getString(Constants.AppConfig.LOCALE);
        
        try {
            File file = new File(resourcePath);  
            URL[] urls = {file.toURI().toURL()};  
            ClassLoader loader = new URLClassLoader(urls);            
            s_LanguageBundle
                = ResourceBundle.getBundle(resourceFile, new Locale(s_Locale), loader);
        
        } catch (MissingResourceException | MalformedURLException mrex) {
            String error = "Could not load language resource file at: \"%s\" for locale \"%s\"";
            throw new RuntimeException(String.format(error, resourcePath + resourceFile, s_Locale),
                mrex);
        }
    }

    /**
     * Gets language bundle used for internationalization.
     */
    public static ResourceBundle getLanguageBundle() {
        return s_LanguageBundle;
    }

    /**
     * Gets locale from global configurations.
     */
    public static String getLocale() {
        return s_Locale;
    }

    /**
     * Gets a localized string using current locale.
     */
    public static String getString(String key) {
        return s_LanguageBundle.getString(key);
    }

}
