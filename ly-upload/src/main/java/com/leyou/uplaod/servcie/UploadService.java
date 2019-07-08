package com.leyou.uplaod.servcie;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: seadragon
 * @Date: 2019-06-13 21:03
 * @Description:
 */

public interface UploadService {
    //上传图片
    String uploadImage(MultipartFile file);
}
