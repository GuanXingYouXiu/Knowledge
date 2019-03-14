package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 知识库筛选条件的Bean
 * @author: LZA
 * @date: 2019-3-8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeBean {

    //知识库编号
    private String id;
    //问题
    private String ask;
    //部门编号
    private String dataOrg;
    //商家编号
    private String shopNum;
    //商品名称
    private String productName;
    //问题种类
    private List<Integer> sortList;

    private String sortName;
    //答案
    private String answer;
    //关键词
    private String keyWord;

    private String productFactory;

}
