package demo.app.util;

import demo.app.common.ILogger;
import demo.app.constants.Constants;
import demo.app.dependency.DependencyManager;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import javax.swing.ImageIcon;



/**
 * Provides common utilities for all classes in this application.<br/>
 * Copied from client.ClientContextStub
 */
public class ApplicationContext {

    private final String RESOURCE_LOCATION = "/demo/app/resources/";
    private final String TEMPLATE_LOCATION = "/demo/app/resources/templates/";
    private final String IMAGE_LOCATION = "/demo/app/resources/images/";
    private final String SCHEMA_LOCATION = "/demo/app/resources/schema/";
    
    private URLClassLoader m_PluginClassLoader;
    private ILogger m_InfoLogger;
    private LinkedHashMap<String, String> toolProviders;    
    //private UserModel m_LoggedInUser;

    private static ApplicationContext s_Instance;

    /**
     * Get the singleton instance of ApplicationContext.
     */
    public static ApplicationContext instance() {
        if (s_Instance == null) {
            s_Instance = new ApplicationContext();
        }
        return s_Instance;
    }

    private ApplicationContext() {
        m_InfoLogger = (ILogger)DependencyManager.resolveBean(Constants.Loggers.INFO);

        m_InfoLogger.info("Start-up time: " + DateFormat.getDateTimeInstance().format(new Date()));
        m_InfoLogger.info("OS name: " + System.getProperty("os.name"));
        m_InfoLogger.info("Java version: " + System.getProperty("java.version"));


        // TODO: Commented until find out what it is for.
        //m_InfoLogger.info("veleocity.log.file = " + configHelper.getString("app.velocity.logFile"));
        //m_InfoLogger.info("login.config.file = " + configHelper.getString("application.security.login.config"));
        //m_InfoLogger.info("ssl.trsutStore = " + configHelper.getString("application.security.ssl.trustStore"));

        //
        // Plugin Class Loader �𐶐�����
        //
        /*
        StringBuilder sb;
        ArrayList<String> test = new ArrayList<>();
        File pluginDir = new File(getLocation("plugins"));
        File pluginDir = new File("plugins");
        listJars(test, pluginDir);
        ArrayList<URL> list = new ArrayList<URL>();
        for (String path : test) {
            sb = new StringBuilder();
            if (isWin()) {
                sb.append("jar:file:/");
            } else {
                sb.append("jar:file://");
            }
            sb.append(path);
            sb.append("!/");
            URL url = new URL(sb.toString());
            list.add(url);
            m_BootLogger.debug(url);
        }
        URL[] urls = list.toArray(new URL[list.size()]);
        pluginClassLoader = new URLClassLoader(urls);

        //
        // Plugin �����X�g�A�b�v����
        //
        /*
        PluginLister<MainTool> lister = PluginLister.list(MainTool.class, pluginClassLoader);
        toolProviders = lister.getProviders();
        if (toolProviders != null) {
            Iterator<String> iter = toolProviders.keySet().iterator();
            while (iter.hasNext()) {
                String cmd = iter.next();
                String clsName = toolProviders.get(cmd);
                m_BootLogger.debug(cmd + " = " + clsName);
            }
        }

        // Velocity ��������
        sb = new StringBuilder();
        sb.append(getLocation("log"));
        sb.append(File.separator);
        sb.append(getString("application.velocity.log.file"));
        Velocity.setProperty("runtime.log", sb.toString());
        Velocity.init();
        m_BootLogger.info("Velocity �������܂���");

        // �f�t�H���g�� UI �t�H���g��ύX����
        setUIFonts();

        // �}�b�N���j���[�o�[�֑Ή�����
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", String.valueOf(true));
        }

        // login configuration file
        sb = new StringBuilder();
        sb.append(getLocation("security"));
        sb.append(File.separator);
        sb.append(getString("application.security.login.config"));
        String loginConfig = sb.toString();
        System.setProperty("java.security.auth.login.config", loginConfig);
        m_BootLogger.info("���O�C���\���t�@�C����ݒ肵�܂���: " + loginConfig);
        //*/
    }

    public URLClassLoader getPluginClassLoader() {
        return m_PluginClassLoader;
    }

    public LinkedHashMap<String, String> getToolProviders() {
        return toolProviders;
    }

    public URL getResource(String name) {
        if (!name.startsWith("/")) {
            name = RESOURCE_LOCATION + name;
        }
        return this.getClass().getResource(name);
    }

    public URL getImageResource(String name) {
        if (!name.startsWith("/")) {
            name = IMAGE_LOCATION + name;
        }
        return this.getClass().getResource(name);
    }

    public InputStream getResourceAsStream(String name) {
        if (!name.startsWith("/")) {
            name = RESOURCE_LOCATION + name;
        }
        return this.getClass().getResourceAsStream(name);
    }

    public InputStream getTemplateAsStream(String name) {
        if (!name.startsWith("/")) {
            name = TEMPLATE_LOCATION + name;
        }
        return this.getClass().getResourceAsStream(name);
    }

    public ImageIcon getImageIcon(String name) {
        return new ImageIcon(getImageResource(name));
    }

    public ImageIcon getSchemaIcon(String name) {
        if (!name.startsWith("/")) {
            name = SCHEMA_LOCATION + name;
        }
        return new ImageIcon(this.getClass().getResource(name));
    }

    public int getHigherRowHeight() {
        return 20;
    }

    private void listJars(ArrayList list, File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listJars(list, file);
            } else if (file.isFile()) {
                String path = file.getPath();
                if (path.toLowerCase().endsWith(".jar")) {
                    list.add(path);
                }
            }
        }
    }

    public String appendApplicationTitle(String title) {
        return String.format("%s - %s - %s",
            title,
            ConfigHelper.getString(Constants.AppConfig.APP_PRODUCT_NAME),
            ConfigHelper.getString(Constants.AppConfig.APP_VERSION));
    }
    /**
     * Gets current logged-in user.
     */
//    public UserModel getLoggedInUser() {
//        return m_LoggedInUser;
//    }
//
//    /**
//     * Sets current logged-in user.
//     */
//    public void setLoggedInUser(UserModel loggedInUser) throws PropertyVetoException {
//        m_LoggedInUser = loggedInUser;
//    }
}