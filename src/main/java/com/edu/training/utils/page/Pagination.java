package com.edu.training.utils.page;

import com.edu.training.models.PaginationRange;

import java.util.Collections;
import java.util.List;

public class Pagination {


    /**
     * Returns a view (not a new list) of the sourceList for the
     * Range based on page and pageSize
     *
     * @param sourceList
     * @param page,      page number should start from 1
     * @return Custom error can be given instead of returning emptyList
     */
    public static <T> List<T> getPage(List<T> sourceList, int page) {
        int pageSize = 10;
        if (page <= 0) {
            throw new IllegalArgumentException("Can not pagination !");
        }
        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() <= fromIndex) {
            return Collections.emptyList();
        }

        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    /**
     * Returns a view (not a new list) of the sourceList for the
     * Range based on page and pageSize
     *
     * @param sourceList
     * @param page,      page number should start from 1
     * @param pageSize
     * @return Custom error can be given instead of returning emptyList
     */
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("Can not pagination !");
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() <= fromIndex) {
            return Collections.emptyList();
        }


        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    public static PaginationRange paginationByRange(int currentPage, long totalRecord, int limit, int range) {
        PaginationRange p = new PaginationRange();
        p.setCurrentPage(currentPage);
        p.setTotalPage((int) Math.ceil( (double)totalRecord/ (double) limit));
        if (p.getTotalPage() < 0) {
            p.setTotalPage(1);
        }
        if (currentPage < 1) {
            p.setCurrentPage(1);
        }
        if (currentPage > p.getTotalPage()) {
            p.setCurrentPage(p.getTotalPage());
        }
        int middle = (range / 2) + 1;
        if (p.getTotalPage() < range) {
            p.setMin(1);
            p.setMax(p.getTotalPage());
        } else {
            p.setMin(p.getCurrentPage() - middle + 1);
            p.setMax(p.getCurrentPage() + middle - 1);
            if (p.getMin() < 1) {
                p.setMin(1);
                p.setMax(range);
            } else if (p.getMax() > p.getTotalPage()) {
                p.setMax(p.getTotalPage());
                p.setMin(p.getTotalPage() - range + 1);
            }
        }
        return p;
    }


}
