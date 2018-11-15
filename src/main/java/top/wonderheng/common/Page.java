package top.wonderheng.common;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: top.wonderheng.common
 * @Author: WonderHeng
 * @CreateTime: 2018-11-10 23:40
 */
public class Page {
    private final Integer pageIndex;

    private final Integer pageSize;

    public Page(Integer pageIndex, Integer pageSize) {
        if (pageIndex <= 0) {
            throw new IllegalArgumentException("pageIndex must be >0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must be >0");
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getSkip() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }
}
