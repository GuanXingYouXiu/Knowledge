package com.example.demo.controller;

import com.example.demo.model.Knowledge;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SqlProviderController
 * @Description: 注解操作数据库的一些方法
 * @author: LZA
 * @date: 2019-03-08
 */
public class SqlProviderController {

    /**
     * 根据Id查询图片路径.
     */
    public String queryImgPathById(String Id){
        StringBuffer sql = new StringBuffer("select image_path from t_knowledge where 1=1");
        if(Id!= null || Id !=""){
            sql.append(" and Id=#{id}");
        }
        return sql.toString();
    }

    /**
     * 根据Id做批量删除
     */
    public String deleteBatchByIds(Map map){
        List<String> Ids = (List<String>) map.get("list");
        StringBuffer sql = new StringBuffer("delete from t_knowledge where id in (");
        for (int i = 0; i < Ids.size(); i++) {
            sql.append("'").append(Ids.get(i)).append("'");
            if (i < Ids.size() - 1)
                sql.append(",");
        }
        sql.append(")");
        return sql.toString();
    }

    /**
     * 根据Id 查询知识库数据
     */
    public String queryKnowledgeById(String Id){
        StringBuffer sql = new StringBuffer("select id ,ask,answer,data_org as dataOrg,image_path as imagePath,shop_num as shopNum,product_factory as productFactory" +
                ",product_name as productName,video_path as videoPath,doc_path as docPath,sort,group_id as groupId  from t_knowledge where 1=1");
        if(Id!= null || Id !=""){
            sql.append(" and id=#{id,jdbcType=VARCHAR}");
        }
        return sql.toString();
    }

    /**
     * 查询所有Org
     */
    public String queryOrgAll(){
        StringBuffer sql = new StringBuffer("select name,data_org as dataOrg from t_org where parent_id !=' ' ");
        return sql.toString();
    }
    /**
     * 查询所有sort
     */
    public String querySortAll(){
        StringBuffer sql = new StringBuffer("select * from t_knowledge_sort where 1=1");
        return sql.toString();
    }

}
