package com.zdw.reggie.backend.controller;


import com.zdw.reggie.common.R;
import com.zdw.reggie.common.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
@Slf4j
public class ImgHandleController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传功能
     * @param file
     * @return
     */
    @PostMapping("/common/upload")
    public R uplaod(MultipartFile file){
        log.info("图片上传功能执行中...");

        //原来的文件名
        String filename = file.getOriginalFilename();
        //截取文件名的后缀信息
        //使用UUID给文件重新起名
        String lastfilename=UUIDUtil.getUUID()+filename.substring(filename.lastIndexOf("."));

        log.info("文件最后的名字------>"+lastfilename);

        //创建目录
        File dir=new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }

        //将文件转存到指定目录

        try {
            file.transferTo(new File(basePath+lastfilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(lastfilename);
    }

    @GetMapping("/common/download")
    public void Download(String name, HttpServletResponse response){
        log.info("图片下载功能执行中...{}",name);
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
