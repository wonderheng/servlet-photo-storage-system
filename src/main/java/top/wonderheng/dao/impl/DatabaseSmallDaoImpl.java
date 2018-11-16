package top.wonderheng.dao.impl;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import top.wonderheng.common.Page;
import top.wonderheng.dao.SmallFileDao;
import top.wonderheng.domain.SmallFile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.dao.impl
 * @Author: WonderHeng
 * @CreateTime: 2018-11-14 12:16
 */
@Repository
public class DatabaseSmallDaoImpl implements SmallFileDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(SmallFile smallFile) {
        String sql = "insert into db_photo (id,name,content_type,size,upload_date,md5,content) values (?,?,?,?,?,?,?)";
            int a = jdbcTemplate.update(sql,
                    smallFile.getId(),
                    smallFile.getName(),
                    smallFile.getContentType(),
                    smallFile.getSize(),
                    smallFile.getUploadDate(),
                    smallFile.getMd5(),
                    smallFile.getContent().getData());
    }

    @Override
    public void delete(String id) {
        String sql = "delete from db_photo where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public SmallFile find(String id) {
        String sql = "select name,content_type,size,upload_date,md5,content from db_photo where id = ?";
        SmallFile smallFile = this.jdbcTemplate.query(sql, new RowMapper<SmallFile>() {
            @Override
            public SmallFile mapRow(ResultSet resultSet, int i) throws SQLException {
                SmallFile sf = new SmallFile();
                sf.setName(resultSet.getString("name"));
                sf.setContentType(resultSet.getString("content_type"));
                sf.setSize(resultSet.getLong("size"));
                sf.setUploadDate(resultSet.getTimestamp("upload_date").toLocalDateTime());
                sf.setMd5(resultSet.getString("md5"));
                sf.setContent(new Binary(resultSet.getBytes("content")));
                return sf;
            }
        }, id).get(0);
        return smallFile;
    }

    @Override
    public List<SmallFile> query(Page page) {
        //0 ~ length-1
        //pageIndex 1
        // (pageIndex-1)*pageSize   ~  pageIndex*pageSize
        //[0 , pageSize)
        int pageIndex = page.getPageIndex()-1;
        int pageSize = page.getPageSize();

        String sql = "select id, name, content_type, size, upload_date, md5, content from db_photo limit ? offset ?;";
        List<SmallFile> list = this.jdbcTemplate.query(sql, new RowMapper<SmallFile>() {
            @Override
            public SmallFile mapRow(ResultSet resultSet, int i) throws SQLException {
                SmallFile sf = new SmallFile();
                sf.setId(resultSet.getString("id"));
                sf.setName(resultSet.getString("name"));
                sf.setContentType(resultSet.getString("content_type"));
                sf.setSize(resultSet.getLong("size"));
                sf.setUploadDate(resultSet.getTimestamp("upload_date").toLocalDateTime());
                sf.setMd5(resultSet.getString("md5"));
                sf.setContent(new Binary(resultSet.getBytes("content")));
                return sf;
            }
        }, pageSize, pageIndex);
        return list;
    }
}