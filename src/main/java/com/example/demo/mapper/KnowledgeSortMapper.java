package com.example.demo.mapper;

import com.example.demo.controller.SqlProviderController;
import com.example.demo.model.KnowledgeSort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KnowledgeSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KnowledgeSort record);

    int insertSelective(KnowledgeSort record);

    KnowledgeSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KnowledgeSort record);

    int updateByPrimaryKey(KnowledgeSort record);


    @SelectProvider(type = SqlProviderController.class,method = "querySortAll")
    List<KnowledgeSort> querySortAll();
}