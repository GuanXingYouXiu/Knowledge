package com.example.demo.controller;

import com.example.demo.bean.KnowledgeBean;
import com.example.demo.model.Knowledge;
import com.example.demo.servise.impl.FileServiceImpl;
import com.example.demo.servise.impl.KnowledgeServiceImpl;
import com.example.demo.util.PageUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Ids;

import javax.jws.WebParam;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.print.attribute.HashPrintJobAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ClassName: KnowledgeControlle
 * @Description: 知识库的相关操作
 * @author: LZA
 * @date: 2019-03-08
 */
@Controller
@RequestMapping("knowledge")
public class KnowledgeControlle {

    @GetMapping("fileupload")
    public String upload() {
        return "/fileupload";
    }

    @Autowired
    private KnowledgeServiceImpl knowledgeService;


    @GetMapping
    public ModelAndView knowledge(ModelAndView model) {
        Map map = knowledgeService.querySortAndOrg();
        model.addObject("orgs", map.get("org"));
        model.addObject("knownledgeSorts", map.get("sort"));
        model.setViewName("knowledge");
        return model;
    }

    /**
     * 多条件查询（问题模糊查询、关键词模糊查询、商品名称模糊查询、
     * 商家编号模糊查询、部门查询、种类Sort查询、分页查询）
     */
    @RequestMapping("queryKnowledgeAll")
    @ResponseBody
    public PageUtil queryKnowledgeAll(String offset, String limit, KnowledgeBean knowledgeBean) {
        if (knowledgeBean.getDataOrg() != null && knowledgeBean.getDataOrg().equals("部门")) {
            knowledgeBean.setDataOrg("");
        }

        Integer star = 0;
        Integer pagesize = 15;

        if (offset != null || limit != null) {
            star = Integer.valueOf(offset);
            pagesize = Integer.valueOf(limit);
        }
        PageInfo<KnowledgeBean> pageInfo = knowledgeService.queryKnowledgeAll(star, pagesize, knowledgeBean);
        PageUtil pageUtil = new PageUtil((int) pageInfo.getTotal(), pageInfo.getList());
        return pageUtil;
    }


//    /**
//     * 添加知识库资料（上传文件）
//     * 部门（data_org）、商品名称、商家编号、问题分类(sort)、生产厂家、答案、问题、上传的文件（视频、图片、文档）
//     */
//    @PostMapping("addKnowledge")
//    @ResponseBody
//    public void addKnowledge(Knowledge knowledge, HttpServletRequest request) {
//
//    }

    /**
     * 下载知识库图片
     */
    @GetMapping("downloadFile")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, String path) throws UnsupportedEncodingException {
        return knowledgeService.downloadFile(request, response, path);
    }

    /**
     * 下载多个知识库图片（压缩包）
     */
    @GetMapping("downloadFileMore")
    @ResponseBody
    public String downloadFileMore(String Id) {
        return knowledgeService.downloadFileMore(Id);
    }

    /**
     * 批量删除
     */
    @RequestMapping("deleteBatchByIds")
    public ModelAndView deleteBatchByIds(String Ids) {

        if(Ids!=null){
            List<String> ids = new ArrayList<>();
            String[]Stringids=Ids.split(",");
            for (int i = 0; i <Stringids.length ; i++) {
                ids.add(Stringids[i]);
            }
            knowledgeService.deleteBatchByIds(ids);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("massge");
        modelAndView.addObject("massged", "删除成功");
        return modelAndView;
    }

    /**
     * 去修改，根据id去查询数据信息
     */
    @GetMapping("queryKnowledgeById")
    public ModelAndView queryKnowledgeById(String Id) {
        Knowledge knowledge = null;
        if (StringUtil.isEmpty(Id)){
            knowledge = new Knowledge();
        }else{
            knowledge = knowledgeService.queryKnowledgeById(Id);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updata");
        Map map = knowledgeService.querySortAndOrg();
        modelAndView.addObject("orgs", map.get("org"));
        modelAndView.addObject("knownledgeSorts", map.get("sort"));
        modelAndView.addObject("model", knowledge);
        return modelAndView;
    }

    /**
     * 知识库数据修改操作
     */
    @PostMapping(value = "updateKnowledge")
    public ModelAndView updateKnowledge(@RequestParam(value = "id",required = false) String id,
                                @RequestParam(value = "productName",required = false) String productName,
                                @RequestParam(value = "shopNum",required = false) String shopNum,
                                @RequestParam(value = "sort",required = false) Integer sort,
                                @RequestParam(value = "productFactory",required = false) String productFactory,
                                @RequestParam(value = "ask",required = false) String ask,
                                @RequestParam(value = "answer",required = false) String answer,
                                @RequestParam(value = "dataOrg",required = false) String dataOrg,
                                @RequestParam MultipartFile[] imagePath,
                                HttpServletRequest request) throws IOException {
        Knowledge knowledge = new Knowledge();

        knowledge.setId(id);
        knowledge.setProductName(productName);
        knowledge.setShopNum(shopNum);
        knowledge.setSort(sort);
        knowledge.setProductFactory(productFactory);
        knowledge.setAsk(ask);
        knowledge.setAnswer(answer);
        knowledge.setDataOrg(dataOrg);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("massge");

        if (StringUtil.isNotEmpty(id)) {
            knowledgeService.updateKnowledge(knowledge, request, imagePath);
            modelAndView.addObject("massged","更新成功");
        } else {
            knowledgeService.addKnowledge(knowledge, request, imagePath);
            modelAndView.addObject("massged", "添加成功");
        }
        return modelAndView;
    }

}
