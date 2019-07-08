package com.leyou.uplaod.web;

import com.leyou.uplaod.servcie.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Auther: seadragon
 * @Date: 2019-06-13 20:58
 * @Description:
 */

@RestController
@RequestMapping("upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    //上传文件
    @RequestMapping(value = "image",method = RequestMethod.POST)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(uploadService.uploadImage(file));
    }

}
