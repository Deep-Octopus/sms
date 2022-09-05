package com.example.ktp.controller;


import com.example.ktp.dto.Result;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/file")
public class FileController {

    // 设置固定的日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // 将 yml 中的自定义配置注入到这里
    @Value("${gorit.file.root.path}")
    private String filePath;
    // 日志打印
    private Logger log = LoggerFactory.getLogger("FileController");

    @PostMapping("uploadHomework")
    public Result<Object> fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        // 获得时间戳
        String format = String.valueOf(new Date().getTime());
        //时间戳拼接随机数
        format = format.substring(0,format.length()-5)+(int)(Math.random()*100000)+"";
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        // 时间 和 日期拼接
        String newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath +"studentHomework\\"+ newFileName);
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            // 打印日志
            log.info("上传成功，当前上传的文件保存在 {}", filePath + newFileName);
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            Map<String,String> map = new HashMap<>();
            map.put("fileName",newFileName);
            map.put("address",dest.getPath());
            return Result.buildSuccess(map);
        } catch (IOException e) {
            log.error(e.toString());
        }
        // 待完成 —— 文件类型校验工作
        return Result.buildErr(409,"上传错误");
    }

//    @GetMapping("/download")
//    public ResponseEntity<FileSystemResource> download(@RequestParam String path) {
//        System.out.println(path);
//        File file = fileBasicService.download(path);
//        String fileName = file.getName();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", String.format("attachment;filename=\"%s", fileName));
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        headers.add("Last-Modified", new java.util.Date().toString());
//        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .body(new FileSystemResource(file));
//    }

}
