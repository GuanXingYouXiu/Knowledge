package com.example.demo.controller;

import com.example.demo.servise.impl.FileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @ClassName: FileController
 * @Description: 文件上传、下载处理
 * @author: LZA
 * @date: 2019-03-07
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {

    @Autowired
    private FileServiceImpl fileService;

    /**文件上传*/
    @PostMapping("upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.fileUpload(file);
    }

    /**多文件上传*/
    @PostMapping("uploadMore")
    @ResponseBody
    public Map handleFileUpload(HttpServletRequest request){
       return fileService.handleFileUpload(request);
    }

    /**文件下载*/
    @GetMapping ("download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,String fileName)throws UnsupportedEncodingException {
        return fileService.downloadFile(request,response,fileName);
    }

    /**多文件压缩包下载*/
    @GetMapping("downloadMore")
    public String downloadFileMore(String imgPath){
        return fileService.downloadFileMore(imgPath);
    }
}
