package com.example.demo.servise.impl;

import com.example.demo.bean.KnowledgeBean;
import com.example.demo.mapper.KnowledgeMapper;
import com.example.demo.mapper.KnowledgeSortMapper;
import com.example.demo.mapper.OrgMapper;
import com.example.demo.model.Knowledge;
import com.example.demo.model.Org;
import com.example.demo.servise.KnowledgeService;
import com.example.demo.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName: KnowledgeServiceImpl
 * @Description: 知识库的相关操作
 * @author: LZA
 * @date: 2019-03-07
 */
@Service
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Value("${File.filePath}")
    private String filePath;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private KnowledgeSortMapper knowledgeSortMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public PageInfo<KnowledgeBean> queryKnowledgeAll(int pageNum, int pageSize, KnowledgeBean knowledgeBean) {
        PageHelper.startPage(pageNum, pageSize);
        List<KnowledgeBean> knowledges = knowledgeMapper.queryKnowledgeAll(knowledgeBean);
        PageInfo<KnowledgeBean> knowledgeBeanAll = new PageInfo<>(knowledges);
        return knowledgeBeanAll;
    }


    @Override
    public void addKnowledge(Knowledge knowledge, HttpServletRequest request, MultipartFile[] imagePath) throws IOException {
        Map map = fileService.FileUploadAll(request, imagePath);

        Setdocandimgandmp4 setdocandimgandmp4 = new Setdocandimgandmp4(map).invoke();
        String docPath = setdocandimgandmp4.getDocPath();
        String jpgPath = setdocandimgandmp4.getJpgPath();
        String videoPath = setdocandimgandmp4.getVideoPath();


        String id = RandomUtil.getRandomString(8) + "-" + RandomUtil.getRandomString(4) + "-" + RandomUtil.getRandomString(4)
                + "-" + RandomUtil.getRandomString(4) + "-" + RandomUtil.getRandomString(12);
        knowledge.setId(id);

        knowledgeSet(knowledge, docPath, jpgPath, videoPath);

        log.info("添加的时间》》》" + knowledge.getBuildTime());
        log.info("当前时间》》》》" + System.currentTimeMillis());
        knowledgeMapper.insertSelective(knowledge);
    }

    @Override
    public void updateKnowledge(Knowledge knowledge, HttpServletRequest request, MultipartFile[] imagePath) throws IOException {
//        Map map =fileService.handleFileUpload(request);
        Map map = fileService.FileUploadAll(request, imagePath);

        Setdocandimgandmp4 setdocandimgandmp4 = new Setdocandimgandmp4(map).invoke();
        String docPath = setdocandimgandmp4.getDocPath();
        String jpgPath = setdocandimgandmp4.getJpgPath();
        String videoPath = setdocandimgandmp4.getVideoPath();

        knowledgeSet(knowledge, docPath, jpgPath, videoPath);
        log.info("修改的时间》》》" + knowledge.getBuildTime());
        log.info("当前时间》》》》" + System.currentTimeMillis());
        knowledgeMapper.updateKnowledge(knowledge);
    }

    private void knowledgeSet(Knowledge knowledge, String docPath, String jpgPath, String videoPath) {
        knowledge.setImagePath(jpgPath);
        knowledge.setDocPath(docPath);
        knowledge.setVideoPath(videoPath);
        //得到一个timestamp格式的时间，存入mysql中的时间格式为"yyyy-MM-dd HH:mm:ss"
        Timestamp timestamp = new Timestamp(new Date().getTime());
        knowledge.setBuildTime(timestamp);
    }

    @Override
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, String path) throws UnsupportedEncodingException {

        int index = filePath.lastIndexOf("//");
        String fileName = path.substring(index + 2);
        String result = fileService.downloadFile(request, response, fileName);
        log.info(fileName + ">>>>>>" + result);
        return "success";
    }

    @Override
    public String downloadFileMore(String Id) {
        //需要压缩的文件--包括文件地址和文件名
        String imgPath = knowledgeMapper.queryImgPathById(Id);
        return fileService.downloadFileMore(imgPath);
    }

    @Override
    public void deleteBatchByIds(List<String> Ids) {
        knowledgeMapper.deleteBatchByIds(Ids);
    }

    @Override
    public Knowledge queryKnowledgeById(String Id) {
        return knowledgeMapper.queryKnowledgeById(Id);
    }

    @Override
    public Map querySortAndOrg() {
        List<Org> orgs = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("sort", knowledgeSortMapper.querySortAll());
        map.put("org", getChild(orgMapper.queryOrgAll(), new ArrayList<Org>()));
        return map;
    }


    private List<Org> getChild(List<Org> orgs, List<Org> child) {
        List<Org> orgList = null;
        for (Org org : orgs) {
            if (org.getChild().size() == 0) {
                child.add(org);
                orgList = child;
            } else {
                orgList = getChild(org.getChild(), child);
            }

        }
        return orgList;
    }

    private class Setdocandimgandmp4 {
        private Map map;
        private String docPath;
        private String jpgPath;
        private String videoPath;

        public Setdocandimgandmp4(Map map) {
            this.map = map;
        }

        public String getDocPath() {
            return docPath;
        }

        public String getJpgPath() {
            return jpgPath;
        }

        public String getVideoPath() {
            return videoPath;
        }

        public Setdocandimgandmp4 invoke() {
            docPath = null;
            jpgPath = null;
            videoPath = null;

            if (StringUtil.isNotEmpty((String) map.get("docPath"))) {
                docPath = (String) map.get("docPath");
            }
            if (StringUtil.isNotEmpty((String) map.get("jpgPath"))) {
                jpgPath = (String) map.get("jpgPath");
            }
            if (StringUtil.isNotEmpty((String) map.get("mp4Path"))) {
                videoPath = (String) map.get("mp4Path");
            }
            return this;
        }
    }
}
