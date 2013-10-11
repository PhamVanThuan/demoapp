/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.ui.hello;

import demo.app.common.ILogger;
import demo.app.common.ModuleBase;
import demo.app.constants.Constants;
import demo.app.constants.UINames;
import demo.app.dependency.DependencyManager;
import demo.app.events.SceneRequestingEvent;
import demo.app.util.PubSub;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author thuanpv
 */
public class HelloController extends ModuleBase {

    /*
     * FXML Element
     */
    @FXML private Button btnHello;
    @FXML private Button btnBack;
    @FXML private Label lbHello;
    
    private final static String VIEW_PATH = "Hello.fxml";
    private BorderPane m_BorderPane;
    private ILogger m_ErrorLogger;
    private ILogger m_DebugLogger;
    
    public HelloController(){
        PubSub.subscribe(this);
    }
    
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
    
    private void initComponent(){
        m_BorderPane = new BorderPane();
        loadViewByPath(VIEW_PATH, m_BorderPane);
    }
    
    // <editor-fold  desc="GUI event handlers" defaultstate="collapsed">
    @FXML
    private void btnHello_Action(ActionEvent event) {
       
    }
    
    @FXML
    private void btnBack_Action(ActionEvent event) {
        PubSub.publish(new SceneRequestingEvent(UINames.Scene.HOME));
    }
    
    // </editor-fold>
    
}
