package top.wonderheng.dao;

import top.wonderheng.common.Page;
import top.wonderheng.domain.SmallFile;

import java.util.List;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.dao
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:54
 */
public interface SmallFileDao {
    void save(SmallFile smallFile);

    void delete(String id);

    SmallFile find(String id);

    List<SmallFile> query(Page page);
}
