package com.example.demo.servise.impl;

import com.example.demo.servise.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName: FileServiceImpl
 * @Description: 文件的相关操作（上传、下载）
 * @author: LZA
 * @date: 2019-03-08
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    //设置文件的存储路径
    @Value("${File.filePath}")
    private String filePath;

    //设置压缩包的存储路径
    @Value("${File.zipPath}")
    private String zipPath;

    @Override
    public String fileUpload(MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名：---" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("上传文件的后缀名：--" + suffixName);
            String path = filePath + fileName;
            log.info("文件路径：--" + path);
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return path;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传失败";
    }

    @Override
    public Map handleFileUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        Map<String,String> map=new HashMap<>();
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        String fileName=null;
//        String suffix=null;
//        String path = null;
//        StringBuffer jpgFileUrl=new StringBuffer();
//        StringBuffer docFileUrl=new StringBuffer();
//        StringBuffer mp4FileUrl=new StringBuffer();
//        //循环文件，分别做处理
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            //获取文件名
//            fileName = file.getOriginalFilename();
//            log.info("上传的文件名：---"+fileName);
//            //获取文件路径
//            path=filePath+fileName;
//            log.info("上传文件的路径：--"+path);
//            // 检测是否存在目录
//            File dest = new File(path);
//            if (!dest.getParentFile().exists()) {
//                // 如果不存在就新建文件夹
//                dest.getParentFile().mkdirs();
//            }
//            if (!file.isEmpty()) {
//                try {
//                    //如果文件夹与文件都不为空则写入
//                    byte[] bytes = file.getBytes();
//                    //设置文件路径及名字
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(path)));
//                    stream.write(bytes);
//                    stream.close();
//                    //根据文件后缀名，对于不同类型文件做相关处理
//                    suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    if(suffix.equals("doc")){
//                        //该文件为doc文件 ，给路径拼接“ ，”好用于同类型多文件
//                        docFileUrl.append(path+",");
//                        log.info("doc文件的路径>>>>>>>>>>>>>>"+docFileUrl.toString());
//                        map.put("docPath",docFileUrl.toString());
//                    }else if(suffix.equals("jpg") ||suffix.equals("png")){
//                        //该文件为jpg文件 ，给路径拼接“ ，”好用于同类型多文件
//                        jpgFileUrl.append(path+",");
//                        log.info("jpg、png文件的路径>>>>>>>>>>>>>>"+jpgFileUrl.toString());
//                        map.put("jpgPath",jpgFileUrl.toString());
//                    }else if (suffix.equals("mp4")){
//                        //该文件为mp4文件 ，给路径拼接“ ，”好用于同类型多文件
//                        mp4FileUrl.append(path+",");
//                        log.info("mp4文件的路径>>>>>>>>>>>>>>"+mp4FileUrl.toString());
//                        map.put("mp4Path",mp4FileUrl.toString());
//                    }else{
//                        log.info(">>>>>>>>其他类型文件可以继续增加");
//                    }
//
//                } catch (Exception e) {
//                    String errorMessage="第 " + i +" 个文件上传失败  ==> "+ e.getMessage();
//                    map.put("arror",errorMessage );
//                    return map;
//                }
//            } else {
//                String errorMessage="第 " + i + " 个文件上传失败因为文件为空";
//                map.put("arror",errorMessage );
//                return map;
//            }
//        }
        return null;
    }

    @Override
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        // 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath, fileName);
            log.info(">>>>>>>" + file.getPath());
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.setContentType("application/octet-stream");//告诉浏览器输出内容为流;设置了头文件才会有弹框;
//                response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", fileName));
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    log.info("下载文件名>>>>>>>" + file.getName());
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    log.info(fileName + ">>>>>>>success");
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @Override
    public String downloadFileMore(String imgPath) {
        //需要压缩的文件--包括文件地址和文件名
        String[] path = imgPath.split(",");
        // 要生成的压缩文件地址和文件名称
        File zipFile = new File(zipPath);
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        try {
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < path.length; i++) {
                File file = new File(path[i]);
                //将需要压缩的文件格式化为输入流
                zipSource = new FileInputStream(file);
                //压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                ZipEntry zipEntry = new ZipEntry(file.getName());
                //定位该压缩条目位置，开始写入文件到压缩包中
                zipStream.putNextEntry(zipEntry);
                //输入缓冲流
                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                int read = 0;
                //创建读写缓冲区
                byte[] buf = new byte[1024 * 10];
                while ((read = bufferStream.read(buf, 0, 1024 * 10)) != -1) {
                    zipStream.write(buf, 0, read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bufferStream) bufferStream.close();
                if (null != zipStream) zipStream.close();
                if (null != zipSource) zipSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    @Override
    public Map FileUploadAll(HttpServletRequest request, MultipartFile[] imagePath) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        Map<String, String> map = new HashMap<>();
        StringBuffer jpgFileUrl = new StringBuffer();
        StringBuffer docFileUrl = new StringBuffer();
        StringBuffer mp4FileUrl = new StringBuffer();

        upLoadImg(imagePath, jpgFileUrl);
        upLoadDocAndMp4(request, multipartResolver, docFileUrl, mp4FileUrl);

        map.put("jpgPath", jpgFileUrl.toString());
        map.put("docPath", docFileUrl.toString());
        map.put("mp4Path", mp4FileUrl.toString());
        return map;
    }

    private void upLoadDocAndMp4(HttpServletRequest request, CommonsMultipartResolver multipartResolver, StringBuffer docFileUrl, StringBuffer mp4FileUrl) throws IOException {
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            String fileName = null;
            String suffix = null;
            String path = null;
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    fileName = file.getOriginalFilename();
                    path = filePath + fileName;
                    // 检测是否存在目录
                    File dest = new File(path);
                    if (!dest.getParentFile().exists()) {
                        // 如果不存在就新建文件夹
                        dest.getParentFile().mkdirs();
                    }
                    if(!path.equals(filePath)){
                        suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                        if (suffix.equals("docx")) {
                            file.transferTo(new File(path));
                            docFileUrl.append(path + ",");
                        } else if (suffix.equals("mp4")) {
                            file.transferTo(new File(path));
                            mp4FileUrl.append(path + ",");
                        } else {
                            log.info(">>>>>>>>其他类型文件可以继续增加");
                        }
                    }
                }
            }
        }
    }

    private void upLoadImg(MultipartFile[] imagePath, StringBuffer jpgFileUrl) throws IOException {
        String impPath=null;
        for (MultipartFile imgFile : imagePath) {
            impPath=filePath + imgFile.getOriginalFilename();
            File dest = new File(impPath);
            if (!dest.getParentFile().exists()) {
                // 如果不存在就新建文件夹
                dest.getParentFile().mkdirs();
            }
            if(!impPath.equals(filePath)){
                String jpgSuffix = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf(".") + 1);
                if (jpgSuffix.equals("jpg") || jpgSuffix.equals("png")) {
                    imgFile.transferTo(new File(impPath));
                    jpgFileUrl.append(filePath + imgFile.getOriginalFilename() + ",");
                }
            }
        }
    }
}
