package com.vn.osp.notarialservices.common.util;

import java.util.ArrayList;
import java.util.List;

public class PagingResult {
    private List<?> items = new ArrayList();
    private long rowCount = 0;
    private int numberPerPage = 20;
    private int pageNumber = 1;

    public PagingResult(){};
    public PagingResult(List<?> items, long rowCount) {
        this.items = items;
        this.rowCount = rowCount;
    }

    public List<Integer> getPageList() {
        List<Integer> pages = new ArrayList<Integer>();

        int from = pageNumber  - 3;
        int to = pageNumber + 5;
        if (from < 0) {
            to -= from;
            from = 1;
        }

        if (from < 1) {
            from = 1;
        }


        if (to > getPageCount()) {
            to = getPageCount();
        }

        for (int i=from; i<=to; i++ ) {
            pages.add(i);
        }

        return pages;
    }

    public int getPageCount() {
        return (int) (Math.ceil( (double) rowCount / numberPerPage));
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public int getNumberPerPage() {
        return numberPerPage;
    }

    public void setNumberPerPage(int numberPerPage) {
        this.numberPerPage = numberPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
