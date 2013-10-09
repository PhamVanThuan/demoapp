package demo.app.common;

import demo.app.exceptions.RuntimeExceptionWrapper;
import demo.app.util.ConfigHelper;
import demo.app.util.LanguageHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;



/**
 * Base class for all UI modules, which can usually be attached directly
 * to Scene and display fully in a Stage.
 */
public abstract class ModuleBase 
    implements Initializable{
    
    /**
     * @deprecated Should use loadViewByPath
     */
    @Deprecated
    protected void loadView(String viewKey, Parent parent) {
        loadView(viewKey, parent, this);
    }
    
    /**
     * @deprecated Should use loadViewByPath
     */
    @Deprecated
    protected void loadView(String viewKey, Parent parent, Initializable controller) {
        String fxmlPath = ConfigHelper.getString(viewKey);
        loadViewByPath(fxmlPath, parent);
    }
    
    protected void loadViewByPath(String fxmlPath, Parent parent) {
        ResourceBundle languageBundle = LanguageHelper.getLanguageBundle();
        URL fxmlResource = getClass().getResource(fxmlPath);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource, languageBundle);
        fxmlLoader.setRoot(parent);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (Exception ex) {
            throw new RuntimeExceptionWrapper("Could not load FXML file from: " + fxmlPath, ex);
        }
    }
    
    /**
     * Gets view rendered from FXML.
     */
    public abstract Pane getView();
}
