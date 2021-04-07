package com.edu.training.models;

public class PaginationRange {

    private int currentPage;
    private int totalPage;
    private int min;
    private int max;

    public PaginationRange() {

    }

    public PaginationRange(int currentPage, int totalPage, int min, int max) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.min = min;
        this.max = max;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
