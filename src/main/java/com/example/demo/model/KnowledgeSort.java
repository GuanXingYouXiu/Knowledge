package com.example.demo.model;

public class KnowledgeSort {
    private Integer id;

    private String sort;

    public KnowledgeSort(Integer id, String sort) {
        this.id = id;
        this.sort = sort;
    }

    public KnowledgeSort() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }
}