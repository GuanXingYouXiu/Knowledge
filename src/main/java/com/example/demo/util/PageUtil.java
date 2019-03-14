package com.example.demo.util;

import java.util.List;

public class PageUtil {
    private Integer total;
    private List row;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    private Integer errcode = 0;

    public PageUtil() {
    }

    public PageUtil(Integer total, List row) {
        this.total = total;
        this.row = row;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRow() {
        return row;
    }

    public void setRow(List row) {
        this.row = row;
    }
}
