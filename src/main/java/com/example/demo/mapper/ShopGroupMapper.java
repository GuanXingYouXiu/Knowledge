package com.example.demo.mapper;

import com.example.demo.model.ShopGroup;

public interface ShopGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopGroup record);

    int insertSelective(ShopGroup record);

    ShopGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopGroup record);

    int updateByPrimaryKey(ShopGroup record);
}