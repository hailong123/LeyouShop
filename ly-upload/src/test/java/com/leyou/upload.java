package com.leyou;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Auther: seadragon
 * @Date: 2019-06-18 20:30
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class upload {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    ThumbImageConfig thumbImageConfig;

    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("/Users/seadragon/Desktop/images.jpeg");
        //上传并且生成缩略图
        StorePath storePath = this.storageClient.uploadFile(new FileInputStream(file), file.length(), "jpeg",null);
        //带分组的路径
        System.out.printf(storePath.getFullPath());
        //不带组的路径
        System.out.printf(storePath.getPath());
    }


    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException {
        File file = new File("/Users/seadragon/Desktop/images.jpeg");
        //上传并且生成带缩略图的图片
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(),"jpge",null);
        //带分组的路径
        System.out.printf(storePath.getFullPath());
        //不带分组的路径
        System.out.printf(storePath.getPath());
        //获取缩略图路径
        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);
    }
}
