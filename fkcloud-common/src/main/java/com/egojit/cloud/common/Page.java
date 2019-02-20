package com.egojit.cloud.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分页对象
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public class Page<T> {

    /**
     * 每页数量
     */
    private int pageSize = 0;

    /**
     * 当前页
     */
    private int pageIndex = 12;

    /**
     * 总数
     */
    private long totalCount;

    /**
     * 是否需要分页
     */
    private boolean isNeedPage=true;

    /**
     * 数据
     */
    private List<T> list;

    public Page(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Page() {
        this(0, 12);
    }


    /**
     * 构造方法
     *
     * @param request 传递 repage 参数，来记住页码
     */
    public Page(HttpServletRequest request) {
        // 设置页码参数（传递repage参数，来记住页码）
        String pageIndex = request.getParameter("pageIndex");
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNumeric(pageIndex)) {
            this.setPageIndex(Integer.parseInt(pageIndex));
        }
        if (StringUtils.isNumeric(pageSize)) {
            this.setPageSize(Integer.parseInt(pageSize));
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<T> getList() {
        return list;
    }

    public Page<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }


    public boolean isNeedPage() {
        return isNeedPage;
    }

    public void setNeedPage(boolean needPage) {
        isNeedPage = needPage;
    }
}
