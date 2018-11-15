package top.wonderheng.service;

import top.wonderheng.domain.SmallFile;

import java.util.List;
import java.util.Optional;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.service.impl
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:52
 */
public interface FileService {

    /**
     * 保存文件
     *
     * @param smallFile
     * @return
     */
    SmallFile saveFile(SmallFile smallFile);

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    void removeFile(String id);

    /**
     * 根据id获取文件
     *
     * @param id
     * @return
     */
    Optional<SmallFile> getFileById(String id);

    /**
     * 分页查询，按上传时间降序
     *
     * @param pageIndex 页的下标 从1开始
     * @param pageSize  页的大小 必须大于0
     * @return
     */
    List<SmallFile> listFilesByPage(int pageIndex, int pageSize);
}
