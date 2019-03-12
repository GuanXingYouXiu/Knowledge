package com.example.demo.mapper;

import com.example.demo.model.KnowledgeSort;

public interface KnowledgeSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KnowledgeSort record);

    int insertSelective(KnowledgeSort record);

    KnowledgeSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KnowledgeSort record);

    int updateByPrimaryKey(KnowledgeSort record);
}