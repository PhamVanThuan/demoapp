package demo.app;

import com.google.common.eventbus.Subscribe;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import demo.app.common.ModuleBase;
import demo.app.constants.Constants;
import demo.app.events.SceneRequestingEvent;
import demo.app.events.WindowClosingEvent;
import demo.app.ui.hello.HelloController;
import demo.app.ui.HomeController;
import demo.app.util.ApplicationContext;
import demo.app.util.LanguageHelper;
import demo.app.util.PubSub;


/**
 * Manages main screens in applications.
 */
public class ScreenManager {
    
    private Stage m_Stage;
    private String[] m_CssFiles;
    private ApplicationContext m_AppContext;
    
    public ScreenManager(Stage stage) throws MalformedURLException {
        m_Stage = stage;
        m_AppContext = ApplicationContext.instance();
        resolveDependencies();
        PubSub.subscribe(this);
        loadCss();
    }
        
    public void loadHomeScene() {
        m_Stage.setResizable(false);
        HomeController homeController = new HomeController();
        //LoginModule loginUI = new LoginModule(m_UserServiceProvider, m_SettingsServiceProvider);
        String title = LanguageHelper.getString(Constants.Language.HOME);
        replaceSceneContent(homeController);
        m_Stage.setTitle(m_AppContext.appendApplicationTitle(title));
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
                loadHelloScene();
                break;
        }
    }
    
    private void resolveDependencies() {
       
    }
    
    private <T extends ModuleBase> void replaceSceneContent(ModuleBase viewWrapper) {
        Parent view = viewWrapper.getView();
        applyStylesheets(view);
        Scene scene = new Scene(view);
        m_Stage.setScene(scene);
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
