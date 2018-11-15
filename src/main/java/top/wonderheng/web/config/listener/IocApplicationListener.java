package top.wonderheng.web.config.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.config.listener
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:49
 */
@WebListener
public class IocApplicationListener implements ServletContextListener {

    private static ApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("application-context.xml");
        }

    }

    public static <T> T getBean(Class<T> classz) {
        return context.getBean(classz);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context = null;
    }
}
