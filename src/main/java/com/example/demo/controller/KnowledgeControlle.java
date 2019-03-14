package com.example.demo.controller;

import com.example.demo.bean.KnowledgeBean;
import com.example.demo.model.Knowledge;
import com.example.demo.servise.impl.FileServiceImpl;
import com.example.demo.servise.impl.KnowledgeServiceImpl;
import com.example.demo.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: KnowledgeControlle
 * @Description: 知识库的相关操作
 * @author: LZA
 * @date: 2019-03-08
 */
@Controller
@RequestMapping("knowledge")
public class KnowledgeControlle {

    @Autowired
    private KnowledgeServiceImpl knowledgeService;



    @GetMapping
    public ModelAndView knowledge(ModelAndView model){
        Map map=knowledgeService.querySortAndOrg();
        model.addObject ("orgs",map.get("org"));
        model.addObject ("knownledgeSorts", map.get("sort"));
        model.setViewName("knowledge");
        return model;
    }

    /**
     * 多条件查询（问题模糊查询、关键词模糊查询、商品名称模糊查询、
     * 商家编号模糊查询、部门查询、种类Sort查询、分页查询）
     */
    @RequestMapping("queryKnowledgeAll")
    @ResponseBody
    public PageUtil queryKnowledgeAll(String offset, String limit,KnowledgeBean knowledgeBean) {
        if (knowledgeBean.getDataOrg() != null && knowledgeBean.getDataOrg().equals("部门")) {
            knowledgeBean.setDataOrg("");
        }

        Integer star = 0;
        Integer pagesize =15;

        System.out.printf(".........."+offset);
        System.out.printf(".........."+limit);

        if (offset != null || limit != null) {
            star = Integer.valueOf(offset);
            pagesize= Integer.valueOf(limit) ;
        }

        PageInfo<KnowledgeBean> pageInfo = knowledgeService.queryKnowledgeAll(star, pagesize, knowledgeBean);
        PageUtil pageUtil = new PageUtil((int) pageInfo.getTotal(), pageInfo.getList());
        return pageUtil;
    }


    /**
     * 添加知识库资料（上传文件）
     * 部门（data_org）、商品名称、商家编号、问题分类(sort)、生产厂家、答案、问题、上传的文件（视频、图片、文档）
     */
    @PostMapping("addKnowledge")
    @ResponseBody
    public void addKnowledge(Knowledge knowledge,HttpServletRequest request)  {
        knowledgeService.addKnowledge(knowledge,request);
    }

    /**
     * 下载知识库图片
     */
    @GetMapping("downloadFile")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,String path )throws UnsupportedEncodingException {
        return knowledgeService.downloadFile(request,response,path);
    }

    /**
     * 下载多个知识库图片（压缩包）
     */
    @GetMapping("downloadFileMore")
    @ResponseBody
    public String downloadFileMore(String Id){
        return knowledgeService.downloadFileMore(Id);
    }

    /**
     * 批量删除
     */
    @PostMapping("deleteBatchByIds")
    @ResponseBody
    public void deleteBatchByIds(@RequestBody List<String> Ids){
        knowledgeService.deleteBatchByIds(Ids);
    }

    /**
     * 去修改，根据id去查询数据信息
     */
    @GetMapping("queryKnowledgeById")
    @ResponseBody
    public Knowledge queryKnowledgeById( String Id){
        return knowledgeService.queryKnowledgeById(Id);
    }

    /**
     * 知识库数据修改操作
     */
    @RequestMapping(value="updateKnowledge",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public void updateKnowledge(Knowledge knowledge,HttpServletRequest request){
        knowledgeService.updateKnowledge(knowledge,request);
    }

}
