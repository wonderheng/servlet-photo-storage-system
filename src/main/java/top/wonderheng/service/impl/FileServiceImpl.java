package top.wonderheng.service.impl;

import org.bson.types.ObjectId;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wonderheng.common.Page;
import top.wonderheng.dao.SmallFileDao;
import top.wonderheng.domain.SmallFile;
import top.wonderheng.service.FileService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.service.impl
 * @Author: WonderHeng
 * @CreateTime: 2018-11-11 00:12
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private SmallFileDao smallFileDao;

    public FileServiceImpl() { }

    @Override
    public SmallFile saveFile(SmallFile smallFile) {
        //Id生成策略
        //UUID
        //时间戳+自增长的编号
        //开源的一些实现 ObjectId , twitter
        smallFile.setId(new ObjectId().toString());
        smallFile.setUploadDate(LocalDateTime.now());
        smallFile.setMd5(DigestUtils.md5Hex(smallFile.getContent().getData()));
        smallFileDao.save(smallFile);
        return smallFile;
    }

    @Override
    public void removeFile(String id) {
        smallFileDao.delete(id);
    }

    @Override
    public Optional<SmallFile> getFileById(String id) {
        return Optional.ofNullable(smallFileDao.find(id));
    }

    @Override
    public List<SmallFile> listFilesByPage(int pageIndex, int pageSize) {
        return smallFileDao.query(new Page(pageIndex, pageSize));
    }
}