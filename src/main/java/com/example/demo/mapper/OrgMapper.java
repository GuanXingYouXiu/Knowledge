package com.example.demo.mapper;

import com.example.demo.controller.SqlProviderController;
import com.example.demo.model.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrgMapper {
    int deleteByPrimaryKey(String id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);

    //    @SelectProvider(type = SqlProviderController.class,method = "queryOrgAll")
    List<Org> queryOrgAll();
}