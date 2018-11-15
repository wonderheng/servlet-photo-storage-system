package top.wonderheng.web.view;

import org.apache.commons.io.IOUtils;
import org.bson.types.Binary;
import top.wonderheng.domain.SmallFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.view
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:48
 */
@WebServlet(urlPatterns = {"/view/upload"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 3) //2M
public class FileViewUploadServlet extends FileViewBaseServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part part = req.getPart("filename");
            SmallFile smallFile = new SmallFile();
            smallFile.setName(part.getSubmittedFileName());
            smallFile.setContentType(part.getContentType());
            try (InputStream is = part.getInputStream()) {
                smallFile.setContent(new Binary(IOUtils.toByteArray(is)));
            }
            smallFile.setSize(smallFile.getContent().length());
            fileService.saveFile(smallFile);
        } catch (Exception ignored) {
        }
        resp.sendRedirect("/");
    }
}