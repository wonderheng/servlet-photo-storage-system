package top.wonderheng.web.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import top.wonderheng.service.FileService;
import top.wonderheng.web.config.listener.IocApplicationListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.api
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:42
 */
public class FileApiBaseServlet extends HttpServlet {

    private Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .create();

    public FileService fileService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.fileService = IocApplicationListener.getBean(FileService.class);
    }

    void sendData(HttpServletResponse response, Object data, String message) {
        response.setContentType("application/javascript");
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", data);
        result.put("message", message);
        String content = gson.toJson(result);
        try {
            response.getWriter().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
