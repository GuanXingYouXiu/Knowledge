package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Org {
    private String id;

    private String fullName;

    private String name;

    private String orgCode;

    private String dataOrg;

    private List<Org> child = null;

    public List<Org> getChild() {
        return child;
    }

    public void setChild(List<Org> child) {
        this.child = child;
    }

    public Org(String id, String fullName, String name, String orgCode, String parentId, String dataOrg) {
        this.id = id;
        this.fullName = fullName;
        this.name = name;
        this.orgCode = orgCode;
        this.dataOrg = dataOrg;
    }

    public Org() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getDataOrg() {
        return dataOrg;
    }

    public void setDataOrg(String dataOrg) {
        this.dataOrg = dataOrg == null ? null : dataOrg.trim();
    }
}