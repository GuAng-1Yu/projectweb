package com.iscc.propertymanagent.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class HousePageSlice<T> implements Serializable {
    private int totalRecord;
    private int totalPage;
    private int currentPage;
    private int pageNum;
    private List<T> data;
    private int start;
    private int end;



    public HousePageSlice(int currentPage, int pageNum, List<T> data) {
        this.currentPage = currentPage;
        this.pageNum = pageNum;
        this.totalRecord = data.size();
        this.totalPage = this.totalRecord % this.pageNum == 0 ? this.totalRecord / this.pageNum : (this.totalRecord / this.pageNum) + 1;
        this.start = (this.currentPage - 1) * this.pageNum;
        this.end = this.currentPage * this.pageNum;
    }}
