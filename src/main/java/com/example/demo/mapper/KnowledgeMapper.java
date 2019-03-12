package com.example.demo.mapper;

import com.example.demo.bean.KnowledgeBean;
import com.example.demo.controller.SqlProviderController;
import com.example.demo.model.Knowledge;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KnowledgeMapper {
    int insert(Knowledge record);

    /** 添加知识库资料*/
    int insertSelective(Knowledge record);

    /** 测试一下，使用注解做条件筛选*/
    @SelectProvider(type = SqlProviderController.class,method = "queryKnowledgeBySort")
    List<Knowledge> queryKnowledgeBySort(Integer sortId);

    /** 条件筛选知识库*/
    List<KnowledgeBean> queryKnowledgeAll(KnowledgeBean knowledgeBean);

    /** 根据ID编号查询图片路径*/
    @SelectProvider(type = SqlProviderController.class,method = "queryImgPathById")
    String queryImgPathById(String Id);

    /** 批量删除*/
    @DeleteProvider(type = SqlProviderController.class,method = "deleteBatchByIds")
    void deleteBatchByIds(List<String> Ids);

    /** 根据Id 查询知识库数据信息*/
    @SelectProvider(type = SqlProviderController.class,method = "queryKnowledgeById")
    Knowledge queryKnowledgeById(String Id);

    /** 根据Id修改知识库数据*/
    void updateKnowledge(Knowledge knowledge);
}