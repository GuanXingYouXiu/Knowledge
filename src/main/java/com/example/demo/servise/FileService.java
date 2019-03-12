package com.example.demo.servise;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface FileService {

    //文件上传
    String fileUpload (MultipartFile file)throws IOException;

    //多文件上传
    Map handleFileUpload(HttpServletRequest request);

    //文件下载
    String downloadFile(HttpServletRequest request, HttpServletResponse response,String fileName) throws UnsupportedEncodingException;

    //多文件压缩包下载
    String downloadFileMore(String imgPath);
}
