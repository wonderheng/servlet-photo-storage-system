package top.wonderheng.dao.impl;

import org.springframework.stereotype.Repository;
import top.wonderheng.common.Page;
import top.wonderheng.dao.SmallFileDao;
import top.wonderheng.domain.SmallFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.dao.impl
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:55
 */
//@Repository
public class InMemorySmallDaoImpl implements SmallFileDao {
    private static final ConcurrentMap<String, SmallFile> smallFileConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public void save(SmallFile smallFile) {
        smallFileConcurrentMap.put(smallFile.getId(), smallFile);
    }

    @Override
    public void delete(String id) {
        smallFileConcurrentMap.remove(id);
    }

    @Override
    public SmallFile find(String id) {
        return smallFileConcurrentMap.get(id);
    }

    @Override
    public List<SmallFile> query(Page page) {
        // pageIndex pageSize
        List<String> ids = new ArrayList<>();
        ids.addAll(smallFileConcurrentMap.keySet());

        //0 ~ length-1
        //pageIndex 1
        // (pageIndex-1)*pageSize   ~  pageIndex*pageSize
        //[0 , pageSize)
        int pageIndex = page.getPageIndex();
        int pageSize = page.getPageSize();

        if (pageIndex <= 0) {
            throw new IllegalArgumentException("pageIndex must be > 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must be >0");
        }

        //[, )
        int fromIndex = (pageIndex - 1) * pageSize;
        int toIndex = pageIndex * pageSize;
        int size = ids.size();
        fromIndex = fromIndex > size ? size : fromIndex;
        toIndex = toIndex > size ? size : toIndex;

        List<String> keys = ids.subList(fromIndex, toIndex);

        List<SmallFile> smallFiles = new ArrayList<>();
        for (String key : keys) {
            smallFiles.add(smallFileConcurrentMap.get(key));
        }
        return smallFiles;
    }
}
