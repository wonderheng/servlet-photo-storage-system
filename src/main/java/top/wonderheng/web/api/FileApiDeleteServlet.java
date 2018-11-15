package top.wonderheng.web.api;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.api
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:43
 */
@WebServlet(urlPatterns = {"/api/delete"})
@MultipartConfig
public class FileApiDeleteServlet extends FileApiBaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            fileService.removeFile(id);
            this.sendData(resp, true, "");
        } catch (Exception ignored) {
            this.sendData(resp, false, "Delete failed");
        }
    }
}
