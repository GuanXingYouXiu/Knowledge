package com.example.demo.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


@Controller
@RequestMapping("commodity")
public class testcontroller {


    @RequestMapping("todownloadhtml")
    public String download(Model model) {
        model.addAttribute("img", "C://Users//23108//Desktop//Video.mp4");
        return "download";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> export(@RequestParam("strZipPath") String strZipPath) throws IOException {

        String fileName = strZipPath.substring(strZipPath.lastIndexOf("//") + 1);
        String filePath = strZipPath.substring(0, strZipPath.lastIndexOf("//"));

        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath + "//" + fileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }


    @PostMapping("addCommodity")
    @ResponseBody
    public String addCommodityPic(HttpServletRequest request, @RequestParam MultipartFile[] commoPicArr) throws IOException {
//        int i=commoPicArr.length;
//        commoPicArr.
        for (MultipartFile file : commoPicArr) {
            if (file != null) {// 判断上传的文件是否为空
                String path = null;// 文件路径
                String type = null;// 文件类型
                String fileName = file.getOriginalFilename();// 文件原名称
                System.out.println("上传的文件原名称:" + fileName);
                // 判断文件类型
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                if (type != null) {// 判断文件类型是否为空
//                    if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
//                        path = realPath +/*System.getProperty("file.separator")+*/trueFileName;
                    path = "D:/springUpload" + file.getOriginalFilename();
                    System.out.println("存放图片文件的路径:" + path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
//                    } else {
//                        System.out.println("不是我们想要的文件类型,请按要求重新上传");
//                        return null;
//                    }
                } else {
                    System.out.println("文件类型为空");
                    return null;
                }
            } else {
                System.out.println("没有找到相对应的文件");
                return null;
            }
        }


        return "kjljljlj";
    }
}

//    //一次遍历所有文件
//    MultipartFile file=multiRequest.getFile(iter.next().toString());
//                if(file!=null)
//                        {
//                        String path="E:/springUpload"+file.getOriginalFilename();
//                        //上传
//                        file.transferTo(new File(path));
//                        }