package top.wonderheng.web.config.listener;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.nio.charset.StandardCharsets;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.config.listener
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:50
 */
@WebListener
public class FreemarkerConfigLoaderListener implements ServletContextListener {

    public static final String TEMPLATE_KEY = "_template_";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassLoaderForTemplateLoading(FreemarkerConfigLoaderListener.class.getClassLoader(), "/templates");
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        sce.getServletContext().setAttribute(TEMPLATE_KEY, cfg);

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
