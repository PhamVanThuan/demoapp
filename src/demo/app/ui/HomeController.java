/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.ui;

import demo.app.common.ILogger;
import demo.app.common.ModuleBase;
import demo.app.constants.Constants;
import demo.app.dependency.DependencyManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author thuanpv
 */
public class HomeController extends ModuleBase {

    private BorderPane m_BorderPane;
    private ILogger m_ErrorLogger;
    private ILogger m_DebugLogger;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        m_ErrorLogger = (ILogger) DependencyManager.resolveBean(Constants.Loggers.ERROR);
        m_DebugLogger = (ILogger) DependencyManager.resolveBean(Constants.Loggers.DEBUG);
    }    

    @Override
    public Pane getView() {
        if (m_BorderPane == null) {
            initComponent();
        }
        return m_BorderPane;
    }
    
    private void initComponent() {
        m_BorderPane = new BorderPane();
        loadView(Constants.FXML.HOME, m_BorderPane);
    }
    
}
