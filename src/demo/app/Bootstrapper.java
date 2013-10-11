/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app;

import demo.app.common.ILogger;
import demo.app.constants.Constants;
import demo.app.dependency.DependencyManager;
import demo.app.impl.Log4jLogger;
import demo.app.impl.datalayers.PersonDataLayer;
import demo.app.impl.servicesProviders.PersonServiceProvider;
import demo.app.interfaces.datalayers.IPersonDatalayer;
import demo.app.interfaces.serviceProviders.IPersonServiceProvider;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author thuanpv
 */
public class Bootstrapper {
    private BooleanProperty m_LengthyWorkProperty;
    
    public Bootstrapper() {
        m_LengthyWorkProperty = new SimpleBooleanProperty(false);
    }
    
    /**
     * Registers dependencies that will be used throughout application.
     * @throws ClassNotFoundException If database driver class cannot be found.
     */
    public void registerDependencies()
            throws ClassNotFoundException {
        
        //Inject PersonServiceProvider
        IPersonDatalayer personDataLayer = new PersonDataLayer();
        IPersonServiceProvider personServiceProvider = new PersonServiceProvider(personDataLayer);
        
        DependencyManager.registerInstance(IPersonDatalayer.class, personDataLayer);
        DependencyManager.registerInstance(IPersonServiceProvider.class, personServiceProvider);
        
    }
    
    public void registerLoggers() {
        String configPath = Constants.Paths.LOG_CONFIG;
        ILogger infoLogger = new Log4jLogger(Constants.Loggers.INFO, configPath, true);
        ILogger debugLogger = new Log4jLogger(Constants.Loggers.DEBUG, configPath, true);
        ILogger errorLogger = new Log4jLogger(Constants.Loggers.ERROR, configPath, true);
        DependencyManager.registerBean(Constants.Loggers.INFO, infoLogger);
        DependencyManager.registerBean(Constants.Loggers.DEBUG, debugLogger);
        DependencyManager.registerBean(Constants.Loggers.ERROR, errorLogger);
    }
}
