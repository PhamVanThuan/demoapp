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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author thuanpv
 */
public class HomeController extends ModuleBase {

    /*
     * FXML Element
     */
    @FXML private Button btnSayHello;
    @FXML private TextField txtName;
    @FXML private Label lbDisplayName;
    
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
    
    // <editor-fold  desc="GUI event handlers" defaultstate="collapsed">
    
    @FXML
    private void btnSayHello(ActionEvent event) {
        String name = txtName.getText();
        lbDisplayName.setText(name);
    }
  

    //*/
    
}
