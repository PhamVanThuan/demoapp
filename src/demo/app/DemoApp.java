/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app;

import demo.app.common.ILogger;
import demo.app.constants.Constants;
import demo.app.dependency.DependencyManager;
import demo.app.util.ApplicationContext;
import demo.app.util.PubSub;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author thuanpv
 */
public class DemoApp extends Application {

    private Stage m_Stage;
    private ApplicationContext m_Context;
    private ILogger m_DebugLogger;
    private ILogger m_ErrorLogger;
    private Bootstrapper m_Bootstrapper;
    private ScreenManager m_ScreenManager;

    public DemoApp() {
        m_Bootstrapper = new Bootstrapper();
        m_Bootstrapper.registerLoggers();
        m_DebugLogger = (ILogger) DependencyManager.resolveBean(Constants.Loggers.DEBUG);
        m_ErrorLogger = (ILogger) DependencyManager.resolveBean(Constants.Loggers.ERROR);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            PubSub.subscribe(this);
            m_Stage = primaryStage;
            m_Bootstrapper.registerDependencies();
            m_ScreenManager = new ScreenManager(m_Stage);
            m_Context = ApplicationContext.instance();
            m_Stage.show();

            // Must call sizeToScene() after show() so that main window with setResizable(false)
            // won't have extra height and width than expected.
            m_Stage.sizeToScene();

        } catch (Exception ex) {
            m_ErrorLogger.error(null, ex);
            m_DebugLogger.error(null, ex);
        }
    }
    
    private void showHomeScreen() {
        
    }
    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
