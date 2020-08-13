package com.iscc.propertymanagent.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class Pager<T> implements Serializable {
    private int totalRecord;
    private int totalPage;
    private int currPage;
    private int pageNum;
    private List<T> data;
    private int start;
    private int end;

    public Pager(int currPage, int pageNum, List<T> data) {
        this.currPage = currPage;
        this.pageNum = pageNum;
        //总记录数
        this.totalRecord = data.size();
        this.totalPage = this.totalRecord%this.pageNum ==0 ? this.totalRecord / this.pageNum: (this.totalRecord/this.pageNum)+1;

        this.start = (this.currPage-1)*this.pageNum;
        this.end = this.currPage* this.pageNum;

        this.data = data.subList(start,end);
    }
}
