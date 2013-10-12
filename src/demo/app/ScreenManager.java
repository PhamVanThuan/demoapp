package demo.app;

import com.google.common.eventbus.Subscribe;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import demo.app.common.ModuleBase;
import demo.app.common.UIHelper;
import demo.app.constants.Constants;
import demo.app.dependency.DependencyManager;
import demo.app.events.SceneRequestingEvent;
import demo.app.events.WindowClosingEvent;
import demo.app.interfaces.serviceProviders.IPersonServiceProvider;
import demo.app.ui.EditController;
import demo.app.ui.hello.HelloController;
import demo.app.ui.HomeController;
import demo.app.util.ApplicationContext;
import demo.app.util.LanguageHelper;
import demo.app.util.PubSub;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;


/**
 * Manages main screens in applications.
 */
public class ScreenManager {
    
    private Stage m_Stage;
    private String[] m_CssFiles;
    private ApplicationContext m_AppContext;
    private IPersonServiceProvider m_PersonServiceProvider;
    
    public ScreenManager(Stage stage) throws MalformedURLException {
        m_Stage = stage;
        m_AppContext = ApplicationContext.instance();
        resolveDependencies();
        PubSub.subscribe(this);
        loadCss();
    }
        
    public void loadHomeScene() {
        m_Stage.setResizable(false);
        HomeController loginUI = new HomeController(m_PersonServiceProvider);
        String title = LanguageHelper.getString(Constants.Language.HOME);
        replaceSceneContent(loginUI);
        m_Stage.setTitle(m_AppContext.appendApplicationTitle(title));
    }
    
    public void showHelloDialog() {
        HelloController helloController = new HelloController();
        Stage settings = showSubStage(Constants.Language.HELLO, helloController.getView(), false);
        settings.initModality(Modality.APPLICATION_MODAL);
        settings.show();
    }
    public void showEditDialog(){
        EditController editController = new EditController(m_PersonServiceProvider);
        Stage edit = showSubStage(Constants.Language.HELLO, editController.getView(), false);
        edit.initModality(Modality.APPLICATION_MODAL);
        edit.show();
    }
    
    public void loadHelloScene() {
        m_Stage.setResizable(true);
        HelloController helloController = new HelloController();
        //LoginModule loginUI = new LoginModule(m_UserServiceProvider, m_SettingsServiceProvider);
        String title = LanguageHelper.getString(Constants.Language.HELLO);
        replaceSceneContent(helloController);
        m_Stage.setTitle(m_AppContext.appendApplicationTitle(title));
    }

    @Subscribe
    public void OnWindowClosingRequested(WindowClosingEvent event) {
        System.err.println("Close Event");
        m_Stage.close();
    }
    
    @Subscribe
    public void OnSceneRequested(SceneRequestingEvent event) {
        switch (event.getRequestedScene()) {
            case HOME:
                loadHomeScene();
                break;
            case HELLO:
                //loadHelloScene();
                showHelloDialog();
            case EDIT:
                showEditDialog();
                break;
        }
    }
    
    private void resolveDependencies() {
       m_PersonServiceProvider = DependencyManager.resolveInstance(IPersonServiceProvider.class);
    }
    
    private <T extends ModuleBase> void replaceSceneContent(ModuleBase viewWrapper) {
        Parent view = viewWrapper.getView();
        applyStylesheets(view);
        Scene scene = new Scene(view);
        m_Stage.setScene(scene);
    }
    
    private Stage showSubStage(String titleKey, Pane viewRoot, boolean resizeable) {
        String title = LanguageHelper.getString(titleKey);
        applyStylesheets(viewRoot);
        return UIHelper.createSubStage(m_Stage, viewRoot, m_AppContext.appendApplicationTitle(title),
            resizeable);
    }

    private void applyStylesheets(Parent node) {
        node.getStylesheets().addAll(m_CssFiles);
    }
    
    private void loadCss() throws MalformedURLException {
        File file = new File("./stylesheets/default.css");
        URL url = file.toURI().toURL();
        String defaultCss = url.toExternalForm();

        file = new File("./stylesheets/crystalOcean.css");
        url = file.toURI().toURL();
        String crystalOcean = url.toExternalForm();

        m_CssFiles = new String[] { defaultCss, crystalOcean };
    }
}
