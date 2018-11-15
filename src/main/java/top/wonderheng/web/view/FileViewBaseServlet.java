package top.wonderheng.web.view;

import freemarker.template.Configuration;
import freemarker.template.Template;
import top.wonderheng.service.FileService;
import top.wonderheng.web.config.listener.FreemarkerConfigLoaderListener;
import top.wonderheng.web.config.listener.IocApplicationListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.view
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:46
 */
public class FileViewBaseServlet extends HttpServlet {
    public FileService fileService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.fileService = IocApplicationListener.getBean(FileService.class);
    }

    /**
     * 获取模板
     *
     * @param templateName
     * @param request
     * @param response
     * @return
     */
    protected Template getTemplate(String templateName, HttpServletRequest request, HttpServletResponse response) {
        ServletContext servletContext = request.getServletContext();
        Configuration configuration =
                (Configuration) servletContext.getAttribute(FreemarkerConfigLoaderListener.TEMPLATE_KEY);
        try {
            return configuration.getTemplate(templateName);
        } catch (IOException e) {
            return null;
        }
    }
}
