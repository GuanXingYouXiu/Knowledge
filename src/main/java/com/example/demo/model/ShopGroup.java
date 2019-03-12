package com.example.demo.model;

public class ShopGroup {
    private Integer id;

    private String groupName;

    private String shopId;

    private Integer flag;

    public ShopGroup(Integer id, String groupName, String shopId, Integer flag) {
        this.id = id;
        this.groupName = groupName;
        this.shopId = shopId;
        this.flag = flag;
    }

    public ShopGroup() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}