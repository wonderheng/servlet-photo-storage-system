package top.wonderheng.web.view;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import top.wonderheng.domain.SmallFile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.view
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:47
 */
@WebServlet(urlPatterns = {"/view/index", "/", ""})
public class FileViewIndexServlet extends FileViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Template template = getTemplate("index.ftl", req, resp);
        List<SmallFile> smallFileList = fileService.listFilesByPage(1, 20);

        smallFileList.stream().map(smallFile -> {
            Map<String, Object> item = new HashMap<>();
            item.put("id", smallFile.getId());
            item.put("contentType", smallFile.getContentType());
            item.put("name", smallFile.getName());
            item.put("size", smallFile.getSize());
            item.put("md5", smallFile.getMd5());
            item.put("uploadDate", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(smallFile.getUploadDate()));
            return item;
        });
        Map<String, Object> data = new HashMap<>();
        data.put("files", smallFileList);
        try {
            template.process(data, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
