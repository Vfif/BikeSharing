package com.epam.project.controller;

import com.epam.project.type.PageChangeType;

public class Router {
    private String page;
    private PageChangeType way;

    public Router(String page, PageChangeType way) {
        this.page = page;
        this.way = way;
    }

    public Router() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public PageChangeType getWay() {
        return way;
    }

    public void setWay(PageChangeType way) {
        this.way = way;
    }
}
