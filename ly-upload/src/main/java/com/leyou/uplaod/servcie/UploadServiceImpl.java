package com.leyou.uplaod.servcie;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.uplaod.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;



/**
 * @Auther: seadragon
 * @Date: 2019-06-13 21:03
 * @Description:
 */

@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private FastFileStorageClient storageClient;

    @Resource
    private UploadProperties uploadProperties;

    //文件上传
    @Override
    public String uploadImage(MultipartFile file) {

        //校验文件类型
        String contentType = file.getContentType();

        if (!uploadProperties.getAllowTypes().contains(contentType)) {
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE_ERROR);
        }

        //校验文件的内容
        try {

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            if (bufferedImage == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE_ERROR);
            }

            //上传图片到FastDFS服务器
            String extension   = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);

            return uploadProperties.getBaseUrl() + storePath.getFullPath();

        } catch (IOException e) {
            //上传失败
            throw new LyException(ExceptionEnum.UPLOAD_IMAGE_ERROR);
        }

    }
}

