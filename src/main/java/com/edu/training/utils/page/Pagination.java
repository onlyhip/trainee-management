package com.edu.training.utils.page;

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
}
