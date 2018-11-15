package top.wonderheng.web.config.listener;

import top.wonderheng.dao.StorageMode;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.config.listener
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:50
 */
@WebListener
public class StorageModeLoaderListener implements ServletContextListener {

    public static StorageMode storageMode = StorageMode.Memory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String mode = servletContext.getInitParameter("storage_mode");
        //通过web.xml配置参数，获取项目存储的方式
        for (StorageMode item : StorageMode.values()) {
            if (item.name().equalsIgnoreCase(mode)) {
                storageMode = item;
                break;
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


    }

}
