package com.example.demo.model;

public class MenuItem {
    private String id;

    private String caption;

    private String fid;

    private String parentId;

    private Integer showOrder;

    public MenuItem(String id, String caption, String fid, String parentId, Integer showOrder) {
        this.id = id;
        this.caption = caption;
        this.fid = fid;
        this.parentId = parentId;
        this.showOrder = showOrder;
    }

    public MenuItem() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption == null ? null : caption.trim();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }
}