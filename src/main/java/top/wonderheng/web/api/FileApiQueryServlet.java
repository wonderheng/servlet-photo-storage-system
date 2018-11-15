package top.wonderheng.web.api;

import top.wonderheng.domain.SmallFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.api
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:43
 */
@WebServlet(urlPatterns = {"/api/query"})
@MultipartConfig
public class FileApiQueryServlet extends FileApiBaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //http://localhost:80/api/query?pageIndex=xx&pageSize=xx
            String pageIndex = req.getParameter("pageIndex");
            String pageSize = req.getParameter("pageSize");
            if (pageIndex == null || pageIndex.equals("")) {
                pageIndex = "1";
            }
            if (pageSize == null || pageSize.equals("")) {
                pageSize = "20";
            }
            List<SmallFile> smallFileList = fileService.listFilesByPage(
                    Integer.parseInt(pageIndex),
                    Integer.parseInt(pageSize)
            );
            List<Map<String, Object>> data = new ArrayList<>();
            for (SmallFile smallFile : smallFileList) {
                Map<String, Object> item = new HashMap<>();
                item.put("id", smallFile.getId());
                item.put("contentType", smallFile.getContentType());
                item.put("name", smallFile.getName());
                item.put("size", smallFile.getSize());
                item.put("md5", smallFile.getMd5());
                item.put("uploadDate", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(smallFile.getUploadDate()));
                data.add(item);
            }
            this.sendData(resp, data, "");
        } catch (Exception ignored) {
            this.sendData(resp, "", "Query filed");
        }
    }
}
