package com.example.demo.model;


import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class Knowledge {
    private String id;

    private String ask;

    private String dataOrg;

    private String imagePath;

    private String shopNum;

    private String productName;

    private String videoPath;

    private String docPath;

    private Date buildTime;

    private String productFactory;

    private Integer sort;

    private Integer groupId;

    private String answer;

    public Knowledge(String id, String ask, String dataOrg, String imagePath, String shopNum, String productName, String videoPath, String docPath, Date buildTime, String productFactory, Integer sort, Integer groupId, String answer) {
        this.id = id;
        this.ask = ask;
        this.dataOrg = dataOrg;
        this.imagePath = imagePath;
        this.shopNum = shopNum;
        this.productName = productName;
        this.videoPath = videoPath;
        this.docPath = docPath;
        this.buildTime = buildTime;
        this.productFactory = productFactory;
        this.sort = sort;
        this.groupId = groupId;
        this.answer = answer;
    }

    public Knowledge(String id, String ask, String dataOrg, String imagePath, String shopNum, String productName, String videoPath, String docPath, Date buildTime, String productFactory, Integer sort, String answer) {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask == null ? null : ask.trim();
    }

    public String getDataOrg() {
        return dataOrg;
    }

    public void setDataOrg(String dataOrg) {
        this.dataOrg = dataOrg == null ? null : dataOrg.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum == null ? null : shopNum.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath == null ? null : docPath.trim();
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getProductFactory() {
        return productFactory;
    }

    public void setProductFactory(String productFactory) {
        this.productFactory = productFactory == null ? null : productFactory.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}