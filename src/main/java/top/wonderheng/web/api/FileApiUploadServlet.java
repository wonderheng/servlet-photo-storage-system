package top.wonderheng.web.api;

import org.apache.commons.io.IOUtils;
import org.bson.types.Binary;
import top.wonderheng.domain.SmallFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.api
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:43
 */
@WebServlet(urlPatterns = {"/api/upload"})
@MultipartConfig
public class FileApiUploadServlet extends FileApiBaseServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part part = req.getPart("filename");
            SmallFile smallFile = new SmallFile();
            smallFile.setName(part.getSubmittedFileName());
            smallFile.setContentType(part.getContentType());
            try (InputStream is = part.getInputStream()) {
                smallFile.setContent(new Binary(IOUtils.toByteArray(is)));
            }
            smallFile = fileService.saveFile(smallFile);
            //http://localhost:80/view/file?id=xxxx
            this.sendData(resp,
                    String.format("%s://%s:%d/view/file?id=%s",
                            req.getScheme(),
                            req.getLocalAddr(),
                            req.getLocalPort(),
                            smallFile.getId()), "");
        } catch (Exception ignored) {
            this.sendData(resp, "", "Upload filed");
        }
    }
}
