/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.ui;

import demo.app.common.ILogger;
import demo.app.common.ModuleBase;
import demo.app.constants.Constants;
import demo.app.dependency.DependencyManager;
import demo.app.interfaces.serviceProviders.IPersonServiceProvider;
import demo.app.models.PersonModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author thuanpv
 */
public class EditController extends ModuleBase {

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;
    private PersonModel person;
    
    private BorderPane m_BorderPane;
    private ILogger m_ErrorLogger;
    private ILogger m_DebugLogger;
    private final static String VIEW_PATH = "Edit.fxml";
    private IPersonServiceProvider m_PersonServiceProvider;
    /*
     * Constructor
     */
    public EditController(IPersonServiceProvider personServicerProvider) {
        m_PersonServiceProvider = personServicerProvider;
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

    private void initComponent() {
        m_BorderPane = new BorderPane();
        loadViewByPath(VIEW_PATH, m_BorderPane);
    }

    @FXML
    private void btnOk_Action(ActionEvent event) {
        
        person = new PersonModel();
        person.setFirstName(firstNameField.getText());
        person.setLastName(lastNameField.getText());
        person.setStreet(streetField.getText());
        person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
        person.setCity(cityField.getText());
        
        m_PersonServiceProvider.addPerson(person);
        
    }

    @FXML
    private void btnCancel_Action(ActionEvent event) {
    }
}
