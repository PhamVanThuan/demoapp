/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.constants;

/**
 *
 * @author thuanpv
 */
public class Constants {
    
    /**
     * Logger class
     */
    public final class Loggers {
        /**
         * Value: "info"
         */
        public final static String INFO = "info";
        
        /**
         * Value: "debug"
         */
        public final static String DEBUG = "debug";
        
        /**
         * Value: "error"
         */
        public final static String ERROR = "error";
    }
    
    
    
    public final class Paths {
    
        /**
         * Path to application config file.<br/>
         * Value: "./config/app.properties"
         */
        //public static final String APP_CONFIG = "/open/dolphin/resources/config/app.properties";
        public static final String APP_CONFIG = "./config/app.properties";
        
        /**
         * Path to logger config file.<br/>
         * Value: "/open/dolphin/resources/config/log4j.properties"
         */
        //public static final String LOG_CONFIG = "/open/dolphin/resources/config/log4j.properties";
        public static final String LOG_CONFIG = "./config/log4j.properties";
    }
    
    public final class AppConfig {
        
        /**
         * Value: "app.preloaderCountDown"
         */
        public final static String APP_PRELOADER_COUNTDOWN = "app.preloaderCountDown";
        
        /**
         * Value: "app.productName"
         */
        public final static String APP_PRODUCT_NAME = "app.productName";

        /**
         * Value: "app.version"
         */
        public final static String APP_VERSION = "app.version";

        /**
         * Value: "areaNetwork.list"
         */
        public final static String AREA_NETWORK_NAMES = "areaNetwork.list";

        /**
         * Value: "app.dir.base"
         */
        public final static String BASE_DIR = "app.dir.base";

        /**
         * Value: "app.charset"
         */
        public final static String APP_CHARSET = "app.charset";
        
        /**
         * Value: "app.languageResourceName"
         */
        public final static String LANGUAGE_RESOURCE_NAME = "app.languageResourceName";

        /**
         * Value: "app.languageResourcePath"
         */
        public final static String LANGUAGE_RESOURCE_PATH = "app.languageResourcePath";

        /**
         * Value: "app.locale"
         */
        public final static String LOCALE = "app.locale";

        /**
         * Value: "app.dir.lib"
         */
        public final static String LIB_DIR = "app.dir.lib";

        /**
         * Value: "app.dir.log"
         */
        public final static String LOG_DIR = "app.dir.log";

        /**
         * Value: "app.dir.plugins"
         */
        public final static String PLUGINS_DIR = "app.dir.plugins";

        /**
         * Value: "registration.infoFile"
         */
        public final static String REGISTRATION_INFO_FILE = "registration.infoFile";

        /**
         * Value: "app.dir.setting"
         */
        public final static String SETTING_DIR = "app.dir.setting";

        /**
         * Value: "app.dir.security"
         */
        public final static String SECURITY_DIR = "app.dir.security";

        /**
         * Value: "app.dir.schema"
         */
        public final static String SCHEMA_DIR = "app.dir.schema";

        /**
         * Value: "app.version"
         */
        public final static String VERSION = "app.version";
    }

    public final class Language {
        /**
         * Value: "HOME"
         */
        public final static String HOME = "HOME";

    }
    
    /**
     * Key names in application config file to look up .fxml files for views.
     */
    public final class FXML {
        /**
         * Value: "ui.home"
         */
        public final static String HOME = "ui.home";

    }
    
}
