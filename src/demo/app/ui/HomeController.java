/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.ui;

import demo.app.common.ILogger;
import demo.app.common.ModuleBase;
import demo.app.constants.Constants;
import demo.app.constants.UINames;
import demo.app.dependency.DependencyManager;
import demo.app.events.SceneRequestingEvent;
import demo.app.interfaces.serviceProviders.IPersonServiceProvider;
import demo.app.models.PersonModel;
import demo.app.util.PubSub;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private Button btnNew;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<PersonModel> tblViewPerson;
    @FXML
    private TableColumn<PersonModel, String> firstNameColumn;
    @FXML
    private TableColumn<PersonModel, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    
    private ObservableList<PersonModel> personData = FXCollections.observableArrayList();
    
    private final static String VIEW_PATH = "Home.fxml";
    private BorderPane m_BorderPane;
    private ILogger m_ErrorLogger;
    private ILogger m_DebugLogger;
    private IPersonServiceProvider m_PersonServiceProvider;

    /*
     * Constructor
     */
    public HomeController(IPersonServiceProvider personServicerProvider) {
        m_PersonServiceProvider = personServicerProvider;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("lastName"));
        tblViewPerson.setItems(personData);
        
        //Action Change
        tblViewPerson.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonModel>() {
            @Override
            public void changed(ObservableValue<? extends PersonModel> ov, PersonModel t, PersonModel t1) {
               showPersonDetails(t1);
            }
        });
        
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
        
        List<PersonModel> personList =  m_PersonServiceProvider.getAllUser();
        for(int i=0; i< personList.size(); i++)
        {
            personData.add(personList.get(i));
        }
        
        m_BorderPane = new BorderPane();
        loadViewByPath(VIEW_PATH, m_BorderPane);
    }
    
    private void showPersonDetails(PersonModel personModel){
        firstNameLabel.setText(personModel.getFirstName());
        lastNameLabel.setText(personModel.getLastName());
        streetLabel.setText(personModel.getStreet());
        postalCodeLabel.setText(Integer.toString(personModel.getPostalCode()));
        cityLabel.setText(personModel.getCity());
        
    }

    // <editor-fold  desc="GUI event handlers" defaultstate="collapsed">
    @FXML
    private void btnNew_Action(ActionEvent event) {
        PubSub.publish(new SceneRequestingEvent(UINames.Scene.EDIT));
    }

    @FXML
    private void btnEdit_Action(ActionEvent event) {
    }

    @FXML
    private void btnDelete_Action(ActionEvent event) {
    }
    //*/
}
