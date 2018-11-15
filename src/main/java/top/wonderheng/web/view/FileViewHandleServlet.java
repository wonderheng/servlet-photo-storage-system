package top.wonderheng.web.view;

import top.wonderheng.domain.SmallFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.web.view
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:46
 */
@WebServlet(urlPatterns = {"/view/file"})
@MultipartConfig
public class FileViewHandleServlet extends FileViewBaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String method = Optional.ofNullable(req.getParameter("method")).orElse("");
        Optional<SmallFile> file = fileService.getFileById(id);
        if (file.isPresent()) {
            SmallFile smallFile = file.get();
            resp.setHeader("Content-Length", String.valueOf(smallFile.getSize()));
            resp.setHeader("Connection", "close");
            switch (method) {
                case "delete":
                    //删除
                    fileService.removeFile(id);
                    resp.sendRedirect("/");
                    break;
                case "download":
                    //下载
                    resp.setHeader(
                            "Content-Disposition",
                            "attachment;filename=" + new String(smallFile.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                    resp.setHeader("Content-Type", "application/octet-stream");
                    break;
                default:
                    //显示
                    resp.setHeader("Content-Type", smallFile.getContentType());
                    break;
            }
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(file.get().getContent().getData());
            outputStream.flush();
        } else {
            resp.sendRedirect("/");
        }
    }
}
